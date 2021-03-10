package jeu;

import exceptions.FichierAbsentException;
import golf.InfoTerrain;
import golf.Parcours;

import java.util.*;

public class CarteScore {
	protected final HashMap<Joueur, ScoresJoueur > carteScore;
	protected final ArrayList<Integer> pars;
	protected final ArrayList<Joueur> joueurs;
	protected final Parcours parcours;
	protected boolean render;

	public CarteScore(ArrayList<Joueur> joueurs, Parcours parcours) throws FichierAbsentException {
		this.parcours = parcours;
		this.carteScore = new HashMap<>();
		this.joueurs = joueurs;
		ArrayList<InfoTerrain> infoTerrains  = parcours.getInfoAllTerrain();
		this.pars = new ArrayList<Integer>();
		for (InfoTerrain infoTerrain : infoTerrains ) {
			this.pars.add(infoTerrain.getPar());
		}
		for (Joueur joueur : joueurs) {
			this.carteScore.put(joueur, new ScoresJoueur(this.pars));
		}
	}

	public int getPar(int numTrou) {
		return this.pars.get(numTrou-1);
	}

	public void addCoup(Joueur joueur, int numTrou) {
		this.carteScore.get(joueur).addCoup(numTrou);
	}

	public int getScore(Joueur joueur, int numTrou) {
		return this.carteScore.get(joueur).getScore(numTrou);
	}

	public ScoresJoueur getScore(Joueur joueur) {
		return this.carteScore.get(joueur);
	}


	public void afficher() {
		System.out.println(" ");
		System.out.println("Carte des scores ");
		int i = 7;
		for (Joueur joueur : carteScore.keySet()) {
			if (joueur.getNom().length() > i) {
				i = joueur.getNom().length();
			}
		}
		final int nb_espace = i;
		System.out.print("Trou n°");
		i = 0;
		while (i < nb_espace  - 7 ) {
			System.out.print(" ");
			i++;
		}
		System.out.print("|");
		for(int k = 0; k < this.pars.size(); k++) {
			System.out.print(" " + (k+1) + " |");
		}
		System.out.println(" ");
		System.out.print("Par");
		i=0;
		while (i < nb_espace  - 3 ) {
			System.out.print(" ");
			i++;
		}
		System.out.print("|");
		for(int k = 0; k < this.pars.size(); k++) {
			System.out.print(" " + this.pars.get(k) + " |");
		}

		System.out.println(" ");
		this.carteScore.forEach((key,val) -> {
			System.out.print(key.getNom());
			int p = 0;
			while (p < nb_espace  - key.getNom().length()) {
				System.out.print(" ");
				p++;
			}
			System.out.print("|");
			for(int k = 1; k < this.pars.size() + 1; k++) {
				System.out.print(" " + val.getScore(k)+" |");
			}
			System.out.println(" ");
		});




	}
	public boolean isRendered() {
		return render;
	}

	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}

	public Parcours getParcours() {
		return this.parcours;
	}
}
