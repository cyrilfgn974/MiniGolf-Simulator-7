package interfaces;

import editeur.Editeur;
import exceptions.ConfigParcoursException;
import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
import golf.InfoTerrain;
import golf.Parcours;
import jeu.Inventaire;
import jeu.Joueur;
import jeu.ListeJoueur;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;

public class AjoutTerrain extends BasicGameState implements ComponentListener {

    private Image background, panneau;

    private MouseOverArea rightArrow, leftArrow;
    private HashMap<String, ArrayList<String>> parcours;
    private ArrayList<String> nomParcours;

    private TextField nouveauParcours;
    private BoutonTexte boutonCreer;
    private BoutonTexte boutonAdd;

    private int idParcours;
    private StateBasedGame sbg;

    private Rectangle separation;

    private UnicodeFont font;
    private UnicodeFont font2;

    public AjoutTerrain() throws SlickException {

    }

    public Parcours getCurrentParcours(){
        try {
            return Parcours.charger(nomParcours.get(idParcours).replaceAll("[^a-zA-Z0-9]", "") + ".save");
        } catch (FichierAbsentException e) {
            throw new Error("PARCOURS INEXISTANT : " + nomParcours.get(idParcours) + ".save");
        }
    }


    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(leftArrow == abstractComponent){
            idParcours--;
            if(idParcours < 0){
                idParcours = nomParcours.size() - 1;
            }
        }
        else if(rightArrow == abstractComponent){
            idParcours++;
            if(idParcours >= nomParcours.size()){
                idParcours = 0;
            }
        }
        else if(boutonCreer == abstractComponent){
            Parcours p = new Parcours(nouveauParcours.getText());
            try {
                p.ajouterTerrain(Editeur.fileNameTerrain + ".save");
                ConnexionEditeur.inventaireEditeur.addParcours(p.getNomParcours().replaceAll("[^a-zA-Z0-9]", ""));
                Inventaire.update(ConnexionEditeur.inventaireEditeur);
                Parcours.sauvegarder(p);
                sbg.enterState(ViewController.MAINMENUSTATE);
            } catch (ConfigParcoursException | FichierExistantException e) {
                e.printStackTrace();
            }
        }
        else if(boutonAdd == abstractComponent && nomParcours.size() > 0){
            try {
                Parcours p = this.getCurrentParcours();
                p.ajouterTerrain(Editeur.fileNameTerrain + ".save");
                Parcours.update(p);
                sbg.enterState(ViewController.MAINMENUSTATE);
            } catch (FichierExistantException e) {
                throw new Error("FICHIER EXISTANT");
            } catch (ConfigParcoursException e){
                throw new Error("CONFIG PARCOURS");
            }
        }

    }

    @Override
    public int getID() {
        return ViewController.AJOUTTERRAINEDITEUR;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        this.parcours = new HashMap<>();
        this.nomParcours = new ArrayList<>();
        try {
            for (String s : ConnexionEditeur.inventaireEditeur.getParcours()){
                Parcours p = Parcours.charger(s + ".save");
                if(p.getNbTerrains() < 9 && p.isUpdatable()) {
                    if (!nomParcours.contains(p.getNomParcours()))
                        nomParcours.add(p.getNomParcours());
                    ArrayList<String> nomTerrain = new ArrayList<>();
                    for (InfoTerrain it : p.getInfoAllTerrain()) {
                        nomTerrain.add(it.getNomTerrain());
                    }
                    parcours.put(p.getNomParcours(), nomTerrain);
                }
            }
        } catch (FichierAbsentException e) {
            throw new Error("JOUEUR SANS INVENTAIRE");
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        this.sbg = stateBasedGame;
        this.background = new Image("res/back2.png");
        this.panneau = new Image("res/wood.png");
        font = new UnicodeFont( "res/font/cour.ttf", 35, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
        font2 = new UnicodeFont( "res/font/cour.ttf", 25, true, false);
        font2.getEffects().add(new ColorEffect(java.awt.Color.black));
        font2.addAsciiGlyphs();
        font2.loadGlyphs();
        Image right = new Image("res/rightArrow.png").getScaledCopy(0.15f);
        this.rightArrow = new MouseOverArea(container, right,
                (int)((0.85f*container.getWidth()) - right.getWidth()),
                container.getHeight()/3 - (right.getHeight()/2), this);
        this.leftArrow = new MouseOverArea(container, right.getFlippedCopy(true, false),
                (int)(0.15f*container.getWidth()),
                container.getHeight()/3 - (right.getHeight()/2), this);
        this.leftArrow.setMouseOverImage(right.getFlippedCopy(true, false).getScaledCopy(1.1f));
        this.rightArrow.setMouseOverImage(right.getScaledCopy(1.1f));

        this.nouveauParcours = new TextField(container, font2, (int)(0.2f*container.getWidth()), (int)(0.75f*container.getHeight()), (int)(0.6f*container.getWidth()), font2.getLineHeight());
        this.boutonCreer = new BoutonTexte(container, "Creer", 0, (int)(nouveauParcours.getY() + nouveauParcours.getHeight() + 30), this);
        this.boutonCreer.setX(0.5f*(container.getWidth() - this.boutonCreer.getWidth()));
        this.boutonAdd = new BoutonTexte(container, "Ajouter", 0, (int)(0.6f*container.getHeight()), this);
        this.boutonAdd.setX(0.5f*(container.getWidth() - this.boutonAdd.getWidth()));
        this.idParcours = 0;
        this.separation = new Rectangle((int)(0.2f*container.getWidth()), (int)(0.69*container.getHeight()), (int)(0.6f*container.getWidth()), 3);
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        this.background.draw(0,0,container.getWidth(), container.getHeight());
        this.panneau.draw(0,0,container.getWidth(), container.getHeight());
        this.leftArrow.render(container, g);
        this.rightArrow.render(container,g);
        if(nomParcours.size() > 0) {
            font.drawString((container.getWidth() - font.getWidth(nomParcours.get(idParcours))) / 2f, 0.15f * container.getHeight(), nomParcours.get(idParcours));
            for (int i = 0; i < parcours.get(nomParcours.get(idParcours)).size(); i++) {
                font2.drawString((container.getWidth() - font.getWidth(i + " - " + nomParcours.get(idParcours))) / 2f,
                        0.15f * container.getHeight() + 2 * font.getLineHeight() + (i * font2.getLineHeight()),
                        i + " - " + parcours.get(nomParcours.get(idParcours)).get(i));
            }
        }
        g.setColor(Color.black);
        g.fill(separation);
        nouveauParcours.render(container,g);
        boutonCreer.render(container, g);
        boutonAdd.render(container,g);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
