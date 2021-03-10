package interfaces;

import golf.Point2D;
import golf.Terrain;
import jeu.Balle;

import jeu.Viseur;
import org.newdawn.slick.*;


public class ViseurGraphic extends Viseur implements KeyListener {
    private GameContainer container;
    public boolean render;
    private boolean movingArrow;
    private boolean cursorIsMoving;
    private Image arrow;
    private Image jauge;
    private float pas = 0.06f  ;
    private float scale = 0.6f;
    private float yCursor = 0.0f;
    private float angle;
    private float caseWidth;
    private float caseHeight;
    private float xBalle;
    private float yBalle;
    private Image cursor;
    private float cursorSpeed = 0.2f;
    private float alpha = 0.8f;
    private float movingAxe;
    private boolean acceptedInput;


    public ViseurGraphic(Balle balle) {
        super(balle);
        this.render = false;
        this.movingArrow = false;
        this.cursorIsMoving = false;
        acceptedInput  = false;
    }

    public ViseurGraphic(Viseur viseur) {
        this(viseur.getBalle());
    }


    @Override
    public void initScanner() { };

    @Override
    public void viser(Point2D trou) {
        this.render = true;
        this.movingArrow = true;
        this.cursorIsMoving = false;
        this.axeAngle = this.balle.angle(trou);
        this.acceptedInput = true;
        initPosition();
    }


    public void init(GameContainer gameContainer) throws SlickException {
        this.container = gameContainer;
        this.caseWidth = (float) gameContainer.getWidth()/ Terrain.NB_COLONNE_TERRAIN;
        this.caseHeight = (float) gameContainer.getHeight()/ Terrain.NB_LIGNE_TERRAIN;

        this.arrow = new Image("./res/viseur/fleche.png");
        this.jauge = new Image("./res/viseur/jauge.png");
        this.cursor = new Image("./res/viseur/curseur.png");


    }



    @Override
    public void initPosition() {
        this.xBalle = this.balle.getX()* caseWidth +this.balle.getRayon();
        this.yBalle = this.balle.getY()* caseHeight +this.balle.getRayon();
        this.arrow.setCenterOfRotation(0,this.arrow.getHeight()/2.0f);
        this.arrow.setRotation(this.axeAngle);
        this.movingAxe = 0.0f;

    }


    public void update(GameContainer gameContainer, int i) throws SlickException {
            if (this.movingArrow) {
                if (this.angle > 35.0f) {
                    this.angle = 35.0f;
                    pas = -pas;
                }
                if (this.angle < -35.0f) {
                    this.angle = -35.0f;
                    pas = -pas;
                }
                this.angle += i * pas;
                this.axeAngle += i*this.movingAxe;
            this.arrow.setRotation(this.axeAngle + this.angle);
            } else if (this.cursorIsMoving) {
                if (yCursor < 0) {
                    yCursor = 0;
                    cursorSpeed = -cursorSpeed;
                }
                if (yCursor > jauge.getHeight()*scale-15) {
                    yCursor = jauge.getHeight()*scale-15;
                    cursorSpeed = -cursorSpeed;
                }
                yCursor += i * cursorSpeed;
            }
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (render) {
            if (cursorIsMoving) {
                this.jauge.draw(ViewController.WIDTH - this.jauge.getWidth()*scale,
                        ViewController.HEIGHT - this.jauge.getHeight()*scale,scale);
                this.cursor.draw(ViewController.WIDTH - 4.0f*this.jauge.getWidth()*scale/5.0f,
                        ViewController.HEIGHT - this.jauge.getHeight()*scale+yCursor,scale);
                    this.jauge.setAlpha(this.alpha);
                this.cursor.setAlpha(this.alpha);
            }
            this.arrow.draw(xBalle,
                    yBalle - this.arrow.getHeight()/2.0f);

        }
    }

    public void keyPressed(int key, char c) {
        if (movingArrow) {
            if (Input.KEY_RCONTROL == key) {
                this.movingArrow = false;
                this.cursorIsMoving = true;
            }
            else if (Input.KEY_UP == key) {
                this.movingAxe = -0.03f;
            }
            else if (Input.KEY_DOWN == key) {
                this.movingAxe = 0.03f;
            }
        }

    }
    public void keyReleased(int key, char c) {
        if (!this.movingArrow && this.render) {
            if (Input.KEY_RCONTROL == key) {
                this.cursorIsMoving = false;
                float vitesse = this.balle.V_INI_MAX *
                        (this.jauge.getHeight()*scale-15 - yCursor) / (this.jauge.getHeight()*scale - 15);
                this.balle.initVitesse(vitesse, this.arrow.getRotation());
                this.render = false;
            }
        }
        else if (Input.KEY_UP == key || Input.KEY_DOWN == key) {
            this.movingAxe = 0.0f;
        }


    }


    @Override
    public boolean isRendered() {
        return render;
    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return this.acceptedInput;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
