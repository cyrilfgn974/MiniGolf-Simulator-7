package golf;


import jeu.Balle;

public class SurfaceBunker extends Surface{

    public static final int UID = 4 ;
    private static final String spritePath = "res/bunker.png";


    public SurfaceBunker() {
        super(spritePath);
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.BUNKER;
    }

    public void actualiserMouvement(Balle balle, float dt){
        balle.setX(balle.getX() + balle.getVx() * dt);
        balle.setY(balle.getY() + balle.getVy() * dt);
        balle.setVx(balle.getVx() * (1 - balle.getFrottementX(this.getType())));
        balle.setVy(balle.getVy() * (1 - balle.getFrottementY(this.getType())));}

}
