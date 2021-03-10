package golf;

import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;

import java.io.*;
import java.util.ArrayList;
import jeu.*;

public class InfoJoueur implements Serializable {

	public static final String SAVE_PATH = "save/infos_joueurs/";

    private String nomJoueur;
    private ArrayList<Balle> inventaire;

    public InfoJoueur(String nomJoueur, ArrayList<Balle> inventaire) {
        this.nomJoueur = nomJoueur;
        this.inventaire = inventaire;
        
    }

    public String getNomJoueur() {
        return this.nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public ArrayList<Balle> getInventaire() {
        return this.inventaire;
    }

    public void setInventaire(ArrayList<Balle> inventaire) {
        this.inventaire = inventaire;
    }
    
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.nomJoueur = (String) ois.readObject();
        this.inventaire = (ArrayList<Balle>) ois.readObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.nomJoueur);
        oos.writeObject(this.inventaire);
    }

    public static InfoJoueur charger(String nomFichier) throws FichierAbsentException {
        File f = new File(InfoJoueur.SAVE_PATH + nomFichier);
        if(!f.exists()){
            throw new FichierAbsentException();
        }
        InfoJoueur j = null;
        try {
            // ouverture d'un flux d'entrée depuis le fichier "joueur[nbJoueur].save"
            FileInputStream fis = new FileInputStream(InfoJoueur.SAVE_PATH + nomFichier);
            // création d'un "flux objet" avec le flux fichier
            ObjectInputStream ois= new ObjectInputStream(fis);
            try {
                j = (InfoJoueur) ois.readObject();
            } finally {
                try {
                    ois.close();
                } finally {
                    fis.close();
                }
            }
        } catch(IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        return j;
    }

    public static void sauvegarder(InfoJoueur infoJoueur) throws FichierExistantException {
        String filename = infoJoueur.getNomJoueur().replaceAll(" ", "");
        File f = new File(InfoJoueur.SAVE_PATH + filename + ".save");
        if(f.exists()){
            throw new FichierExistantException();
        }
        try {
            // ouverture d'un flux de sortie vers le fichier "nbjoueur].save"
            FileOutputStream fos = new FileOutputStream(InfoJoueur.SAVE_PATH + filename + ".save");
            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(infoJoueur);
                oos.flush();
            } finally {
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
