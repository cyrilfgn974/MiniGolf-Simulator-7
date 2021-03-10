package interfaces;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PopUpCompteur extends MouseOverArea implements ComponentListener{

    private boolean enabled;
    private float valueToWritre = 0;
    private float pas;
    private float max;
    private int precision;
    private String postValue;

    private String infos = "Veuillez saisir l'angle du tapis roulant";
    private MouseOverArea boutonValider;
    private MouseOverArea boutonAugmenter;
    private MouseOverArea boutonDiminuer;
    private Rectangle principal;
    private Rectangle background;
    private UnicodeFont font;
    private UnicodeFont font2;
    private UnicodeFont font3;

    public PopUpCompteur(GameContainer gameContainer,
                         int width,
                         int height,
                         String phrase,
                         String postValue,
                         float pas,
                         float max,
                         int precision,
                         ComponentListener componentListener) throws SlickException {
        super(gameContainer, new Image( gameContainer.getWidth(), gameContainer.getHeight()), 0,0, componentListener);
        this.principal = new Rectangle(  (float)(gameContainer.getWidth() - width)/2,(float)(gameContainer.getHeight() - height)/2,width, height);
        this.background = new Rectangle(0,0, gameContainer.getWidth(), gameContainer.getHeight());
        this.setInput(gameContainer.getInput());
        this.input.enableKeyRepeat();
        Image fleche = new Image("res/wood_rotate_left.png").getScaledCopy(0.20f);
        this.boutonAugmenter = new MouseOverArea(gameContainer,fleche ,(int)(gameContainer.getWidth() - (1.1*principal.getX()) - fleche.getWidth()) , (int)(principal.getY() + ((principal.getHeight() - fleche.getHeight())/2)),this);
        this.boutonAugmenter.setMouseOverImage(fleche.getScaledCopy(1.1f));
        Image fleche2 = new Image("res/wood_rotate_left_down.png").getScaledCopy(0.20f);
        this.boutonDiminuer = new MouseOverArea(gameContainer, fleche2,(int)(1.1*principal.getX()) , (int)(principal.getY() + ((principal.getHeight() - fleche.getHeight())/2)), this);
        this.boutonDiminuer.setMouseOverImage(fleche2.getScaledCopy(1.1f));
        Image bois = new Image("res/wood_button.png").getScaledCopy(0.2f);
        this.boutonValider = new MouseOverArea(gameContainer,bois, (int)(principal.getX() + ((principal.getWidth() - bois.getWidth())/2)), (int)(principal.getY() + 0.7f*principal.getHeight()), this);
        this.boutonValider.setMouseOverImage(new Image("res/wood_button_hover.png").getScaledCopy(0.21f));
        font = new UnicodeFont( "res/font/cour.ttf", 80, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();

        font2 = new UnicodeFont( "res/font/cour.ttf", 20, true, false);
        font2.getEffects().add(new ColorEffect(java.awt.Color.black));
        font2.addAsciiGlyphs();
        font2.loadGlyphs();

        font3 = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font3.getEffects().add(new ColorEffect(java.awt.Color.black));
        font3.addAsciiGlyphs();
        font3.loadGlyphs();

        this.infos = phrase;
        this.valueToWritre = 0;
        this.max = max;
        this.pas = pas;
        this.precision = precision;
        this.postValue = postValue;
    }

    public void enable(){
        enabled = true;
        this.setFocus(true);
        this.valueToWritre = 0;
    }

    private void disable(){
        enabled = false;
        this.setFocus(false);
    }

    public void doRender(GameContainer gameContainer, Graphics graphics){
        if(enabled){
            graphics.setColor(new Color(0,0,0,122));
            graphics.fill(background);
            graphics.setColor(new Color(200,175, 118));
            graphics.fill(principal);
            String value = String.format("%."+precision+"f", valueToWritre);
            graphics.setFont(font);
            font.drawString(principal.getX() + ((principal.getWidth() - font.getWidth(value+" "+postValue))/2),
                    principal.getY() + ((principal.getHeight() - font.getLineHeight())/2), value +" " + this.postValue);
            this.boutonDiminuer.render(gameContainer,graphics);
            this.boutonAugmenter.render(gameContainer,graphics);
            this.boutonValider.render(gameContainer,graphics);
            graphics.setFont(font2);
            font2.drawString(principal.getX() + ((principal.getWidth() - font2.getWidth(infos))/2),
                    principal.getY() + 0.07f*principal.getHeight(), infos );
            graphics.setFont(font3);
            font3.drawString(principal.getX() - 20 + ((principal.getWidth() - font2.getWidth("Valider"))/2),
                    boutonValider.getY() + (float)((boutonValider.getHeight() - font3.getLineHeight())/2), "Valider" );

        }
    }


    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if(enabled){
            doRender(gameContainer, graphics);
        }
    }

    @Override
    public void mouseWheelMoved(int change) {
       if(change > 0){
           augmenterDegres(this.pas);
       }
       else{
           this.augmenterDegres(-this.pas);
       }
    }


    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_RIGHT || key == Input.KEY_UP)
            augmenterDegres(this.pas);
        else if(key == Input.KEY_LEFT || key == Input.KEY_DOWN)
            augmenterDegres(-this.pas);
    }

    private void augmenterDegres(float pas){
        valueToWritre += pas;
        if(valueToWritre > max)
            valueToWritre -= max;
        else if(valueToWritre < 0)
            valueToWritre += max;
    }

    public float getValueToWritre() {
        return valueToWritre;
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(enabled) {
            if (abstractComponent == boutonAugmenter) {
                augmenterDegres(pas);
            } else if (abstractComponent == boutonDiminuer) {
                augmenterDegres(-pas);

            } else if (abstractComponent == boutonValider) {
                this.notifyListeners();
                disable();
            }
        }
    }
}
