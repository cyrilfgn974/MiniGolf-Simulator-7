package jeu;

import exceptions.InHoleException;
import exceptions.PenaliteException;
import golf.Parcours;

import java.util.ArrayList;

public class ArbitreClassique extends Arbitre {

    public ArbitreClassique(ArrayList<Joueur> joueurs, Parcours parcours) {
        super(joueurs, parcours);
    }




    protected void faitJouer() {
        float oldX = this.joueurCourant.getBalle().getX();
        float oldY = this.joueurCourant.getBalle().getY();
        try {
            afficher_terrain();
            System.out.println(this.joueurCourant.getNom() + " a toi de jouer");
            this.joueurCourant.jouer();
            //this.carteScores.addCoup(this.joueurCourant, numTrou);
        } catch (PenaliteException e) {
            // Replacer la balle
            this.joueurCourant.setBalle(oldX, oldY);
            //this.carteScores.addCoup(this.joueurCourant, numTrou);
            //this.carteScores.addCoup(this.joueurCourant, numTrou);
            System.out.println("Pénalité pour " + this.joueurCourant.getNom());
        }
        catch (InHoleException e) {}
    }

    // Trier dans l'ordre décroissant de la distance au trou
    protected void ordreIP() {
        for (int k = nbJoueurEnJeu -1; k >= 0; k--) {
            float dist = this.joueurs.get(this.ordre[k]).distance();
            int aux = ordre[k];
            //tant que la distance au trou du premier joueur est inferieur a la distance au trou du i�me joueur et que i<nbJoueurs
            int i = k;
            while (i < nbJoueurEnJeu-1 &&
                    this.joueurs.get(this.ordre[i+1]).distance()>dist){
                this.ordre[i] = this.ordre[i+1];

                i++;
            }
            ordre[i] = aux;
        }
    }

    public void updateScore() {
        this.carteScores.addCoup(this.joueurCourant,numTerrainCourant);
    }

    protected void ordreDepart() {
        /* Trier */
        for(int i = 1; i < nbJoueur; i++) {
             int x = this.carteScores.getScore(joueurs.get(i),numTerrainCourant);
             int j = i;
             while (j>0 && this.carteScores.getScore(joueurs.get(j- 1),numTerrainCourant) > x) {
                 this.ordre[j] = this.ordre[j-1];
                 j--;
         }
         this.ordre[j] = this.ordre[i];
        }
    }
}

