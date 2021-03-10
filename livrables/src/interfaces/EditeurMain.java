package interfaces;

import editeur.Editeur;
import interfaces.AfficheurTerrain;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;

public class EditeurMain extends StateBasedGame {

    public static void main(String[] args) throws SlickException {
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("natives").getAbsolutePath());
        AppGameContainer app = new AppGameContainer(new EditeurMain("Editeur"));
        app.setDisplayMode(1280, 720, false);
        app.start();

    }

    public EditeurMain(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new Editeur());
        addState(new AfficheurTerrain());
    }


}
