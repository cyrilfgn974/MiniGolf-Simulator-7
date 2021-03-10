package golf;

import jeu.Balle;

public class SurfaceGreen extends Surface{

    private static final String spritePath = "res/green.png";
    private static final long serialVersionUID = 8L;

    public SurfaceGreen()  {
        super(spritePath);
    }

    protected  SurfaceGreen(String spriteFileName){
        super(spriteFileName);
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.GREEN;
    }

    public void actualiserMouvement(Balle balle, float dt){
    	//System.out.println(balle.getX() + " , " + balle.getY());
    	balle.setX(balle.getX() + balle.getVx() * dt);
        balle.setY(balle.getY() + balle.getVy() * dt);
        balle.setVx(balle.getVx() * (1 - balle.getFrottementX(this.getType())));
        balle.setVy(balle.getVy() * (1 - balle.getFrottementY(this.getType())));
    }

}
