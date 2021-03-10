package interfaces;

import exceptions.FichierAbsentException;
import golf.Parcours;
import jeu.CarteScore;
import jeu.Joueur;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;

import java.awt.Font;
import java.util.ArrayList;

public class CarteScoreGraphic extends CarteScore {

    private Image carte;
    private final Font awtFont = new java.awt.Font("Calibri", 1, 23);
    private TrueTypeFont font;
    public static float widthCarte = ViewController.WIDTH * 0.7f;
    public static float heightCarte = ViewController.HEIGHT * 0.7f;
    public static float widthCase = 44.0f;
    public static float heightCase = 31.0f;
    public static float xCarte = (ViewController.WIDTH - widthCarte)/ 2.0f;
    public static float yCarte = (ViewController.HEIGHT - heightCarte)/ 2.0f;
    private boolean estTermine;

    public CarteScoreGraphic(ArrayList<Joueur> joueurs,
                             Parcours parcours)
            throws FichierAbsentException {
        super(joueurs, parcours);
    }

    public CarteScoreGraphic(CarteScore carteScore) throws FichierAbsentException {
        this(carteScore.getJoueurs(), carteScore.getParcours());
    }


    public void init(GameContainer gameContainer) throws SlickException {
        font = new TrueTypeFont(awtFont, false);
        carte = new Image("./res/cartescores/carte.png");
        this.render = false;
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //Afficher la carte
        carte.draw(xCarte, yCarte, widthCarte, heightCarte);

        // Afficher les nom des joueurs
        for (int k = 0; k < joueurs.size(); k++) {
            font.drawString(xCarte + 145, yCarte + 227 + k * heightCase, joueurs.get(k).getNom(), Color.black);
        }

        // Afficher les pars et les scores
        for (int k = 0; k < this.pars.size(); k++) {
            font.drawString(
                    xCarte + 280 + k * widthCase - font.getWidth(Integer.toString(pars.get(k))) / 2.0f,
                    yCarte + 198,
                    Integer.toString(pars.get(k)),
                    Color.black);

            for (Joueur joueur : joueurs) {
                if (carteScore.get(joueur).getScore(k + 1) > 0) {
                    font.drawString(
                            xCarte + 280 + widthCase * k
                                    - font.getWidth(Integer.toString(carteScore.get(joueur).getScore(k + 1))) / 2.0f,
                            yCarte + 198 + heightCase * (joueurs.indexOf(joueur)+1),
                            Integer.toString(carteScore.get(joueur).getScore(k + 1)),
                            Color.black);

                    this.estTermine = true;
                } else {
                    this.estTermine = false;
                }
            }
        }
        // Afficher des croix pour les trous qui n'existe pas
        for (int k = this.pars.size(); k < 9; k++) {
            font.drawString(
                    xCarte + 280 + k * 44 - font.getWidth("X") / 2.0f,
                    yCarte + 198, "X",
                    Color.black);
        }
        // Afficher le totale totale si le parcours est fini
        if (estTermine) {
            for (Joueur joueur : joueurs) {
                font.drawString(
                        xCarte + 288 + widthCase * 9
                                - font.getWidth(Integer.toString(carteScore.get(joueur).total())) / 2.0f,
                        yCarte + 198 + heightCase * (joueurs.indexOf(joueur)+1),
                        Integer.toString(carteScore.get(joueur).total()),
                        Color.black);
            }
        }
    }
}