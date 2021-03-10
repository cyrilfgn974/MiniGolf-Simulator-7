package golf;

import jeu.Balle;

public class SurfaceFairway extends Surface {

    public static final int UID = 2;
    private static final String spritePath = "res/fairway.png";

    public SurfaceFairway(){
        super(spritePath);
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.FAIRWAY;
    }

    public void actualiserMouvement(Balle balle, float dt){
        balle.setX(balle.getX() + balle.getVx() * dt);
        balle.setY(balle.getY() + balle.getVy() * dt);
        balle.setVx(balle.getVx() * (1 - balle.getFrottementX(this.getType())));
        balle.setVy(balle.getVy() * (1 - balle.getFrottementY(this.getType())));
    }

}
