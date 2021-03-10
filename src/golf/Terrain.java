package golf;

import exceptions.ConfigTerrainException;
import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Terrain implements Serializable, Mapable {

    private static final long serialVersionUID = 2L;

    public static final int NB_COLONNE_TERRAIN = 80;
    public static final int NB_LIGNE_TERRAIN = 44;

    public static final String SAVE_PATH = "save/terrains/";

    private ArrayList<Surface> surfaces;
    private Surface[][] terrain;
    private SurfaceDepart surfaceDepart;
    private SurfaceTrou surfaceTrou;
    private InfoTerrain infos;


    public Terrain(String nomTerrain, int par, SurfaceDepart sd, SurfaceTrou st) {
        this.infos = new InfoTerrain(nomTerrain, par);
        this.surfaceDepart = sd;
        this.surfaceTrou = st;
        this.surfaces = new ArrayList<>() ;
        this.terrain = new Surface[NB_COLONNE_TERRAIN][NB_LIGNE_TERRAIN];
        this.remplirTerrain();
    }

    public Terrain(String nomTerrain, int par){
        this(nomTerrain, par, new SurfaceDepart(), new SurfaceTrou());
    }

    public Terrain(){
        this("noName", 0);
    }

    public Surface getSurface(int x, int y){
        return terrain[x][y];
    }

    public InfoTerrain getInfos() {
        return infos;
    }

    public void setSurface(int x, int y, Surface surface) {
        // Si la position est celle du trou ou du départ alors je supprimme ce dernier
        if(terrain[x][y] != null){
            if (terrain[x][y].getType() == TypeSurface.DEPART) {
                surfaceDepart.deletePosition();
            } else if (terrain[x][y].getType() == TypeSurface.TROU) {
                surfaceTrou.deletePosition();
            }
        }

        // Si je pose un Trou ou un Depart, je retire l'ancien et actualise la position du nouveau
        if(surface.getType() == TypeSurface.DEPART){
            if (surfaceDepart.getPosition() != null) {
                int posX = (int) surfaceDepart.getPosition().getX();
                int posY = (int) surfaceDepart.getPosition().getY();
                terrain[posX][posY] = surfaces.get(0);
            }
            surfaceDepart.setPosition(x,y);
        }
        else if(surface.getType() == TypeSurface.TROU) {
            if (surfaceTrou.getPosition() != null) {
                int posX = (int) surfaceTrou.getPosition().getX();
                int posY = (int) surfaceTrou.getPosition().getY();
                terrain[posX][posY] = surfaces.get(0);
            }
            surfaceTrou.setPosition(x,y);
        }

        // Si ma surface et deja dans ma Liste alors je ne l'ajoute pas
        if(!surfaces.contains(surface)){
            surfaces.add(surface);
        }

        terrain[x][y] = surface;
    }


    public boolean estValide(){
        return surfaceTrou.getPosition() != null
                && surfaceDepart.getPosition() != null;
    }

    public boolean haveTrou(){
        return surfaceTrou.getPosition() != null;
    }

    public Point2D getTrou(){
        if(this.surfaceTrou == null)
            return null;
        return this.surfaceTrou.getPosition();
    }

    public boolean haveDepart(){
        return surfaceDepart.getPosition() != null;
    }

    public Point2D getDepart(){
        if(this.surfaceDepart == null)
            return null;
        return this.surfaceDepart.getPosition();
    }

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {    	
    	this.infos = (InfoTerrain) ois.readObject();
    	this.surfaceDepart = (SurfaceDepart)ois.readObject();
    	this.surfaceTrou = (SurfaceTrou) ois.readObject();
        this.surfaces = (ArrayList<Surface>) ois.readObject();
        this.terrain = new Surface[NB_COLONNE_TERRAIN][NB_LIGNE_TERRAIN];
        int x = 0;
        int y = 0;
        while(x < NB_COLONNE_TERRAIN){
            while(y < NB_LIGNE_TERRAIN){
                terrain[x][y] = surfaces.get(ois.readInt());
                y++;
            }
            y = 0;
            x++;
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(infos);
        oos.writeObject(surfaceDepart);
        oos.writeObject(surfaceTrou);
        oos.writeObject(surfaces);
        int x = 0;
        int y = 0;
        while(x < NB_COLONNE_TERRAIN){
            while(y < NB_LIGNE_TERRAIN){
                oos.writeInt(this.surfaces.indexOf(terrain[x][y]));
                y++;
            }
            y = 0;
            x++;
        }
    }

    public static Terrain charger(String nomFichier) throws FichierAbsentException {
        File f = new File(Terrain.SAVE_PATH + nomFichier);
        if(!f.exists()){
            throw new FichierAbsentException();
        }
        Terrain t = null;
        try {
            // ouverture d'un flux d'entrée depuis le fichier "terrain[nbTerrain].save" 
        	FileInputStream fis = new FileInputStream(Terrain.SAVE_PATH + nomFichier);
            // création d'un "flux objet" avec le flux fichier
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                t = (Terrain) ois.readObject();
                t.setInfos(InfoTerrain.charger(nomFichier));
                //System.out.println(t.surfaces);
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

    private void setInfos(InfoTerrain infos){
        this.infos = infos;
    }

    public static void sauvegarder(Terrain terrain) throws ConfigTerrainException, FichierExistantException {
        if(!terrain.estValide()){
            throw new ConfigTerrainException();
        }
        //System.out.println(terrain.surfaces);
        String filename = terrain.infos.getNomTerrain().replaceAll("[^a-zA-Z0-9]", "");
        File f = new File(Terrain.SAVE_PATH + filename + ".save");
        if(f.exists()){
            throw new FichierExistantException();
        }
        try {
            // ouverture d'un flux de sortie vers le fichier "terrain[nbTerrain].save"
            FileOutputStream fos = new FileOutputStream(Terrain.SAVE_PATH + filename + ".save");
            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(terrain);
                oos.flush();
                InfoTerrain.sauvegarder(terrain.getInfos());
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

    @Override
    public String toString() {
        int x = 0;
        int y = 0;
        StringBuilder sb = new StringBuilder();
        while(y < NB_LIGNE_TERRAIN){
            while (x < NB_COLONNE_TERRAIN){
                sb.append(terrain[x][y].getClass().getName());
                sb.append("\t");
                x++;
            }
            sb.append("\n");
            x=0;
            y++;
        }
        return sb.toString();
    }

    public void loadTexture() throws SlickException {
        for(Surface s : surfaces){
            s.loadTexture();
        }
    }

    private void remplirTerrain() {
        SurfaceGreen sg = new SurfaceGreen();
        for(int i = 0; i< Terrain.NB_COLONNE_TERRAIN; i++){
            for(int j = 0; j< Terrain.NB_LIGNE_TERRAIN; j++){
                this.setSurface(i,j, sg);
            }
        }
    }

    @Override
    public void drawMap(float x, float y, float width, float height, float offset) {
        float xSize = width/Terrain.NB_COLONNE_TERRAIN;
        float ySize = height/Terrain.NB_LIGNE_TERRAIN;
        for(int i = 0; i< Terrain.NB_COLONNE_TERRAIN; i++){
            for(int j = 0; j< Terrain.NB_LIGNE_TERRAIN; j++){
                terrain[i][j].drawTexture((i*xSize) + x + offset , (j*ySize) + y + offset, xSize - offset, ySize - offset);
            }
        }
    }
}
