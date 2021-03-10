package interfaces;

import editeur.Editeur;
import exceptions.FichierAbsentException;
import golf.Terrain;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class AfficheurTerrain extends BasicGameState {
    public static final int ID = 2;

    private Terrain terrain;
    public AfficheurTerrain(){

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        try {
            this.terrain = Terrain.charger(Editeur.fileNameTerrain + ".save");
        } catch (FichierAbsentException e) {
            e.printStackTrace();
        }
        terrain.loadTexture();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
    }



    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
            terrain.drawMap(0, 0, gameContainer.getWidth(), gameContainer.getHeight(), 0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {

    }
}
