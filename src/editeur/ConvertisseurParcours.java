package editeur;

import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
import golf.Parcours;

import java.io.File;
import java.util.Scanner;

public class ConvertisseurParcours {

    public static void main(String[] args) {
        boolean isOK = true;

        Scanner sc = new Scanner(System.in);

        while(isOK) {
            System.out.print("Entrez le nom du parcours (sans le.save) :  ");
            String nom_parcours = sc.nextLine();

            try {
                Parcours p = Parcours.charger(nom_parcours);
                p.setUpdatable(false);
                String filename = p.getNomParcours().replaceAll("[^a-zA-Z0-9]", "");
                File f = new File(Parcours.SAVE_PATH + filename + ".save");
                if (f.delete()) {
                    Parcours.sauvegarder(p);
                } else {
                    throw new FichierExistantException();
                }
            } catch (FichierAbsentException e) {
                System.out.println("Erreur sur le nom de fichier il existe pas...");
            } catch (FichierExistantException e) {
                System.out.println("Probleme qui ne devrait pas exister...");
            }
            System.out.println("\nConversion OK \n");

            System.out.println("Souhaitez vous continuez ? (o/n)");
            if(!sc.nextLine().equals("y")){
                isOK = false;
            }
        }

    }
}
