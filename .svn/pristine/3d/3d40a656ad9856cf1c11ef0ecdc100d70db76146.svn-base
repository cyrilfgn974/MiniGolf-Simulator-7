package jeu;

import interfaces.BoutonTexte;
import interfaces.SelectionParcours;
import interfaces.ViewController;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class SelectionJoueur extends BasicGameState implements ComponentListener {

    public static final int ID = 1;

    private StateBasedGame sbg;

    private ListeJoueur lj;
    private BoutonTexte btnValider;
    private BoutonTexte btnQuitter;
    private Image background;
    private AjoutJoueur addJoueur;
    private String pseudo;
    private Balle balleJoueur;

    public SelectionJoueur() { }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(abstractComponent == btnValider && ListeJoueur.joueur.size()>0){
            sbg.enterState(SelectionParcours.ID);
        }
        if(abstractComponent == btnQuitter){
            sbg.enterState(ViewController.MAINMENUSTATE);
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        ListeJoueur.joueur.clear();
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.lj = new ListeJoueur(gameContainer, 0,0,0.3f*gameContainer.getWidth(), gameContainer.getHeight());
        this.background = new Image("res/back2.png");
        this.btnValider = new BoutonTexte(gameContainer, "Valider", 1000, gameContainer.getHeight() - 65, this);
        this.btnQuitter = new BoutonTexte(gameContainer, "Quitter", 15, gameContainer.getHeight() - 65, this);
        this.addJoueur = new AjoutJoueur(gameContainer,
                (int)(0.3f*gameContainer.getWidth()),
                0,
                (int)(0.7f*gameContainer.getWidth()),
                (int)(0.8f*gameContainer.getHeight()),
                this);
        this.sbg = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0);
        lj.render(gameContainer, graphics);
        this.btnValider.render(gameContainer, graphics);
        this.btnQuitter.render(gameContainer, graphics);
        this.addJoueur.render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
