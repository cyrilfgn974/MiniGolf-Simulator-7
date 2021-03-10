package jeu;

import golf.TypeSurface;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class AffichageBalle  {

    private Balle toPrint;
    private final float x,y,width,height;

    private UnicodeFont font;
    private UnicodeFont font2;

    public AffichageBalle(Balle balletoPrint, float x, float y, float width, float height) throws SlickException {
        this.toPrint = balletoPrint;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        font = new UnicodeFont( "res/font/cour.ttf", 40, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
        font2 = new UnicodeFont( "res/font/cour.ttf", 20, true, false);
        font2.getEffects().add(new ColorEffect(java.awt.Color.black));
        font2.addAsciiGlyphs();
        font2.loadGlyphs();
    }

    public Balle getToPrint() {
        return toPrint;
    }

    public void setToPrint(Balle toPrint) {
        this.toPrint = toPrint;
    }

    public float getWidth() {
        return width;
    }

    public float getX() {
        return x;
    }

    public void render(GUIContext gameContainer, Graphics graphics) {
        font.drawString(x + width/2 - font.getWidth(toPrint.getNomTexture())/2f,y, toPrint.getNomTexture());
        font2.drawString(x,y + 2*font.getLineHeight(), "Surface");
        font2.drawString(x + width/3,y + 2*font.getLineHeight(), "Vitesse");
        for(int i = 0; i < TypeSurface.boostable.size(); i++){
            font2.drawString(x,y + 10 + 2*font.getLineHeight() + ((i+1)*font2.getLineHeight()), TypeSurface.boostable.get(i).name());
            float coeff = toPrint.getFrottementX().get(TypeSurface.boostable.get(i));
            Rectangle frottement = new org.newdawn.slick.geom.Rectangle(x + width/3, y + 10 +2*font.getLineHeight() + ((i+1)*font2.getLineHeight()), (1-coeff)*((2*width)/3), font2.getLineHeight()-2);
            GradientFill gf = new GradientFill(x + width/3, 0,
                    Color.red,
                    x + width, 0, Color.green,false);
            graphics.fill(frottement, gf);
        }
    }
}
