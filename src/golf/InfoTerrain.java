package golf;

import exceptions.ConfigTerrainException;
import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;

import java.io.*;

public class InfoTerrain implements Serializable {

    public static final String SAVE_PATH = "save/infos_terrains/";

    private static final long serialVersionUID = 3L;


    private String nomTerrain;
    private int par;

    public InfoTerrain(String nomTerrain, int par) {
        this.nomTerrain = nomTerrain;
        this.par = par;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.nomTerrain = (String) ois.readObject();
        this.par = ois.readInt();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.nomTerrain);
        oos.writeInt(this.par);
    }

    public static InfoTerrain charger(String nomFichier) throws FichierAbsentException {
        File f = new File(InfoTerrain.SAVE_PATH + nomFichier);
        //System.out.println(InfoTerrain.SAVE_PATH + nomFichier);
        if(!f.exists()){
            throw new FichierAbsentException();
        }
        InfoTerrain t = null;
        try {
            // ouverture d'un flux d'entrée depuis le fichier "terrain[nbTerrain].save"
            FileInputStream fis = new FileInputStream(InfoTerrain.SAVE_PATH + nomFichier);
            // création d'un "flux objet" avec le flux fichier
            ObjectInputStream ois= new ObjectInputStream(fis);
            try {
                t = (InfoTerrain) ois.readObject();
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
        return t;
    }

    public static void sauvegarder(InfoTerrain infoTerrain) throws ConfigTerrainException, FichierExistantException {
        String filename = infoTerrain.getNomTerrain().replaceAll(" ", "");
        File f = new File(InfoTerrain.SAVE_PATH + filename + ".save");
        if(f.exists()){
            throw new FichierExistantException();
        }
        try {
            // ouverture d'un flux de sortie vers le fichier "terrain[nbTerrain].save"
            FileOutputStream fos = new FileOutputStream(InfoTerrain.SAVE_PATH + filename + ".save");
            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(infoTerrain);
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
