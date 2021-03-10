package jeu;
import golf.*;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ConfigParcoursException;
import exceptions.ConfigTerrainException;
import exceptions.FichierExistantException;

public class CreerSauvegardeParcours {
	public static void main(String[] args) {
		System.out.println();
		System.out.println("Generation des surfaces...");

		SurfaceDepart S_depart = new SurfaceDepart();
		SurfaceTrou S_trou = new SurfaceTrou();
		Surface S_green = new SurfaceGreen();

		System.out.println();
		System.out.println("Generation du parcours ...");

		Scanner sc = new Scanner(System.in);
		System.out.print(" entrez le nom du parcours :  ");
		String nom_parcours  = sc.nextLine();

		Parcours parcours = new Parcours(nom_parcours);
		parcours.setUpdatable(false);

		System.out.println();
		System.out.println("Generation du premier terrain ...");

		ArrayList<String> nom_terrains = new ArrayList<String>();
		//vaut 'o' tant que l'on cree des nouveaux terrains
		String recommencer = "o";
		do {
			System.out.println("entrez le nom de ce terrain :  ");
			String nom_terrain  = sc.nextLine();
			nom_terrains.add(nom_terrain);

			Terrain nouveau_terrain = new Terrain(nom_terrain, 3, S_depart,S_trou);
			nouveau_terrain.setSurface(0, 0, S_depart);
			nouveau_terrain.setSurface(Terrain.NB_COLONNE_TERRAIN-1, Terrain.NB_LIGNE_TERRAIN-1, S_trou);
			//System.out.println(nouveau_terrain);

			//verifier que le terrain est valide et l'ajouter au parcours
			System.out.println(nouveau_terrain.estValide());
			if (nouveau_terrain.estValide()) {
				try {
					Terrain.sauvegarder(nouveau_terrain);
					parcours.ajouterTerrain(nouveau_terrain);
				} catch (ConfigTerrainException e) {
					System.out.println("Le Terrain n'est pas valide");
				} catch (FichierExistantException e) {
					System.out.println("Le nom du terrain n'est pas valide");
				} catch (ConfigParcoursException e) {
					System.out.println("Le nombre maximal de terrain est atteint");
				}

				System.out.println("il y a " + parcours.getNbTerrains() + " terrains.");
			}


			System.out.println("voulez vous creer un nouveau terrain (o/n) :  ");
			recommencer = sc.nextLine();
		} while (recommencer.equals("o"));

		try {
			Parcours.sauvegarder(parcours);
		} catch (FichierExistantException e) {
			System.out.println("Nom de parcours invalide");
		}
		System.out.println("tout est fini");
	}
}
