package interfaces;

import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
import jeu.Inventaire;
import jeu.Joueur;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class ConnexionEditeur extends BasicGameState implements ComponentListener {

    private Image background, panneau;

    private TextField pseudo;
    private BoutonTexte valider;

    private UnicodeFont font;
    private UnicodeFont font2;

    public static Inventaire inventaireEditeur;

    private StateBasedGame sbg;

    public ConnexionEditeur(){
    }

    @Override
    public int getID() {
        return ViewController.CONNEXIONEDITEURSTATE;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        this.pseudo.setText("");
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.background = new Image("res/back2.png");
        this.panneau = new Image("res/wood.png");

        font = new UnicodeFont( "res/font/cour.ttf", 40, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
        font2 = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font2.getEffects().add(new ColorEffect(java.awt.Color.black));
        font2.addAsciiGlyphs();
        font2.loadGlyphs();
        this.pseudo = new TextField(gameContainer, font2, (int)(0.2f*gameContainer.getWidth()),
                (int)(0.5f*gameContainer.getHeight()),
                (int)(0.6f*gameContainer.getWidth()), font2.getLineHeight());
        this.pseudo.setBackgroundColor(Color.white);
        this.valider = new BoutonTexte(gameContainer, "Select/Creer", 0, (int)(0.7f*gameContainer.getHeight()), this);
        this.valider.setX(0.5f*(gameContainer.getWidth() - valider.getWidth()));
        this.sbg = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        this.background.draw(0,0,gameContainer.getWidth(), gameContainer.getHeight());
        this.panneau.draw(0,0,gameContainer.getWidth(), gameContainer.getHeight());
        this.font.drawString((gameContainer.getWidth() - font.getWidth("Entrez le pseudo du joueur"))/2f, 0.2f*gameContainer.getHeight(), "Entrez le pseudo du joueur");
        graphics.setColor(Color.white);
        this.pseudo.render(gameContainer, graphics);
        this.valider.render(gameContainer,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }


    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(valider == abstractComponent){
            try {
                inventaireEditeur = Inventaire.charger(pseudo.getText() + ".save");
            } catch (FichierAbsentException e) {
                inventaireEditeur = new Inventaire(pseudo.getText());
                try {
                    Inventaire.sauvegarder(inventaireEditeur);
                } catch (FichierExistantException fichierExistantException) {
                    throw new Error("INVENTAIRE EXISTANT");
                }
            }
            sbg.enterState(ViewController.EDITEURSTATE);
        }
    }
}
