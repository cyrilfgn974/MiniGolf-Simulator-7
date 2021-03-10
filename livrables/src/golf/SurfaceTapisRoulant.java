package golf;


import jeu.Balle;
import org.newdawn.slick.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SurfaceTapisRoulant extends Surface  {
	
	private static final long serialVersionUID = 5258232446618749172L;
    private static final String spritePath = "res/tapis_roulant.png";

    private int direction;
    private float coeffAcceleration;

    public SurfaceTapisRoulant(float coeffAcceleration, int direction) {
        super(spritePath);
        this.direction = direction;
        this.coeffAcceleration = coeffAcceleration;
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.TAPISROULANT;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(this.getDirection());
        oos.writeFloat(this.coeffAcceleration);
    }

    private void readObject(ObjectInputStream ois) throws IOException {
        this.direction = ois.readInt();
        this.coeffAcceleration = ois.readFloat();
    }
    
    @Override
    public void drawTexture(float x, float y, float width, float height) {
        super.spriteImg.getSubImage(1,1).draw(x,y,width,height);

        Image img;
        if(coeffAcceleration < 0.8f){
            img = super.spriteImg.getSubImage(0,0);
        }
        else if(coeffAcceleration > 1.2f){
            img = super.spriteImg.getSubImage(2,0);
        }
        else {
            img = super.spriteImg.getSubImage(1,0);
        }
        img = img.getScaledCopy((int)width, (int)height);
        img.rotate(-direction);
        img.draw(x,y);
    }

    @Override
    public void actualiserMouvement(Balle balle, float dt) {
        balle.setX(balle.getX() + balle.getVx() * dt);
        balle.setY(balle.getY() + balle.getVy() * dt);        
        
        balle.setVx(balle.getVx() * (1 - balle.getFrottementX(this.getType()) + this.coeffAcceleration/10.0f * (float) Math.cos(Math.toRadians(direction))));
        balle.setVy(balle.getVy() * (1 - balle.getFrottementY(this.getType()) + this.coeffAcceleration/ 10.0f* (float) Math.sin(Math.toRadians(direction))));
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SurfaceTapisRoulant))
            return false;
        SurfaceTapisRoulant str = (SurfaceTapisRoulant) obj;
        return this.coeffAcceleration == str.coeffAcceleration && this.direction == str.direction;
    }
}
