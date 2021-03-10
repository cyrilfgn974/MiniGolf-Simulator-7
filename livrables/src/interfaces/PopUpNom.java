package interfaces;

import editeur.Editeur;
import exceptions.ConfigTerrainException;
import exceptions.FichierExistantException;
import golf.Terrain;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;


public class PopUpNom extends MouseOverArea implements ComponentListener{

    private boolean enabled;

    private final TextField nomTerrain;
    private boolean fileExistant;

    private final String infos;
    private final MouseOverArea boutonValider;
    private final Rectangle principal;
    private final Rectangle background;

    private final Terrain terrain;

    private final UnicodeFont font;
    private final UnicodeFont font2;

    public PopUpNom(GameContainer gameContainer,
                             Terrain terrain,
                             int width,
                             int height,
                             String phrase,
                             ComponentListener componentListener) throws SlickException {
        super(gameContainer, new Image( gameContainer.getWidth(), gameContainer.getHeight()), 0,0, componentListener);
        this.principal = new Rectangle(  (float)(gameContainer.getWidth() - width)/2,(float)(gameContainer.getHeight() - height)/2,width, height);
        this.background = new Rectangle(0,0, gameContainer.getWidth(), gameContainer.getHeight());
        this.setInput(gameContainer.getInput());
        this.input.disableKeyRepeat();
        Image bois = new Image("res/wood_button.png").getScaledCopy(0.2f);
        this.boutonValider = new MouseOverArea(gameContainer,bois, (int)(principal.getX() + ((principal.getWidth() - bois.getWidth())/2)), (int)(principal.getY() + 0.7f*principal.getHeight()), this);
        this.boutonValider.setMouseOverImage(new Image("res/wood_button_hover.png").getScaledCopy(0.21f));
        this.terrain = terrain;

        font = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();

        font2 = new UnicodeFont( "res/font/cour.ttf", 20, true, false);
        font2.getEffects().add(new ColorEffect(java.awt.Color.black));
        font2.addAsciiGlyphs();
        font2.loadGlyphs();

        this.nomTerrain = new TextField(gameContainer,
                font,
                (int)(principal.getX()+(0.1*principal.getWidth())),
                (int)(principal.getY()+(0.37*principal.getHeight())),
                (int)(0.8f*principal.getWidth()),
                (int)(0.14f*principal.getHeight()),
                this);
        this.nomTerrain.setBackgroundColor(Color.white);
        this.nomTerrain.setCursorVisible(true);
        this.nomTerrain.setMaxLength(30);
        this.nomTerrain.deactivate();
        this.setFocus(true);
        this.infos = phrase;
    }

    public void enable(){
        enabled = true;
        this.setFocus(true);
        this.fileExistant = false;
        this.boutonValider.setAcceptingInput(true);

    }

    private void disable(){
        enabled = false;
        this.fileExistant = false;
        this.setFocus(false);
        this.boutonValider.setAcceptingInput(false);
    }

    public void doRender(GameContainer gameContainer, Graphics graphics){
        if(enabled){
            graphics.setColor(new Color(0,0,0,122));
            graphics.fill(background);
            graphics.setColor(new Color(200,175, 118));
            graphics.fill(principal);
            graphics.setFont(font);
            this.boutonValider.render(gameContainer,graphics);
            font.drawString(principal.getX() - 20 + ((principal.getWidth() - font.getWidth("Valider"))/2),
                    boutonValider.getY() + (float)((boutonValider.getHeight() - font.getLineHeight())/2),
                    "Valider" );

            graphics.setFont(font2);
            font2.drawString(principal.getX() + ((principal.getWidth() - font2.getWidth(infos))/2),
                    principal.getY() + 0.07f*principal.getHeight(), infos );
            graphics.setColor(Color.white);
            this.nomTerrain.render(gameContainer,graphics);
            if(fileExistant){
                font2.drawString(principal.getX() + ((principal.getWidth() - font2.getWidth(infos))/2),
                        this.nomTerrain.getY() + this.nomTerrain.getHeight() + 20,
                        "Un terrain porte deja se nom",
                        Color.red);
            }
        }
    }


    public void render(GameContainer gameContainer, Graphics graphics) {
        if(enabled){
            doRender(gameContainer, graphics);
        }
    }



    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(enabled) {
            if (abstractComponent == boutonValider) {
                this.terrain.getInfos().setNomTerrain(this.nomTerrain.getText());
                try {
                    Terrain.sauvegarder(terrain);
                    Editeur.fileNameTerrain = terrain.getInfos().getNomTerrain().replaceAll("[^A-Za-z0-9]", "");
                    this.notifyListeners();
                    disable();
                } catch (ConfigTerrainException e) {
                    e.printStackTrace();
                } catch (FichierExistantException e) {
                    System.out.println("ntm");
                    fileExistant = true;
                }
            }
        }
    }

}
