package interfaces;


import java.io.File;

import jeu.SelectionJoueur;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//import interfaces.CoopState;
import editeur.Editeur;
//import interfaces.BoutiqueState;


public class ViewController extends StateBasedGame {

    public static final int MAINMENUSTATE = 0;
    public static final int CLASSIQUESTATE = 1;
    //public static final int COOPSTATE = 2;
    public static final int EDITEURSTATE = 3;
    public static final int CONNEXIONEDITEURSTATE = 7;
    public static final int AJOUTTERRAINEDITEUR = 8;
    //public static final int BOUTIQUESTATE = 5;
    public static final String gamename = "Mini Golf Simulator 7";
    
 // Application Properties
    public static final int WIDTH   = 1280;
    public static final int HEIGHT  = 720;
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;
    
    public ViewController(String gamename) {
		super(gamename); // Texte affiché comme titre de la fenêtre
    }

    @Override
    public void initStatesList(GameContainer gameContainer)
	throws SlickException {
		this.addState(new MainMenuState());
		this.addState(new SelectionJoueur());
		this.addState(new SelectionParcours());
		this.addState(new Partie());
        //this.addState(new MainMenuState(COOPSTATE));
		this.addState(new Editeur());
		this.addState(new ConnexionEditeur());
		this.addState(new AjoutTerrain());
		//this.addState(new MainMenuState(BOUTIQUESTATE));

    }
    // Main Method
    public static void main(String[] args) {
    	System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("natives").getAbsolutePath());
    	try {
            AppGameContainer app = new AppGameContainer(new ViewController(gamename));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }
}