package golf;

import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SurfaceTrou extends SurfaceGreen implements Positionable{

    private static final String spritePath = "res/trou.png";
    private static final long serialVersionUID = 7L;

    private Point2D position;

    public SurfaceTrou() {
        super(spritePath);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(position);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.position = (Point2D) ois.readObject();
    }

    public Point2D getPosition() {
        if (position == null)
            return null;
        return position.clone();
    }

    public void setPosition(float x, float y ) {
        this.position = new Point2D(x,y);
    }

    public void deletePosition(){
        this.position = null;
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.TROU;
    }
}
