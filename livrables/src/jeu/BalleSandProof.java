package jeu;


import static golf.TypeSurface.BUNKER;

public class BalleSandProof extends Balle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BalleSandProof(float x, float y) {
		super(x, y);
		this.frottementX.put(BUNKER, 0.0f);
		this.frottementY.put(BUNKER, 0.0f);
	}
}

