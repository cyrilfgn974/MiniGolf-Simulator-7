package interfaces;

import golf.Surface;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

import java.util.Random;

public class BouttonSurfaceCount extends BouttonSurface{

    private int counter;
    private final static int RAYON = 30;

    public BouttonSurfaceCount(GUIContext context,
                               Image image,
                               float x,
                               float y,
                               float scale,
                               int startCounter,
                               Surface surface,
                               LinearAnimation animation,
                               ComponentListener listener) {
        super(context, image, x, y, scale, surface, animation, listener);
        this.counter = startCounter;
    }

    @Override
    public void render(GUIContext guiContext, Graphics graphics) throws SlickException {
        super.render(guiContext, graphics);
        graphics.setColor(Color.red);
        graphics.fillOval(posX, posY, RAYON, RAYON);
        graphics.setColor(Color.white);
        graphics.drawString( "" + counter,
                posX + ((RAYON - graphics.getFont().getWidth("" + counter))/2f) ,
                posY + ((RAYON - graphics.getFont().getHeight("" + counter))/2f));
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
}
