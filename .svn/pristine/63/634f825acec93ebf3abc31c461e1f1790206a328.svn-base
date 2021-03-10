package interfaces;

import exceptions.FichierAbsentException;
import exceptions.InHoleException;
import exceptions.InWaterException;
import exceptions.OutOfBoundsException;
import jeu.*;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Partie extends BasicGameState implements ComponentListener{

    private int stateID = 5;
    private static Arbitre arbitre;
    private boolean isEntered;
    private boolean isBegining;
    private boolean renderIW;
    private boolean renderOOB;
    private int time;
    private final int delai = 1500;
    private Image oobPancarte;
    private Image inWaterPancarte;
    private boolean inHole;
    private boolean renderCarte;
    private BoutonTexte boutonSuivant;
    private BoutonTexte boutonTerminer;
    private BoutonTexte boutonMenu;
    private float xBouton;
    private float yBouton;
    private float widthBouton;
    private float heightBouton;
    private StateBasedGame sbg;
    private boolean renderMenu;
    private UnicodeFont font ;
    private float widthBordure = 40.0f;
    private Bandeau bandeau;

    public Partie()  {
        this.isEntered = false;
    }

    public static void setArbitre(Arbitre arb) {
        arbitre = arb;
    }


    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        this.isEntered = true;
        init(container, game) ;
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
       this.isEntered = false;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.font = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        this.sbg = stateBasedGame;
        this.xBouton = CarteScoreGraphic.xCarte + CarteScoreGraphic.widthCarte - 30 - (this.font.getWidth("Terrain Suivant")+this.widthBordure)/2.0f;
        this.yBouton = CarteScoreGraphic.yCarte + CarteScoreGraphic.heightCarte - 30;
        this.widthBouton = ViewController.WIDTH * 0.1f;
        this.heightBouton = ViewController.HEIGHT * 0.1f;
        this.inWaterPancarte = new Image("./res/Penalite/DLE.png");
        this.oobPancarte = new Image("./res/Penalite/HL.png");
        boutonSuivant = new BoutonTexte(gameContainer, "Terrain Suivant",(int) xBouton, (int) yBouton, this.font.getWidth("Terrain Suivant")+this.widthBordure , heightBouton, this);
        boutonTerminer = new BoutonTexte(gameContainer, "Terminer",(int) xBouton, (int) yBouton, widthBouton, heightBouton, this);
        boutonMenu = new BoutonTexte(gameContainer, "Menu",0, 0, widthBouton, heightBouton, this);
        if (this.isEntered) {
            this.renderIW = false;
            this.renderOOB = false;
            this.renderCarte = false;
            this.renderMenu = false;
            this.inHole = false;
            this.bandeau = new Bandeau();
            try {
                arbitre.init();
            } catch (FichierAbsentException e) {
                e.printStackTrace();
            }
            for (Joueur joueur : arbitre.getJoueurs()) {
                joueur.setBalle(new BalleGraphic(joueur.getBalle()));
                ((BalleGraphic) joueur.getBalle()).init(gameContainer);
                joueur.setViseur(new ViseurGraphic(joueur.getViseur()));
                ((ViseurGraphic) joueur.getViseur()).init(gameContainer);
            }
            try {
                arbitre.setCarteScores(new CarteScoreGraphic(arbitre.getCarteScore()));
                ((CarteScoreGraphic) arbitre.getCarteScore()).init(gameContainer);
            } catch (FichierAbsentException e) {
                e.printStackTrace();
            }
            this.bandeau.init(gameContainer);
        }
        this.isBegining = true;
        gameContainer.getInput().clearKeyPressedRecord();



    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        // Afficher le terrain courant
        arbitre.getTerrainCourant().drawMap(0, 0, gameContainer.getWidth(), gameContainer.getHeight(), 0);

        // Afficher les balles
        if(arbitre.getNbJoueurEnJeu()>0) {
            int i = 0;
            while (i < arbitre.getNbJoueurEnJeu()) {
                ((BalleGraphic) arbitre.getJoueurs().get(arbitre.getOrdre()[i]).getBalle()).render(gameContainer, graphics);
                i++;
            }
        } else if (arbitre.atDepart()) {
            ((BalleGraphic) arbitre.getJoueurCourant().getBalle()).render(gameContainer, graphics);
        }
        // Si le joueur est en train de viser afficher le viseur
        if (arbitre.getJoueurCourant().isAiming()) {
            ((ViseurGraphic) arbitre.getJoueurCourant().getViseur()).render(gameContainer,graphics);
        }

        // Si le joueur est dans l'eau, afficher la pancarte dans l'eau
        if (this.renderIW) {
            this.inWaterPancarte.draw((gameContainer.getHeight()-this.inWaterPancarte.getHeight())/2.0f,
                    (gameContainer.getWidth()-this.inWaterPancarte.getWidth())/2.0f);
        }
        // Si e joueur est hors limite, afficher la pancarte hors limite
        if (this.renderOOB) {
            this.oobPancarte.draw((gameContainer.getWidth()-this.inWaterPancarte.getWidth())/2.0f,
                    (gameContainer.getHeight()-this.inWaterPancarte.getHeight())/2.0f);
        }
        // Afficher bouton retour menu quand nécessaire
        if (this.renderMenu) {
            boutonMenu.render(gameContainer,graphics);
        }
        // Afficher la carte de score
        if (renderCarte) {
            ((CarteScoreGraphic) arbitre.getCarteScore()).render(gameContainer, graphics);
            if (arbitre.getNumTerrainCourant() < arbitre.getNbTerrain()) {
                boutonSuivant.render(gameContainer, graphics);
            } else {
                boutonTerminer.render(gameContainer, graphics);
            }
            // Afficher le bandeau d'information
        } else {
                this.bandeau.render(gameContainer, graphics);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        // Temporiser si la balle est hors limite ou dans l'eau
        if (this.renderOOB || this.renderIW)
            if (this.time < this.delai) {
                this.time += i;
            }
            else {
                this.time = 0;
                this.renderOOB = false;
                this.renderIW = false;
            }

        else if (arbitre.isTrouEnded()) {
            // Si on est le premier coup du premier joueur
            if (arbitre.isTrouEnded() && arbitre.atDepart() && arbitre.getNumTerrainCourant() == 0) {
                try {
                    arbitre.changerTerrain();
                } catch (FichierAbsentException e) {
                    e.printStackTrace();
                }
                this.bandeau.setBandeau(arbitre);
            // On affiche la carte de score entre deux trous
            } else if (!arbitre.atDepart()) {
                renderCarte = true;
            }
        }
        // Si la balle est en mouvement , on calcule la prochaine position
        if (arbitre.getJoueurCourant().isMoving()) {
            try {
                arbitre.getJoueurCourant().toNextStep(i);
            // Traitement des positions particulières
            } catch (OutOfBoundsException e) {
                this.renderOOB = true;
                this.arbitre.replacerJoueur();
            } catch (InWaterException e) {
                this.renderIW = true;
                this.arbitre.replacerJoueur();
            } catch (InHoleException e) {
                this.inHole = true;
                this.arbitre.inHole();
            }
            this.bandeau.update(gameContainer);
        // Si le joueur est en train de viser , on met à jour le viseur
        } else if (arbitre.getJoueurCourant().isAiming()) {
                ((ViseurGraphic) arbitre.getJoueurCourant().getViseur()).update(gameContainer, i);
                //System.out.println(arbitre.getJoueurCourant().getNom());
        // On fait jouer le premier joueur
        } else if (this.arbitre.atDepart() && arbitre.getNbJoueurEnJeu() == 0) {
            arbitre.addJoueurDepart();
            gameContainer.getInput().addKeyListener(((ViseurGraphic) arbitre.getJoueurCourant().getViseur()));
            arbitre.getJoueurCourant().viser();

        // On fait jouer les joueurs
        } else if (!renderCarte) {
            arbitre.updateScore(); // Ajout du coup
            arbitre.updateOrdreJeu(); // Mise a jour de l'ordre de jeu
            gameContainer.getInput().removeKeyListener(((ViseurGraphic) arbitre.getJoueurCourant().getViseur()));
            arbitre.changerJoueur(); // On change de joueur selon le nouvel ordre
            this.bandeau.setBandeau(arbitre); // on met à jour les paramètres du bandeau
            // Si le trou n'est pas fini , on fait jouer le porchain joueur
            if (!arbitre.isTrouEnded()) {
                gameContainer.getInput().addKeyListener(((ViseurGraphic) arbitre.getJoueurCourant().getViseur()));
                arbitre.getJoueurCourant().viser();
            }
        }
    }


    @Override
    public void componentActivated(AbstractComponent source) {
        if (boutonMenu.equals(source) || boutonTerminer.equals(source)) {
            renderCarte = false;
            sbg.enterState(0);
        } else if (boutonSuivant.equals(source)) {
            renderCarte = false;
            try {
                arbitre.changerTerrain();
            } catch (FichierAbsentException | SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            renderMenu = !renderMenu ;
        }
    }

}
