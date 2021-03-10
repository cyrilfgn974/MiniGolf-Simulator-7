package golf;

import exceptions.ConfigParcoursException;
import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 */
public class Parcours implements Serializable{

    private static final long serialVersionUID = 3L;

    /**
     * Chemin d'enregistrement des parcours
     */
    public static final String SAVE_PATH = "save/parcours/";
    /**
     * Nombre de terrains maximale d'un parcours
     */
    public static final int NB_MAX_TERRAINS = 9;

    private ArrayList<String> listeFichierTerrain;
    private Terrain terrainCourant;
    private byte numeroTerrainCourant;
    private String nomParcours;
    private boolean isUpdatable;

    /**
     * Constructeur d'un parcours avec un nom et aucun Terrain
     *
     * @param nomParcours Le nom du Parcours
     */
    public Parcours(String nomParcours){
        this.nomParcours = nomParcours;
        this.listeFichierTerrain = new ArrayList<>();
        this.isUpdatable = true;
    }

    public void setUpdatable(boolean updatable) {
        isUpdatable = updatable;
    }

    public boolean isUpdatable() {
        return isUpdatable;
    }

    /**
     * Obtenir le terrain chargé
     *
     * @return Le terrain actuellement chargé
     */
    public Terrain getTerrainCourant() {
        return this.terrainCourant;
    }

    /**
     * Obtenir le nom du parcours
     *
     * @return Le nom du parcours
     */
    public String getNomParcours(){
        return this.nomParcours;
    }

    /**
     * Obtenir le nombre de terrain du parcours
     *
     * @return Le nombre de terrain que le parcours possède
     */
    public int getNbTerrains(){
        return listeFichierTerrain.size();
    }

    /**
     * Ajouter un terrain au parcours
     *
     * @param terrain Le trrain à ajouter au parcours
     *
     * @throws ConfigParcoursException Si le parcours possede déjà [NB_MAX_TERRAIN]
     */
    public void ajouterTerrain(Terrain terrain) throws ConfigParcoursException {
        if(listeFichierTerrain.size() >= NB_MAX_TERRAINS){
            throw new ConfigParcoursException();
        }
        listeFichierTerrain.add(terrain.getInfos().getNomTerrain().replaceAll(" ", "") + ".save");
    }

    public void ajouterTerrain(String filename) throws ConfigParcoursException {
        if(listeFichierTerrain.size() >= NB_MAX_TERRAINS){
            throw new ConfigParcoursException();
        }
        listeFichierTerrain.add(filename);
    }

    /**
     * Charger le terrain suivant depuis son fichier
     *
     * <b>Note : </b>Le Terrain doit impérativement avoir été sauvergarder dans un fichier
     *
     * @throws FichierAbsentException Si le fichier renseigné dans la liste n'existe pas
     */
    public void chargerTerrainSuivant() throws FichierAbsentException, SlickException {
    	terrainCourant = Terrain.charger(listeFichierTerrain.get(numeroTerrainCourant - 1));
        terrainCourant.loadTexture();
        numeroTerrainCourant++;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.isUpdatable = ois.readBoolean();
        this.nomParcours = (String) ois.readObject();
        this.listeFichierTerrain = (ArrayList<String>) ois.readObject();
        this.numeroTerrainCourant = 1;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeBoolean(this.isUpdatable);
        oos.writeObject(this.nomParcours);
        oos.writeObject(this.listeFichierTerrain);
    }

    /**
     * Charger un terrain depuis un fichier
     *
     * @param nomFichier nom du fichier contenant le parcours à charger
     *
     * @throws FichierAbsentException Si le nom du fichier passé en paramètre n'existe pas
     *
     * @return Une instance du parcours désiré
     */
    public static Parcours charger(String nomFichier) throws FichierAbsentException {
        File f = new File(Parcours.SAVE_PATH + nomFichier);
        if(!f.exists()){
            throw new FichierAbsentException();
        }
        Parcours p = null;
        try {
            // ouverture d'un flux d'entrée depuis le fichier "terrain[nbTerrain].save"
            FileInputStream fis = new FileInputStream(Parcours.SAVE_PATH + nomFichier);
            // création d'un "flux objet" avec le flux fichier
            ObjectInputStream ois= new ObjectInputStream(fis);
            try {
                p = (Parcours) ois.readObject();
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
        return p;
    }

    /**
     * Sauvegarder le parcours dans un fichier
     *
     * @param parcours Le parcours a sauvegarder dans un fichier
     *
     * @throws FichierExistantException si le fichier existe deja, donc si le nom du parcours est de'ja utilisé
     */
    public static void sauvegarder(Parcours parcours) throws FichierExistantException {
        String filename = parcours.getNomParcours().replaceAll("[^a-zA-Z0-9]", "");
        File f = new File(Parcours.SAVE_PATH + filename + ".save");
        if(f.exists()){
            throw new FichierExistantException();
        }
        try {
            // ouverture d'un flux de sortie vers le fichier "terrain[nbTerrain].save"
            FileOutputStream fos = new FileOutputStream(Parcours.SAVE_PATH + filename + ".save");
            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(parcours);
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

    public static void update(Parcours p) throws FichierExistantException {
        if(p.isUpdatable) {
            String filename = p.getNomParcours().replaceAll("[^a-zA-Z0-9]", "");
            File f = new File(Parcours.SAVE_PATH + filename + ".save");
            if (f.delete()) {
                Parcours.sauvegarder(p);
            } else {
                throw new FichierExistantException();
            }
        }
    }

    public ArrayList<InfoTerrain> getInfoAllTerrain() throws FichierAbsentException {
        ArrayList<InfoTerrain> listInfos = new ArrayList<>();
        for(String s : listeFichierTerrain){
            listInfos.add(InfoTerrain.charger(s));
        }
        return listInfos;
    }

    @Override
    public boolean equals(Object obj) {
        Parcours p = (Parcours)obj;
        if(this.listeFichierTerrain.size() != p.listeFichierTerrain.size())
            return false;
        for(String s : this.listeFichierTerrain){
            if(!p.listeFichierTerrain.contains(s))
                return false;
        }
        return true;
    }
}
