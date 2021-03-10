package interfaces;

import golf.SurfaceTrou;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class BoutonTexte extends MouseOverArea {

    private final UnicodeFont font;
    private final String texte;

    public BoutonTexte(GUIContext container, String texte, int x, int y,float width, float height, ComponentListener listener) throws SlickException {
        super(container, new Image("res/wood_button.png").getScaledCopy((int)width, (int)height), x, y, listener);
        this.texte = texte;
        this.setMouseOverImage(new Image("res/wood_button_hover.png").getScaledCopy((int)width + 5, (int)height + 5));
        font = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
    }

    public BoutonTexte(GUIContext container, String texte, int x, int y, ComponentListener listener) throws SlickException {
        super(container,new Image("res/wood_button.png").getScaledCopy(0.15f), x, y, listener);
        this.texte = texte;
        this.setMouseOverImage(new Image("res/wood_button_hover.png").getScaledCopy(0.16f));
        font = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
    }
    public BoutonTexte(GUIContext container, String texte, int x, int y, float width, float height) throws SlickException {
        super(container,new Image("res/wood_button.png").getScaledCopy((int)width, (int)height), x, y);
        this.texte = texte;
        this.setMouseOverImage(new Image("res/wood_button_hover.png").getScaledCopy(0.16f));
        font = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
    }

    @Override
    public void render(GUIContext container, Graphics g) {
        super.render(container, g);
        font.drawString(this.getX() + (float)((this.getWidth() - font.getWidth(texte))/2),
                this.getY() + (float)((this.getHeight() - font.getLineHeight())/2),
                texte );
    }

}
