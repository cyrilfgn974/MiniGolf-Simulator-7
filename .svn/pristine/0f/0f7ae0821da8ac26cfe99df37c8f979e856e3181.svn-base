package jeu;
import golf.*;

import java.util.Scanner;

import exceptions.ConfigParcoursException;
import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;

public class CreerParcoursEditeur {
	public static void main(String[] args) throws FichierAbsentException, ConfigParcoursException, FichierExistantException {
		System.out.println();
		System.out.println("Generation des surfaces...");

		System.out.println();
		System.out.println("Generation du parcours ...");

		Scanner sc = new Scanner(System.in);
		System.out.print(" entrez le nom du parcours :  ");
		String nom_parcours  = sc.nextLine();
		
		Parcours parcours = new Parcours(nom_parcours);
		
		String recommencer;
		do {
			System.out.println("entrez le nom de ce terrain :  ");
			String nom_terrain = sc.nextLine();
			Terrain newTerrain = Terrain.charger(nom_terrain + ".save");
			
			parcours.ajouterTerrain(newTerrain);
			
			System.out.println("nouveau terrain ?   ");
			recommencer = sc.nextLine();
		} while (recommencer.equals("o"));
		
		Parcours.sauvegarder(parcours);
		System.out.println("fini");
	}
}