package interfaces;

import exceptions.FichierAbsentException;
import golf.Parcours;
import jeu.Balle;
import jeu.Joueur;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class TestRenduCarteScore extends BasicGame {

    public static final int WIDTH   = 1280;
    public static final int HEIGHT  = 720;
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;
    private CarteScoreGraphic carte;

    public TestRenduCarteScore() throws FichierAbsentException {
        super("Test Carte de score");
        Parcours P_encours = Parcours.charger("parcours.save");
        Balle B_j1 = new Balle(-1, -1);
        Balle B_j2 = new Balle(-1, -1);
        Joueur J_1 = new Joueur("joueur1", B_j1);
        Joueur J_2 = new Joueur("joueur2", B_j2);
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        joueurs.add(J_1);
        joueurs.add(J_2);
        this.carte = new CarteScoreGraphic(joueurs, P_encours);
        this.carte.addCoup(J_1,1);
        this.carte.addCoup(J_1,2);
        this.carte.addCoup(J_2,1);
        this.carte.addCoup(J_2,2);

    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        carte.init(gameContainer);
        carte.afficher();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (carte.isRendered()) {
            carte.render(gameContainer, graphics);
        }
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new TestRenduCarteScore());
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException | FichierAbsentException e) {
            e.printStackTrace();
        }
    }
}
