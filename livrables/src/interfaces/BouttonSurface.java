package interfaces;

import golf.Surface;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

public class BouttonSurface extends AbstractComponent {

    protected float posX;
    protected float posY;
    protected float scale;
    private final LinearAnimation animation;
    private final Image bouton;
    private boolean inUse;
    private Surface surface;

    public BouttonSurface(GUIContext context, Image image, float x, float y, float scale, Surface surface, LinearAnimation animation, ComponentListener listener) {
        super(context);
        this.bouton = image;
        this.animation = animation;
        this.posX = x;
        this.posY = y;
        this.scale = scale;
        this.surface = surface;
        super.addListener(listener);
    }

    public Surface getSurface() {
        return surface;
    }

    public void startAnimation(){
        this.animation.start();
    }

    public void update(int delta){
        this.animation.update(delta);
        Vector2f p = animation.distance();
        this.posX += p.getX();
        this.posY += p.getY();

    }

    public boolean isAnimated(){
        return animation.isRunning();
    }

    @Override
    public void render(GUIContext guiContext, Graphics graphics) throws SlickException {
        bouton.draw(posX, posY, scale);
    }

    @Override
    public void setLocation(int x, int y) {
        posX = x;
        posY = y;
    }

    @Override
    public int getX() {
        return (int)posX;
    }

    @Override
    public int getY() {
        return (int)posY;
    }

    @Override
    public int getWidth() {
        return bouton.getWidth();
    }

    @Override
    public int getHeight() {
        return bouton.getHeight();
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if(x > this.posX && y > this.posY && x < this.posX + this.getWidth() && y < this.posY + this.getHeight()) {
            notifyListeners();
        }
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }
}
