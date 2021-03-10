package jeu;

import java.util.Scanner;

import exceptions.ConfigParcoursException;
import exceptions.ConfigTerrainException;
import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;

public class CreerSauvegardeJoueur {
	public static void main(String[] args) throws FichierAbsentException, ConfigParcoursException, ConfigTerrainException, FichierExistantException {
		String continuer;
		Scanner sc = new Scanner(System.in);
		
		
		do {
			System.out.println();
			System.out.println("Generation d'un nouvel inventaire...");
			
			System.out.println();
			System.out.print("Entrez son nom :  ");
			String nom_joueur = sc.nextLine();
			
			Inventaire inventaire = new Inventaire(nom_joueur);
			Inventaire.sauvegarder(inventaire);
			
			System.out.print("voulez vous creer un nouvel inventaire :   ");
			continuer = sc.nextLine();
			
			
		} while (continuer.contentEquals("o"));
		
		System.out.println();
		System.out.println("Cr�ation des inventaires termin�e");
		
	
	
	
	}
}
