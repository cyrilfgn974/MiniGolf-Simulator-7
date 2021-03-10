package jeu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
//import jeu.*;


public class Inventaire implements Serializable {
	public static final String SAVE_PATH = "save/inventaires/";
	
	private ArrayList<String> parcours;
	private ArrayList<Balle> balles;
	private String nom;
	
	public Inventaire(String nomInventaire) {
	    this.nom = nomInventaire;
	    this.balles = new ArrayList<Balle>();
	    this.parcours = new ArrayList<String>();
	    this.balles.add(new Balle());
	    this.balles.add(new BalleSandProof(0,0));
	    this.balles.add(new BalleWaterSurf(0,0));
        this.parcours.add("MonBeauParcours");
        this.parcours.add("ParcoursdeFrance");

	}
	
	public String getNom() {
		return this.nom;
	}
	
	public ArrayList<String> getParcours() {
		return this.parcours;
	}

	public ArrayList<Balle> getBalles() {
		return this.balles;
	}
	
	public void addParcours(String parcours) {
		this.parcours.add(parcours);
	}
	
	public void addBalles(Balle balle) {
		this.balles.add(balle);
		//Inventaire.update(this);
	}
	
	
    private void writeObject(ObjectOutputStream oos) throws IOException {
	    oos.writeObject(this.nom);
    	oos.writeObject(this.parcours);
        oos.writeObject(this.balles);
    }

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
	    this.nom = (String) ois.readObject();
		this.parcours = (ArrayList<String>) ois.readObject();
		this.balles   = (ArrayList<Balle>) ois.readObject();
    }
	
	
	public static void update(Inventaire inventaire) throws FichierExistantException {
        String filename = inventaire.getNom().replaceAll(" ", "");
        File f = new File(Inventaire.SAVE_PATH + filename + ".save");
        System.out.println(filename);
        if (f.delete()) {
            Inventaire.sauvegarder(inventaire);
        } else {
            throw new FichierExistantException();
        }
    }

    //Pouvoir sauvegarder un joueur dans un fichier
    public static void sauvegarder(Inventaire inventaire) throws FichierExistantException {
        String filename = inventaire.getNom().replaceAll(" ", "");
        File f = new File(Inventaire.SAVE_PATH + filename + ".save");
        if(f.exists()){
            throw new FichierExistantException();
        }
        try {
            // ouverture d'un flux de sortie vers le fichier "terrain[nbTerrain].save"
            FileOutputStream fos = new FileOutputStream(Inventaire.SAVE_PATH + filename + ".save");
            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(inventaire);
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
    
    public static Inventaire charger(String nomFichier) throws FichierAbsentException {
        File f = new File(Inventaire.SAVE_PATH + nomFichier);
        //System.out.println(InfoTerrain.SAVE_PATH + nomFichier);
        if(!f.exists()){
            throw new FichierAbsentException();
        }
        Inventaire i = null;
        try {
            // ouverture d'un flux d'entrée depuis le fichier "terrain[nbTerrain].save"
            FileInputStream fis = new FileInputStream(Inventaire.SAVE_PATH + nomFichier);
            // création d'un "flux objet" avec le flux fichier
            ObjectInputStream ois= new ObjectInputStream(fis);
            try {
                i = (Inventaire) ois.readObject();
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
        return i;
    }
}
