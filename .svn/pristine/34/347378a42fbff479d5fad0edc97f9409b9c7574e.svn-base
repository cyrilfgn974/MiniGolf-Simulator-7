package interfaces;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//import controller.MainController;
import interfaces.ViewController;


public class MainMenuState extends BasicGameState implements ComponentListener {
	//private MainController main;

	private StateBasedGame sbg;

	private Image background = null;
	private MouseOverArea PlayClassique = null;
	private MouseOverArea PlayCoop = null;
	private MouseOverArea PlayEditeur = null;
	private MouseOverArea PlayBoutique = null;
	private MouseOverArea SonButton = null;
	private Image SonOff = null;
	private Image SonOn = null;

	private int MusicStat;
	private Music music;
	private boolean isSoundOn  = true;

	public MainMenuState() {
		//main = MainController.getInstance();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		System.out.println("MAIN  MENU STATE");
	}

	@Override
	public int getID() {
		return ViewController.MAINMENUSTATE;
	}

	@Override
	public void init(GameContainer Container, StateBasedGame sbg)
			throws SlickException {
		background = new Image("./res/back.png");
		PlayClassique = new MouseOverArea(Container, new Image("res/playclassique.png"), 489, 300, this);
		PlayCoop = new MouseOverArea(Container, new Image("res/playcoop.png"),489, 400, this);
		PlayEditeur = new MouseOverArea(Container, new Image("res/playediteur.png"), 489, 500, this);
		PlayBoutique = new MouseOverArea(Container, new Image("res/playboutique.png"), 489, 600, this);
		SonOn = new Image("res/sonon.png");
		SonOff = new Image("res/sonoff.png");
		SonButton = new MouseOverArea(Container, SonOn, 5,654, this);
		music = new Music("res/soundtrack.ogg");
		Image playClassiqueOver = new Image("res/playclassiquetouch.png");
		Image playCoopOver = new Image("res/playcooptouch.png");
		Image playEditeurOver = new Image("res/playediteurtouch.png");
		Image playBoutiqueOver = new Image("res/playboutiquetouch.png");
		PlayBoutique.setMouseOverImage(playBoutiqueOver);
		PlayCoop.setMouseOverImage(playCoopOver);
		PlayClassique.setMouseOverImage(playClassiqueOver);
		PlayEditeur.setMouseOverImage(playEditeurOver);
		music.loop();
		this.sbg = sbg;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0,0,arg0.getWidth(), arg0.getHeight());

		PlayEditeur.render(arg0, arg2);
		PlayClassique.render(arg0, arg2);
		PlayBoutique.render(arg0, arg2);
		PlayCoop.render(arg0,arg2);

		SonButton.render(arg0, arg2);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	}

	@Override
	public void componentActivated(AbstractComponent abstractComponent) {
		if(abstractComponent == PlayClassique){
			sbg.enterState(ViewController.CLASSIQUESTATE);
		}
		else if(abstractComponent == PlayCoop){
			//sbg.enterState();
		}
		else if(abstractComponent == PlayEditeur){
			sbg.enterState(ViewController.CONNEXIONEDITEURSTATE);
		}
		else if(abstractComponent == PlayBoutique){
			//sbg.enterState;
		}
		else if(abstractComponent == SonButton){
			isSoundOn = !isSoundOn;
			if(isSoundOn){
				music.play();
				SonButton.setNormalImage(SonOn);
				SonButton.setMouseDownImage(SonOn);
				SonButton.setMouseOverImage(SonOn);
			}
			else{
				music.pause();
				SonButton.setNormalImage(SonOff);
				SonButton.setMouseDownImage(SonOff);
				SonButton.setMouseOverImage(SonOff);
			}
		}
	}
}
