package interfaces;


import golf.Point2D;
import jeu.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;

import java.util.ArrayList;

public class Bandeau {

    private String nomJoueur;
    private int scoreJoueur;
    private int scoreTotal;
    private int par;
    private Image back;
    private float width;
    private float height;
    private UnicodeFont font ;
    private float space = 75.0f;
    private int numeroTerrain;
    private float alpha = 1.0f;
    private ArrayList<Balle> balles;

    public Bandeau() {}


    public void setBandeau(Arbitre arbitre)
    {
        this.nomJoueur = arbitre.getJoueurCourant().getNom();
        this.numeroTerrain = arbitre.getNumTerrainCourant();
        this.balles = arbitre.getBalles();
        this.par = arbitre.getCarteScore().getPar(arbitre.getNumTerrainCourant());
        this.scoreJoueur =  arbitre.getCarteScore().getScore(arbitre.getJoueurCourant(), this.numeroTerrain);
        this.scoreTotal = arbitre.getCarteScore().getScore(arbitre.getJoueurCourant()).total();
    }

    public void init(GameContainer gameContainer) throws SlickException {
        font = new UnicodeFont( "res/font/cour.ttf", 20, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();
        font.loadGlyphs();

        this.back = new Image("./res/wood.png");
        this.width = gameContainer.getWidth()/2.0f;
        this.height = gameContainer.getHeight()/13.0f;
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.back.draw(
                gameContainer.getWidth()/2.0f,
                0,
                this.width,
                this.height);
        this.back.setAlpha(alpha);
        this.font.drawString(
                width + 40,
                15,
                this.nomJoueur );
        this.font.drawString(
                width + 55 + space,
                15,
                "Trou :");
        this.font.drawString(
                width + 90 + space + 50,
                15,
                Integer.toString(this.numeroTerrain));
        this.font.drawString(
                width + 55 + 2* space + 50,
                15,
                "Par :");
        this.font.drawString(
                width + 55 + 2*space + 2*50 ,
                15,
                Integer.toString(this.par));
        this.font.drawString(
                width + 30 + 3*space + 2*50,
                15,
                "Score");
        this.font.drawString(
                width + 45 + 3*space + 3*50,
                15,
                Integer.toString(this.scoreJoueur));
        this.font.drawString(
                width + 35 + 4*space + 3*50,
                15,
                "Total :");
        this.font.drawString(
                width + 80 + 4*space + 4*50,
                15,
                Integer.toString(this.scoreTotal));

    }

    public void update(GameContainer gc) {
        boolean isBehind = false;
        for (Point2D point : this.balles) {
            if (point.getX() > 40 && point.getY() < 4) {
                isBehind = true;
            }
        }
        this.alpha = (isBehind) ? 0.5f : 1.0f;

    }


}
