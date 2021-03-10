package golf;

import jeu.Balle;

public class SurfaceRough extends Surface {

    private static final long serialVersionUID = 1158365476805873578L;
	private static final String spritePath = "res/rough.png";

    public SurfaceRough(){
        super(spritePath);
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.ROUGH;
    }

    public void actualiserMouvement(Balle balle, float dt){
        balle.setX(balle.getX() + balle.getVx() * dt);
        balle.setY(balle.getY() + balle.getVy() * dt);
        balle.setVx(balle.getVx() * (1 - balle.getFrottementX(this.getType())));
        balle.setVy(balle.getVy() * (1 - balle.getFrottementY(this.getType())));
    }


}
