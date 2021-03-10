package interfaces;

import jeu.AjoutParcours;
import jeu.ArbitreClassique;
import jeu.ListeJoueur;
import interfaces.ViewController;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectionParcours extends BasicGameState implements ComponentListener {

    private BoutonTexte btnValider;
    private BoutonTexte btnQuitter;
    private Image background;
    private StateBasedGame sbg;

    private AjoutParcours ajoutParcours;

    public static final int ID = 2;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.sbg = stateBasedGame;
        this.background = new Image("res/back2.png");
        this.btnValider = new BoutonTexte(gameContainer, "Valider", 1000, gameContainer.getHeight() - 65, this);
        this.btnQuitter = new BoutonTexte(gameContainer, "Quitter", 15, gameContainer.getHeight() - 65, this);

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        this.ajoutParcours = new AjoutParcours(container, 0,0, container.getWidth(), (int)(0.85f*container.getHeight()), this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0);
        this.btnValider.render(gameContainer, graphics);
        this.btnQuitter.render(gameContainer, graphics);
        this.ajoutParcours.render(gameContainer, graphics);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(abstractComponent == btnValider){
            //Pour le parcours : this.ajoutParcours.getCurrentParcours();
            // Pour la liste des joueur : ListeJoueur.joueur;
            // Changer de state
            Partie.setArbitre(new ArbitreClassique(ListeJoueur.joueur,this.ajoutParcours.getCurrentParcours()));
            sbg.enterState(5);
        }
        if(abstractComponent == btnQuitter){
            sbg.enterState(ViewController.MAINMENUSTATE);
        }
    }
}
