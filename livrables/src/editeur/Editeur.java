package editeur;



import golf.*;
import interfaces.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Editeur extends BasicGameState implements ComponentListener {

    private final static int ID = 3;

    public static String fileNameTerrain;

    private Image background;

    private Terrain terrain;
    private PopUpCompteur popupAngle;
    private PopUpCompteur popUpVistesse;
    private PopUpNom popUpNom;
    private PopUpCompteur popUpPar;
    private int angleTapis;

    private MouseOverArea boutonValider;
    private BouttonSurface selected;
    private BouttonSurfaceCount buttonDepart;
    private BouttonSurfaceCount buttonTrou;
    private ArrayList<BouttonSurface> buttonsSurfaces;

    private final UnicodeFont font;

    private final float scale = 0.7f;

    private Image imgBoutonDepart;
    private Image imgBoutonTrou ;
    private Image igmBoutonGreen;
    private Image igmBoutonFairway;
    private Image igmBoutonRough;
    private Image igmBoutonEau;
    private Image igmBoutonBunker;
    private Image igmBoutonObsatcle;
    private Image igmTapisRoulant;

    private boolean activateListener;

    private final float terrainSizeX = 1280*scale;
    private final float terrainSizeY = 720*scale;
    private StateBasedGame sbg;

    public Editeur() throws SlickException {
        super();
        font = new UnicodeFont( "res/font/cour.ttf", 38, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame game) throws SlickException {
        Editeur.fileNameTerrain = "";
        this.buttonsSurfaces = new ArrayList<>();
        this.activateListener = true;
        this.terrain = new Terrain();
        this.terrain.loadTexture();
        this.buttonDepart = new BouttonSurfaceCount(gameContainer,
                imgBoutonDepart,
                gameContainer.getWidth()- imgBoutonDepart.getWidth()*scale + (50*scale), 100, scale, 1,
                new SurfaceDepart(),
                new LinearAnimation(new Vector2f(-50*scale, 0), 250), this);
        this.buttonTrou = new BouttonSurfaceCount(gameContainer,
                new Image("res/trou_button.png"),
                gameContainer.getWidth()- imgBoutonTrou.getWidth()*scale + (50*scale), 300, scale, 1,
                new SurfaceTrou(),
                new LinearAnimation(new Vector2f(-50*scale, 0), 250), this);
        this.buttonsSurfaces.add(new BouttonSurface(gameContainer,
                igmBoutonGreen,
                10, gameContainer.getHeight()- igmBoutonGreen.getHeight()*scale + (50*scale), scale,
                new SurfaceGreen(),
                new LinearAnimation(new Vector2f(0, -50*scale), 250), this));
        this.buttonsSurfaces.add(new BouttonSurface(gameContainer,
                igmBoutonFairway,
                160, gameContainer.getHeight()- igmBoutonFairway.getHeight()*scale + (50*scale), scale,
                new SurfaceFairway(),
                new LinearAnimation(new Vector2f(0, -50*scale), 250), this));
        this.buttonsSurfaces.add(new BouttonSurface(gameContainer,
                igmBoutonRough,
                310, gameContainer.getHeight()- igmBoutonRough.getHeight()*scale + (50*scale), scale,
                new SurfaceRough(),
                new LinearAnimation(new Vector2f(0, -50*scale), 250), this));
        this.buttonsSurfaces.add(new BouttonSurface(gameContainer,
                igmBoutonBunker,
                460, gameContainer.getHeight()- igmBoutonBunker.getHeight()*scale + (50*scale), scale,
                new SurfaceBunker(),
                new LinearAnimation(new Vector2f(0, -50*scale), 250), this));
        this.buttonsSurfaces.add(new BouttonSurface(gameContainer,
                igmBoutonObsatcle,
                610, gameContainer.getHeight()- igmBoutonObsatcle.getHeight()*scale + (50*scale), scale,
                new SurfaceObstacle(),
                new LinearAnimation(new Vector2f(0, -50*scale), 250), this));
        this.buttonsSurfaces.add(new BouttonSurface(gameContainer,
                igmBoutonEau,
                760, gameContainer.getHeight()- igmBoutonEau.getHeight()*scale + (50*scale), scale,
                new SurfaceEau(),
                new LinearAnimation(new Vector2f(0, -50*scale), 250), this));
        this.buttonsSurfaces.add(new BouttonSurface(gameContainer,
                igmTapisRoulant,
                910, gameContainer.getHeight()- igmBoutonObsatcle.getHeight()*scale + (50*scale), scale,
                new SurfaceObstacle(),
                new LinearAnimation(new Vector2f(0, -50*scale), 250), this));
        this.popupAngle = new PopUpCompteur(gameContainer ,
                700,
                300,
                "Saisissez l'angle d'orientation du tapis roulant",
                "\u00b0",
                1f,
                360,
                0,
                this);
        this.popUpVistesse = new PopUpCompteur(gameContainer ,
                700,
                300,
                "Saisissez la vitesse du tapis roulant",
                "",
                0.01f,
                2.0f,
                2,
                this);
        Image imgValider = new Image("res/wood.png").getScaledCopy(0.05f);
        this.boutonValider = new MouseOverArea(gameContainer,
                imgValider,
                gameContainer.getWidth() - 15 - imgValider.getWidth(),
                gameContainer.getHeight() -10 - imgValider.getHeight(),
                this
        );
        this.popUpPar = new PopUpCompteur(gameContainer ,
                700,
                300,
                "Saisissez le nombre de coup pour faire un par",
                "coups",
                1f,
                20f,
                0,
                this);
        this.popUpNom = new PopUpNom(gameContainer,
                this.terrain,
                700,
                300,
                "Veuillez saisir le nom du Terrain",
                this);
        this.boutonValider.setMouseOverImage(imgValider.getScaledCopy(1.1f));
        this.buttonDepart.getSurface().loadTexture();
        this.buttonTrou.getSurface().loadTexture();
        for(BouttonSurface b : buttonsSurfaces){
            b.getSurface().loadTexture();
        }

    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        this.background = new Image("res/back2.png");
        this.sbg = stateBasedGame;
        imgBoutonDepart = new Image("res/depart_button.png");
        imgBoutonTrou = new Image("res/trou_button.png");
        igmBoutonGreen = new Image("res/green_button.png");
        igmBoutonFairway = new Image("res/fairway_button.png");
        igmBoutonRough = new Image("res/rough_button.png");
        igmBoutonEau = new Image("res/eau_button.png");
        igmBoutonBunker = new Image("res/bunker_button.png");
        igmBoutonObsatcle = new Image("res/obstacle_button.png");
        igmTapisRoulant = new Image("res/tpr_button.png");

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        this.background.draw(0,0, gameContainer.getWidth(), gameContainer.getHeight());
        graphics.setColor(Color.black);
        graphics.fill(new Rectangle(0,0,terrainSizeX, terrainSizeY));
        this.terrain.drawMap(0,0, terrainSizeX,terrainSizeY, 1);
        this.buttonDepart.render(gameContainer, graphics);
        this.buttonTrou.render(gameContainer,graphics);

        for(BouttonSurface b : buttonsSurfaces){
            b.render(gameContainer, graphics);
        }
        boutonValider.render(gameContainer,graphics);
        graphics.setFont(font);
        font.drawString(boutonValider.getX() + (float)((boutonValider.getWidth() - font.getWidth("Valider"))/2),
                boutonValider.getY() + (float)((boutonValider.getHeight() - font.getLineHeight())/2),
                "Valider");
        popupAngle.render(gameContainer, graphics);
        popUpVistesse.render(gameContainer,graphics);
        popUpNom.render(gameContainer,graphics);
        popUpPar.render(gameContainer,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        if(terrain.haveDepart())
            buttonDepart.setCounter(0);
        else{
            buttonDepart.setCounter(1);
        }
        if(terrain.haveTrou())
            buttonTrou.setCounter(0);
        else{
            buttonTrou.setCounter(1);
        }
        this.buttonDepart.update(i);
        this.buttonTrou.update(i);
        for(BouttonSurface b : buttonsSurfaces){
            b.update(i);
        }
    }


    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        if(newx > 0 && newx < terrainSizeX && newy > 0 && newy < terrainSizeY && selected != null && activateListener) {
            int xPosToMod = (int) (newx / (terrainSizeX / Terrain.NB_COLONNE_TERRAIN));
            int yPosToMod = (int) (newy / (terrainSizeY / Terrain.NB_LIGNE_TERRAIN));
            terrain.setSurface(xPosToMod,yPosToMod , selected.getSurface());
        }
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(abstractComponent == popupAngle){
            this.popUpVistesse.enable();
            this.angleTapis = (int)((PopUpCompteur)abstractComponent).getValueToWritre();
        }
        else if(abstractComponent == popUpPar){
            this.terrain.getInfos().setPar((int)this.popUpPar.getValueToWritre());
            this.popUpNom.enable();
        }
        else if(abstractComponent == popUpNom){
            this.activateListener = true;
            sbg.enterState(ViewController.AJOUTTERRAINEDITEUR);
        }
        else if (abstractComponent == popUpVistesse){
            buttonsSurfaces.get(6).setSurface(new SurfaceTapisRoulant(((PopUpCompteur)abstractComponent).getValueToWritre(),angleTapis));
            selected = buttonsSurfaces.get(6);
            try {
                selected.getSurface().loadTexture();
            } catch (SlickException e) {
                e.printStackTrace();
            }
            this.activateListener = true;
        }
        else if(abstractComponent == boutonValider){
            if(this.terrain.estValide()){
                this.popUpPar.enable();
                this.activateListener = false;
            }
        }
        else if(!isRunningAnimation() && activateListener) {
            if(abstractComponent == buttonsSurfaces.get(6)){
                popupAngle.enable();
                this.activateListener = false;
            }
            if (selected != null && selected != abstractComponent) {
                this.selected.startAnimation();
            }
            if (selected != abstractComponent) {
                this.selected = (BouttonSurface) abstractComponent;
                this.selected.startAnimation();
            }
        }
    }

    private boolean isRunningAnimation() {
        for (BouttonSurface b : buttonsSurfaces){
            if(b.isAnimated()){
                return true;
            }
        }
        return buttonDepart.isAnimated() || buttonTrou.isAnimated();
    }
}
