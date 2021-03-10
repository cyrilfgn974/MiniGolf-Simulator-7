package golf;

import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SurfaceDepart extends SurfaceGreen implements Positionable{
    private Point2D position;

    private static final String spritePath = "res/depart.png";

    private static final long serialVersionUID = 6L;

    public SurfaceDepart() {
        super(spritePath);
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.DEPART;
    }

    public void deletePosition(){
        position = null;
    }

    public void setPosition(float x, float y) {
        this.position = new Point2D(x,y);
    }

    public Point2D getPosition() {
        if (position == null)
            return null;
        return position.clone();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(position);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    	this.position = (Point2D) ois.readObject();
    }
}
