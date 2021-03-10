package jeu;

import exceptions.FichierAbsentException;
import golf.Parcours;
import golf.Terrain;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public abstract class Arbitre {
	protected  Terrain terrainCourant;
	protected int numTerrainCourant;
	protected int nbJoueur;
	protected int nbJoueurEnJeu;
	protected ArrayList<Joueur> joueurs;
	protected CarteScore carteScores;
	protected Parcours parcours;
	protected int ordre[];
	protected Joueur joueurCourant;
	protected float formerX;
	protected float formerY;
	private int nbJoueurAuDepart;
	private int nbTerrain;

	public Arbitre(ArrayList<Joueur> joueurs, Parcours parcours) {
		this.joueurs = joueurs;
		this.parcours = parcours;
	}

	public void init() throws FichierAbsentException {
		this.nbTerrain = parcours.getNbTerrains();
		this.nbJoueur = this.joueurs.size();
		this.nbJoueurEnJeu = 0;
		this.nbJoueurAuDepart = 0;
		this.ordre = new int[this.nbJoueur];
		this.carteScores = new CarteScore(this.joueurs, this.parcours);
		/* Initialiser ordre */
		for(int i =0; i < this.joueurs.size(); i++) {
			this.ordre[i] = i;
		}
		this.numTerrainCourant = 0;
		this.joueurCourant = joueurs.get(ordre[0]);


	}

	void arbitrer() throws FichierAbsentException, SlickException {
		// Ordre des joueurs et Nombre de trous a jouer
		init();
		int nbTrous = this.parcours.getNbTerrains();
		int j;
		for (int numTrou = 1; numTrou <= nbTrous; numTrou++) {
			// Chargement du Nouveau Terrain
			System.out.println(this.parcours.getNbTerrains());
			System.out.println(this.parcours.getInfoAllTerrain());
			this.parcours.chargerTerrainSuivant();
			System.out.println("hello");
			Terrain terrain = this.parcours.getTerrainCourant();
			//System.out.println(this.parcours.getTerrainCourant());
			System.out.print(terrain.estValide());
			int nbPIP = joueurs.size(); //Nombre de joueurs qui n'ont pas atteint le trou

			// Mettre les joueurs au depart
			j = 0;
			while (j < nbPIP) {
				System.out.println(ordre[0]);
				System.out.println(this.joueurs.get(ordre[j]));
				this.joueurs.get(ordre[j]).setTerrain(terrain);
				System.out.println(terrain.getDepart().getX());
				this.joueurs.get(ordre[j]).getBalle().setX(terrain.getDepart().getX());
				this.joueurs.get(ordre[j]).getBalle().setY(terrain.getDepart().getY());
				this.joueurs.get(ordre[j]).setInHole(false);
				j++;
			}
			this.ordreDepart();
			// Faire jouer les joueurs au départ
			j = 0;
			while (j < nbPIP) {
				//faire jouer le jeme joueur
				this.joueurCourant = this.joueurs.get(ordre[j]);
				this.faitJouer();
				if (this.joueurCourant.inHole()) {
					ordre[j] = ordre[nbPIP - 1];
					nbPIP--;
				} else {
					j++;
				}
			}
			// Faire jouer une fois que les balles sont en jeu
			while (nbPIP > 0) {
				this.ordreIP();
				this.joueurCourant = this.joueurs.get(ordre[0]);
				this.faitJouer();
				if (this.joueurCourant.inHole()) {
					ordre[0] = ordre[nbPIP - 1];
					nbPIP--;
				}
			}
			this.carteScores.afficher();
		}
	}

	public int getNbTerrain() {
		return this.nbTerrain;
	}

	public void inHole() {
		this.nbJoueurEnJeu--;
		int aux = this.ordre[this.nbJoueurEnJeu];
		this.ordre[this.nbJoueurEnJeu] = this.ordre[0];
		this.ordre[0] = aux;
	}
	public Terrain getTerrainCourant() {
		return terrainCourant;
	}

	public void changerJoueur() {

			this.joueurCourant = this.joueurs.get(ordre[0]);
			this.formerX = this.joueurCourant.getBalle().getX();
			this.formerY = this.joueurCourant.getBalle().getY();
	}
	public int[] getOrdre(){
		return ordre;
	}

	public void changerTerrain() throws FichierAbsentException, SlickException {
		this.parcours.chargerTerrainSuivant();
		this.terrainCourant = this.parcours.getTerrainCourant();
		this.numTerrainCourant++;
		this.initTerrain();
	}
	protected void initTerrain() {
		int j = 0;
		while (j < nbJoueur) {
			this.joueurs.get(ordre[j]).setTerrain(this.terrainCourant);
			this.joueurs.get(ordre[j]).getBalle().setX(this.terrainCourant.getDepart().getX());
			this.joueurs.get(ordre[j]).getBalle().setY(this.terrainCourant.getDepart().getY());
			this.joueurs.get(ordre[j]).setInHole(false);
			j++;
		}
		this.formerX = this.terrainCourant.getDepart().getX();
		this.formerY = this.terrainCourant.getDepart().getY();
		this.nbJoueurEnJeu = 0;
		this.nbJoueurAuDepart = 0;
		this.ordreDepart();
	}

	public void setCarteScores(CarteScore carte) {
		this.carteScores = carte;
	}

	public void replacerJoueur() {
		this.joueurCourant.getBalle().setX(formerX);
		this.joueurCourant.getBalle().setY(formerY);
		this.joueurCourant.getBalle().setVx(0.0f);
		this.joueurCourant.getBalle().setVy(0.0f);
	}

	public void addJoueurDepart() {
		this.nbJoueurEnJeu ++;
		this.nbJoueurAuDepart ++;
	}
	public void updateOrdreJeu() {

		if (this.nbJoueurAuDepart < nbJoueur) {
			nbJoueurEnJeu ++;
			this.nbJoueurAuDepart++;
			int aux = ordre[0];
			int j = 0;
			while(j < this.nbJoueur-1) {
				ordre[j] = ordre[j+1];
				j++;
			}
			ordre[this.nbJoueur-1] = aux;
		}
		// Si tous les joueurs ont déjà joué un coup
		else {
			this.ordreIP();
		}
	}

	public void afficherScore() {
		this.carteScores.afficher();
	}


	public boolean atDepart() {
		return this.nbJoueurAuDepart<this.nbJoueur;
	}

	protected abstract void faitJouer();

	protected abstract void ordreDepart();

	protected abstract void ordreIP();

	protected void afficher_terrain() {
		for (int i=0 ; i<Terrain.NB_COLONNE_TERRAIN; i++) {
			System.out.print("[");
			for (int j=0 ; j<Terrain.NB_LIGNE_TERRAIN; j++) {
				//Pour chaque Case du terrain
				boolean quelqun = false;
				for (int k = 0; k<joueurs.size(); k++) {
					if (!quelqun && !joueurs.get(k).inHole()) {
						if ((int) joueurs.get(k).getBalle().getX() == i && (int) joueurs.get(k).getBalle().getY() == j) {
							System.out.print(" X ");
							quelqun = true;
						}
					}
				}
				if (!quelqun) {
					System.out.print(" ");
					System.out.print(this.parcours.getTerrainCourant().getSurface(i, j).getType().ordinal());
					System.out.print(" ");
				}

			}
			System.out.println("]");
		}
		for (int k = 0; k<joueurs.size(); k++) {
			System.out.println(joueurs.get(k).getNom() + " : " + (int) joueurs.get(k).getBalle().getX() +" ; " +  (int) joueurs.get(k).getBalle().getY() + " :: " + joueurs.get(k).distance());
		}
	}

	public ArrayList<Balle> getBalles() {
		ArrayList<Balle> balles = new ArrayList<>();
		for(Joueur joueur: joueurs) {
			balles.add(joueur.getBalle());
		}
		return balles;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}


	public CarteScore getCarteScore() {
		return this.carteScores;
	}

	public int getNbJoueurEnJeu() {
		return nbJoueurEnJeu;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}

	public boolean isTrouEnded() {
		return nbJoueurEnJeu < 1;
	}

	public abstract void updateScore();

	public int getNumTerrainCourant() {
		return numTerrainCourant;
	}
}