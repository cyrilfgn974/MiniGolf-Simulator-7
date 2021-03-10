package jeu;

import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
import interfaces.BoutonTexte;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.*;

public class AjoutJoueur extends MouseOverArea implements ComponentListener{

    //partie selection du joueur
    private TextField pseudoJoueurField;
    private String pseudo;
    private BoutonTexte btnAdd;

    // Partie selection de la balle
    private BoutonTexte btnValider;
    private Inventaire inventaire;
    private AffichageBalle affichageBalle;
    private MouseOverArea rightArrow, leftArrow;
    private int idBalleInventaire;

    private Joueur joueur;

    private final UnicodeFont font;

    private boolean selectPlayer;

    public AjoutJoueur(GUIContext container, int x, int y, int width, int height, ComponentListener listener) throws SlickException {
        super(container, new Image("res/wood.png").getScaledCopy(width, height), x,y, listener);
        this.selectPlayer = true;
        font = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
        this.pseudoJoueurField = new TextField(container, font, this.getX() + (int)(0.2f*this.getWidth()), (int)(0.4f*this.getHeight()), (int)(0.6f*this.getWidth()), font.getLineHeight());
        this.pseudoJoueurField.setBackgroundColor(Color.white);
        this.btnAdd = new BoutonTexte(container, "Ajouter", 0,(int)(0.6f*this.getHeight()), this);
        this.btnAdd.setX(x + (float)this.getWidth()/2 - (float)this.btnAdd.getWidth()/2);
        this.idBalleInventaire = 0;
        this.affichageBalle = new AffichageBalle(null,
                this.getX() + (0.25f*this.getWidth()),
                this.getY() + (0.2f*this.getHeight()),
                (0.5f*this.getWidth()),
                (0.8f*this.getHeight()));
        this.btnValider = new BoutonTexte(container, "Valider", 0,(int)(0.7f*this.getHeight()), this);
        this.btnValider.setX(x + (float)this.getWidth()/2 - (float)this.btnAdd.getWidth()/2);
        Image right = new Image("res/rightArrow.png").getScaledCopy(0.10f);
        this.rightArrow = new MouseOverArea(container, right,
                (int)(affichageBalle.getX() + affichageBalle.getWidth() + 20),
                this.getHeight()/2 - (right.getHeight()/2), this);
        this.leftArrow = new MouseOverArea(container, right.getFlippedCopy(true, false),
                (int)(affichageBalle.getX() - right.getWidth() - 20),
                this.getHeight()/2 - (right.getHeight()/2), this);
        this.leftArrow.setMouseOverImage(right.getFlippedCopy(true, false).getScaledCopy(1.1f));
        this.rightArrow.setMouseOverImage(right.getScaledCopy(1.1f));
        this.setAcceptingInput(false);
    }

    @Override
    public void render(GUIContext container, Graphics g) {
        super.render(container,g);
        if(selectPlayer) {
            font.drawString(this.getX() + (this.getWidth() - font.getWidth("Entrez le pseudo du joueur"))/2f, 0.1f*this.getHeight(), "Entrez le pseudo du joueur");
            g.setColor(Color.white);
            this.pseudoJoueurField.render(container, g);
            this.btnAdd.render(container, g);
        }
        else{
            font.drawString(this.getX() + ( this.getWidth() - font.getWidth("Selectionnez la balle de : " + pseudo))/2f, 0.1f*this.getHeight(), "Selectionnez la balle de : " + pseudo);
            this.rightArrow.render(container, g);
            this.leftArrow.render(container,g);
            this.affichageBalle.render(container, g);
            this.btnValider.render(container,g);
        }
    }

    private void setPlayerScreen(boolean val){
        this.selectPlayer = val;
        if(selectPlayer){
            this.pseudoJoueurField.setText("");
            this.pseudoJoueurField.setAcceptingInput(true);
            this.btnAdd.setAcceptingInput(true);
            this.btnValider.setAcceptingInput(true);
        }
        else{
            this.pseudoJoueurField.setAcceptingInput(false);
            this.btnAdd.setAcceptingInput(false);
            this.btnValider.setAcceptingInput(true);
        }
    }

    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        if(btnAdd == abstractComponent){
            this.pseudo = pseudoJoueurField.getText();
            try {
                this.inventaire = Inventaire.charger(pseudo + ".save");
            } catch (FichierAbsentException e) {
                this.inventaire = new Inventaire(pseudo);
                try {
                    Inventaire.sauvegarder(this.inventaire);
                } catch (FichierExistantException fichierExistantException) {
                    throw new Error("FATAL ERROR : Fichier inventaire existant");
                }
            }
            this.affichageBalle.setToPrint(inventaire.getBalles().get(idBalleInventaire));
            setPlayerScreen(false);
        }
        else if(btnValider == abstractComponent){
            setPlayerScreen(true);
            ListeJoueur.joueur.add(new Joueur(this.pseudo, this.affichageBalle.getToPrint()));
            System.out.println(ListeJoueur.joueur.size());
            this.notifyListeners();
        }
        else if(rightArrow == abstractComponent){
            idBalleInventaire++;
            if(idBalleInventaire == inventaire.getBalles().size())
                idBalleInventaire = 0;
            affichageBalle.setToPrint(inventaire.getBalles().get(idBalleInventaire));

        }
        else if(leftArrow == abstractComponent){
            System.out.println(inventaire.getBalles().size());
            idBalleInventaire--;
            if(idBalleInventaire < 0)
                idBalleInventaire = inventaire.getBalles().size() - 1;
            affichageBalle.setToPrint(inventaire.getBalles().get(idBalleInventaire));

        }
    }
}
