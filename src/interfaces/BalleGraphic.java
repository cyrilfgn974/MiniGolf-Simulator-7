package interfaces;

import golf.Terrain;
import jeu.Balle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BalleGraphic extends Balle {
    private float width, height;
    private Image image;
    private boolean render = true;

    public BalleGraphic(Balle balle) {
        super(balle);
    }
    public BalleGraphic() {
        super();
    }

    public void init(GameContainer gameContainer) throws SlickException {
        this.image = new Image("./res/balle/"+ this.nomTexture + ".png");
        this.width = this.image.getWidth()*0.05f;
        this.height = this.image.getHeight()*0.05f;
        this.setSideSize(this.width);
    }


    public void update(GameContainer gameContainer, int i) throws SlickException {
    }


    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (render) {
            float caseySize = (float) gameContainer.getWidth()/ Terrain.NB_COLONNE_TERRAIN;
            float casexSize = (float) gameContainer.getHeight()/ Terrain.NB_LIGNE_TERRAIN;
            this.image.draw(this.getX()*caseySize+(caseySize-this.width)/2.0f,
                    this.getY()*casexSize+(casexSize-this.height)/2.0f, this.width, this.height);
        }
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public boolean isRendered() {
        return this.render;
    }
}
