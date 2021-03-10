package golf;

import jeu.Balle;

public class SurfaceEau extends Surface {


    private static final String spritePath = "res/eau.png";

    public SurfaceEau() {
        super(spritePath);
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.EAU;
    }

    public void actualiserMouvement(Balle balle, float dt){
        balle.setX(balle.getX() + balle.getVx() * dt);
        balle.setY(balle.getY() + balle.getVy() * dt);
        balle.setVx(balle.getVx() * (1 - balle.getFrottementX(this.getType())));
        balle.setVy(balle.getVy() * (1 - balle.getFrottementY(this.getType())));
    }
}
