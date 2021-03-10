package jeu;

import exceptions.InHoleException;
import exceptions.InWaterException;
import exceptions.OutOfBoundsException;
import exceptions.PenaliteException;
import golf.Terrain;
import golf.TypeSurface;


public class Joueur {

    private int delta = 1;
    private final String nom;
    private Balle balle;
    private boolean inHole;
    private Terrain terrain;
    private Viseur viseur ;
    public Joueur(String nom, Balle balle) {
    	this.nom = nom;
        this.balle = balle;
        this.viseur = new Viseur(this.balle);

    }

    public void viser() {
        this.viseur.viser(this.terrain.getTrou());
    }
    public boolean isMoving() {
        return this.balle.isMoving();
    }

    public boolean isAiming() {
        return this.viseur.isRendered();
    }

    public Viseur getViseur() {
        return viseur;
    }

    public void setViseur(Viseur viseur) {
        this.viseur = viseur;
        this.viseur.setBalle(this.balle);
    }


    public void jouer() throws PenaliteException, InHoleException {

	    this.viseur.viser(terrain.getTrou());
    	do {
            this.toNextStep(this.delta);
        } while(this.balle.isMoving());
        
        if (this.terrain.getSurface((int) this.balle.getFrontEdgeX(), (int) this.balle.getFrontEdgeY()).getType() == TypeSurface.EAU) {
            throw new InWaterException();
        }
        if (this.terrain.getSurface((int) this.balle.getX(), (int) this.balle.getY()).getType() == TypeSurface.TROU) {
            this.inHole = true;
        }

    }


    public void toNextStep(int delta) throws OutOfBoundsException, InWaterException, InHoleException {
        this.balle.frontEdge();
        this.delta = delta;
        try {
            this.terrain.getSurface((int) this.balle.getFrontEdgeX(), (int) this.balle.getFrontEdgeY()).actualiserMouvement(balle,delta/1000.0f);
            if (!this.balle.isMoving()) {
                if (this.isIn(TypeSurface.EAU)) {
                    throw new InWaterException();
                }
                if (this.isIn(TypeSurface.TROU)) {
                    this.inHole = true;
                    throw new InHoleException();
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new OutOfBoundsException();
        }
    }

    protected boolean isIn(TypeSurface type) {
        return (this.terrain.getSurface((int) this.balle.getX(), (int) this.balle.getY()).getType() == type
                || this.terrain.getSurface((int) (this.balle.getX() + 1),
                (int) (this.balle.getY() + 1)).getType() == type
                || this.terrain.getSurface((int) (this.balle.getX() + 1),
                (int) (this.balle.getY())).getType() == type
                || this.terrain.getSurface((int) (this.balle.getX()),
                (int) (this.balle.getY() + 1)).getType() == type);
    }

    public boolean inHole() {
        return this.inHole;
    }

    public void setInHole(boolean inHole) {
        this.inHole = inHole;
    }

    public Balle getBalle() {
		return this.balle;
	}

	public void setBalle(float x, float y)
    {
        this.balle.setX(x);
        this.balle.setY(y);
    }

    public void setBalle(Balle balle) {
        this.balle = balle;
    }

	public float distance() {
		return this.balle.distance(this.terrain.getTrou());
	}

    public String getNom() {
        return this.nom;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;

    }

    
}