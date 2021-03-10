package jeu;

import golf.Point2D;
import golf.TypeSurface;

import java.util.HashMap;

import static golf.TypeSurface.*;
import static golf.TypeSurface.EAU;

public class Balle extends Point2D {
	public final float V_INI_MAX = 150.0f;
	
	public final float V_MIN = 0.001f;
	
	protected HashMap<TypeSurface, Float> frottementX;
	protected HashMap<TypeSurface, Float> frottementY;
	private float sideSize = 5;
	protected String nomTexture;
	private float Vx;
	private float Vy;
	private float frontEdgeX;
	private float frontEdgeY;


	public Balle(float x, float y) {
		super(x, y);
		this.Vx = 0.0f;
		this.Vy = 0.0f;
		
		this.frottementX = new HashMap<>();
		this.frottementX.put(GREEN, 0.1f);
		this.frottementX.put(FAIRWAY, 0.2f);
		this.frottementX.put(ROUGH, 0.3f);
		this.frottementX.put(BUNKER, 0.4f);
		this.frottementX.put(EAU, 0.5f);
		this.frottementX.put(DEPART, 0.0f);
		this.frottementX.put(TROU,0.9f);
		this.frottementX.put(TAPISROULANT,0.1f);
		this.frottementX.put(BOUTON,0.1f);
		
		this.frottementY = new HashMap<>();
		this.frottementY.put(GREEN, 0.1f);
		this.frottementY.put(FAIRWAY, 0.2f);
		this.frottementY.put(ROUGH, 0.3f);
		this.frottementY.put(BUNKER, 0.4f);
		this.frottementY.put(EAU, 0.5f);
		this.frottementY.put(DEPART, 0.0f);
		this.frottementY.put(TROU,0.7f);
		this.frottementY.put(TAPISROULANT,0.1f);
		this.frottementY.put(BOUTON,0.1f);
		
		this.nomTexture = "classique";
	}

	public Balle() {
		this(0.0f, 0.0f);
	}

	public Balle(Balle balle) {
		super(balle.getX(), balle.getY());
		this.Vx = balle.getVx();
		this.Vy = balle.getVy();
		this.nomTexture = balle.getNomTexture();
		this.frottementX = balle.getFrottementX();
		this.frottementY = balle.getFrottementY();
		this.nomTexture = balle.getNomTexture();
	}


	public void initVitesse(float vitesse, float angle) {
		this.Vx = (float) Math.cos(Math.toRadians(angle))*vitesse;
		this.Vy = (float) Math.sin(Math.toRadians(angle))*vitesse;
	}

	public String getNomTexture() {
		return nomTexture;
	}

	public void setNomTexture(String nomTexture) {
		this.nomTexture = nomTexture;
	}

	public float getVy() {
		return this.Vy;
	}

	public float getVx() {
		return this.Vx;
	}
	
	public float getVitesse() {
		return (float) Math.sqrt(Vx*Vx + Vy*Vy);
	}

	public void setRayon(float rayon) {
		this.sideSize = 2.0f/((float) Math.sqrt(2))*rayon;
	}

	public float getRayon() {
		return ((float) Math.sqrt(2)*sideSize)/2.0f;
	}

	public void setVx(float vx) {
		this.Vx= vx;
	}

	public void setVy(float vy) {
		this.Vy = vy;
	}

	public HashMap<TypeSurface, Float> getFrottementX() {
		return this.frottementX;
	}

	public HashMap<TypeSurface, Float> getFrottementY() {
		return this.frottementY;
	}
	
	public float getFrottementX(TypeSurface surface) {
		return this.frottementX.get(surface);
	}
	
	public float getFrottementY(TypeSurface surface) {
		return this.frottementY.get(surface);
	}
	
	public void setFrottementX(HashMap<TypeSurface, Float> frottementX) {
		this.frottementX = frottementX;
	}

	public void setFrottementY(HashMap<TypeSurface, Float> frottementY) {
		this.frottementY = frottementY;
	}
	
	public boolean isMoving() {
		return (this.getVitesse() > this.V_MIN);
	}

    public float getDirection() {
		if (Vx != 0.0f)
			return (float) Math.toDegrees(Math.atan(this.Vy/this.Vx));
			else {
				if (Vy > 0) {
					return 90.0f;
				} else {
					return -90.0f;
				}
		}
	}

	public void setSideSize(float sideSize) {
		this.sideSize = sideSize;
	}

	public void frontEdge() {
		float dir = getDirection();
		if (dir > -90.0f && dir <0) {
			this.frontEdgeX = this.getX() + 1 ;
			this.frontEdgeY = this.getY() + 1;
		} else if (dir >= 0.0f && dir < 90.0f) {
			this.frontEdgeX = this.getX() + 1 ;
			this.frontEdgeY = this.getY();
		} else if (dir >= 90.0f && dir < 180.0f) {
			this.frontEdgeX = this.getX();
			this.frontEdgeY = this.getY();
		}  else {
			this.frontEdgeX = this.getX();
			this.frontEdgeY = this.getY()+1;
		}
    }
	public float getFrontEdgeX() {
		return this.frontEdgeX;
	}
	public float getFrontEdgeY() {
		return this.frontEdgeY;
	}
}





