package jeu;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

import java.util.ArrayList;

public class ListeJoueur extends MouseOverArea implements ComponentListener{

    public static ArrayList<Joueur> joueur = new ArrayList<>();
    private final UnicodeFont font;
    private final UnicodeFont font2;

    private float width, height;


    public ListeJoueur(GUIContext container, int x, int y,  float width, float height) throws SlickException {
        super(container, new Image((int)width, (int)height), x,y);
        this.width = width;
        this.height = height;
        font = new UnicodeFont( "res/font/cour.ttf", 25, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
        font2 = new UnicodeFont( "res/font/cour.ttf", 35, true, false);
        font2.getEffects().add(new ColorEffect(java.awt.Color.black));
        font2.addAsciiGlyphs();
        font2.loadGlyphs();

    }

    @Override
    public void render(GUIContext container, Graphics g) {
        g.setColor(new Color(0,0,0,100));
        g.fill(new Rectangle(0,0, width, height));
        font2.drawString((width - font2.getWidth("Liste de joueurs"))/2f,10, "Liste de joueurs");
        for(int i = 0; i < joueur.size(); i++){
            font.drawString(0.1f*width, ((2*i)*font.getLineHeight()) + 2*font2.getLineHeight(), (i+1) + " - " + joueur.get(i).getNom());
            font.drawString((0.1f*width) + 30, (((2*i)+1)*font.getLineHeight()) + 2*font2.getLineHeight(), joueur.get(i).getBalle().getNomTexture());
        }
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }
}
