package jeu;

import exceptions.FichierAbsentException;
import exceptions.InHoleException;
import exceptions.PenaliteException;
import golf.Parcours;

import java.util.ArrayList;
import java.util.Scanner;

public class ArbitreCoop extends Arbitre {
    private Joueur equipe;
    private Scanner sc;

    public ArbitreCoop(ArrayList<Joueur> joueurs, Parcours parcours) throws FichierAbsentException {
        super(joueurs, parcours);
        this.equipe = new Joueur("TEAM", null);
        ArrayList<Joueur> listEquipe =new ArrayList<>();
        listEquipe.add(this.equipe);
        this.carteScores = new CarteScore( listEquipe, parcours);
        initDecisionJoueur();
    }

    public void updateScore() {
        this.carteScores.addCoup(this.equipe,numTerrainCourant);
    }

    public void initDecisionJoueur() {
        this.sc = new Scanner(System.in);;
    }

    protected void ordreDepart() {
        ordreIP();
    }

    protected void ordreIP() {
        System.out.print("Qui joue ? ");
        for(int k = 0; k < nbJoueur ; k++ ) {
            System.out.print(this.joueurs.get(k).getNom() + "(" + k + ") | " );
        }
        this.ordre[0] = sc.nextInt();
        this.ordre[1] = (this.ordre[0] == 0)? 1 : 0;
    }

    protected void faitJouer() {
        float oldX = this.joueurCourant.getBalle().getX();
        float oldY = this.joueurCourant.getBalle().getY();
        try {
            afficher_terrain();
            System.out.println(this.joueurCourant.getNom() + " a toi de jouer");
            this.joueurCourant.jouer();
            this.carteScores.addCoup(this.equipe, numTerrainCourant);
        } catch (PenaliteException e) {
            // Replacer la balle
            this.joueurCourant.setBalle(oldX, oldY);
            this.carteScores.addCoup(this.equipe, numTerrainCourant);
            this.carteScores.addCoup(this.equipe, numTerrainCourant);
            System.out.println("Pénalité pour " + this.equipe.getNom());
        } catch (InHoleException e) {}
    }

}
