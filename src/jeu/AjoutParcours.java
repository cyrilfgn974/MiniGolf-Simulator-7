package jeu;

import exceptions.FichierAbsentException;
import golf.InfoTerrain;
import golf.Parcours;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.*;

import java.util.ArrayList;
import java.util.HashMap;

public class AjoutParcours extends MouseOverArea implements ComponentListener{

    private MouseOverArea rightArrow, leftArrow;
    private HashMap<String, ArrayList<String>> parcours;
    private ArrayList<String> nomParcours;

    private int idParcours;


    private final UnicodeFont font;
    private final UnicodeFont font2;

    public AjoutParcours(GUIContext container, int x, int y, int width, int height, ComponentListener listener) throws SlickException {
        super(container, new Image("res/wood.png").getScaledCopy(width, height), x,y, listener);
        font = new UnicodeFont( "res/font/cour.ttf", 40, true, false);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
        font2 = new UnicodeFont( "res/font/cour.ttf", 30, true, false);
        font2.getEffects().add(new ColorEffect(java.awt.Color.black));
        font2.addAsciiGlyphs();
        font2.loadGlyphs();
        Image right = new Image("res/rightArrow.png").getScaledCopy(0.15f);
        this.rightArrow = new MouseOverArea(container, right,
                (int)((0.85f*this.getWidth()) - right.getWidth()),
                this.getHeight()/2 - (right.getHeight()/2), this);
        this.leftArrow = new MouseOverArea(container, right.getFlippedCopy(true, false),
                (int)(0.15f*this.getWidth()),
                this.getHeight()/2 - (right.getHeight()/2), this);
        this.leftArrow.setMouseOverImage(right.getFlippedCopy(true, false).getScaledCopy(1.1f));
        this.rightArrow.setMouseOverImage(right.getScaledCopy(1.1f));
        this.setAcceptingInput(false);
        this.parcours = new HashMap<>();
        this.nomParcours = new ArrayList<>();
        for(Joueur j : ListeJoueur.joueur){
            try {
                System.out.println(j.getNom());
                for (String s : Inventaire.charger(j.getNom() + ".save").getParcours()){
                    Parcours p = Parcours.charger(s + ".save");
                    System.out.println(s);
                    if(!nomParcours.contains(p.getNomParcours()))
                        nomParcours.add(p.getNomParcours());
                    ArrayList<String> nomTerrain = new ArrayList<>();
                    for(InfoTerrain it : p.getInfoAllTerrain()){
                        nomTerrain.add(it.getNomTerrain());
                        System.out.println(it.getNomTerrain());
                    }
                    parcours.put(p.getNomParcours(), nomTerrain);
                }
            } catch (FichierAbsentException e) {
                throw new Error("JOUEUR SANS INVENTAIRE");
            }
        }
        this.setAcceptingInput(false);
        this.idParcours = 0;
    }

    public Parcours getCurrentParcours(){
        try {
            return Parcours.charger(nomParcours.get(idParcours).replaceAll("[^a-zA-Z0-9]", "") + ".save");
        } catch (FichierAbsentException e) {
            throw new Error("PARCOURS INEXISTANT : " + nomParcours.get(idParcours) + ".save");
        }
    }

    @Override
    public void render(GUIContext container, Graphics g) {
        super.render(container,g);
        leftArrow.render(container, g);
        rightArrow.render(container,g);
        font.drawString((this.getWidth() - font.getWidth(nomParcours.get(idParcours)))/2f, 0.15f*this.getHeight(), nomParcours.get(idParcours));
        for(int i = 0; i < parcours.get(nomParcours.get(idParcours)).size(); i++){
            font2.drawString((this.getWidth() - font.getWidth(i + " - " + nomParcours.get(idParcours)))/2f,
                    0.15f*this.getHeight() + 2*font.getLineHeight() + (i*font2.getLineHeight()),
                    i + " - " +parcours.get(nomParcours.get(idParcours)).get(i));
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
    }
}
