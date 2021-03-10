package golf;

import jeu.Balle;

public class SurfaceObstacle extends Surface{

	private static final long serialVersionUID = 369671842690604238L;
	private static final String spritePath = "res/obstacle.png";

    public SurfaceObstacle() {
        super(spritePath);
    }

    @Override
    public TypeSurface getType() {
        return TypeSurface.OBSTACLE;
    }

    @Override
    public void actualiserMouvement(Balle balle, float dt) {
    	// Retrouver quelle paroi a touché la bille
    	float ancienX = balle.getX() - balle.getVx() * dt;
    	float ancienY = balle.getY() - balle.getVy() * dt;
    	
    	int caseX = (int) balle.getX();
    	int caseY = (int) balle.getY();
    	
    	float test_X = ancienX;
    	float test_Y = ancienY;
    	// Si la balle etait en dessous, au dessus, a droite ou a gauche
    	// Cas simple
    	//
    	// Si la balle est dans une case en diagonale par dicotomie on cherche a
    	// Retrouver un cas simple
    	
    	// Tant qu'on est pas dans un cas simple faire :
    	float inf_dt = 0;
    	float sup_dt = dt;
    	float new_dt;
    	//System.out.println("nouvelle boucle while sur la case : " + caseX + ";" + caseY + ":  depuis  :" + ancienX + ";" + ancienY);
    	while ((int) test_X != caseX && (int) test_Y != caseY ) {
    		//System.out.println(test_X + ";" + test_Y);
    		// Dans la boucle on est
    		// 		Soit toujours dans la case en diagonale
    		// 		Soit Dans la case finale
    		new_dt = (inf_dt + sup_dt)/2;
    		test_X = ancienX + balle.getVx() * new_dt;
    		test_Y = ancienY + balle.getVy() * new_dt;
    		if ((int) test_X == caseX && (int) test_Y == caseY) {
    			sup_dt = new_dt;
    		} else if ((int) test_X != caseX && (int) test_Y != caseY) {
    			inf_dt = new_dt ;
    		}
    	}
	
    	if ((int) test_X == caseX) {	
    		//System.out.println("-Vy");
    		balle.setVy(-1*balle.getVy());
    	} else if ((int) test_Y == caseY) {
    		//System.out.println("-Vx");
    		balle.setVx(-1*balle.getVx());
    	} else {
    	
    	}
    	balle.setX(ancienX);
    	balle.setY(ancienY);
    }
}
