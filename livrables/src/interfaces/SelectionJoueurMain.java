package jeu;

import interfaces.SelectionParcours;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;

public class SelectionJoueurMain extends StateBasedGame {

    public SelectionJoueurMain(String name) {
        super(name);
    }

    public static void main(String[] args) throws SlickException {
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("natives").getAbsolutePath());
        AppGameContainer app = new AppGameContainer(new SelectionJoueurMain("Selection"));
        app.setDisplayMode(1280, 720, false);
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new SelectionJoueur());
        addState(new SelectionParcours());
    }
}
