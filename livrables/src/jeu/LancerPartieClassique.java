package jeu;
import golf.Parcours;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ConfigParcoursException;
import exceptions.ConfigTerrainException;
import exceptions.FichierAbsentException;
import exceptions.FichierExistantException;
import org.newdawn.slick.SlickException;

public class LancerPartieClassique {
	
	
	
	
	
	
	
	public static void main(String[] args) throws FichierAbsentException, ConfigParcoursException, ConfigTerrainException, FichierExistantException, SlickException {
//
//		System.out.println();
//		 System.out.println("Generation des surfaces...");
//
//		 Surface S_depart = new SurfaceDepart();
//		 Surface S_trou = new SurfaceTrou();
//		 Surface S_green = new SurfaceGreen();
//
//		 System.out.println();
//		 System.out.println("Generation du parcours ...");
//
//		 Parcours P_premier = new Parcours("premier_parcours");
//
//
//		 System.out.println();
//		 System.out.println("Generation du premier terrain ...");
//
//		 Terrain T_premier= new Terrain("T_premier", 3, (SurfaceDepart) S_depart,(SurfaceTrou) S_trou);
//		 for (int i=0 ; i<Terrain.NB_LIGNE_TERRAIN; i++) {
//			 for (int j=0 ; j<Terrain.NB_COLONNE_TERRAIN; j++) {
//				 T_premier.setSurface(i, j, S_green);
//			 }
//		 }
//
//
//		 T_premier.setSurface(0, 0, S_depart);
//		 T_premier.setSurface(Terrain.NB_LIGNE_TERRAIN/2, Terrain.NB_COLONNE_TERRAIN/2, S_trou);
//
//		 System.out.println();
//		 System.out.println("Affichage du premier terrain ...");
//
//		 for (int i=0 ; i<Terrain.NB_LIGNE_TERRAIN; i++) {
//			 System.out.print("[");
//			 for (int j=0 ; j<Terrain.NB_COLONNE_TERRAIN; j++) {
//				 System.out.print(" ");
//				 System.out.print(T_premier.getSurface(i, j).getId());
//				 System.out.print(" ");
//			 }
//			 System.out.println("]");
//	 }
////		//verifier que le terrain est valide et l'ajouter au parcours
//		System.out.println(T_premier.estValide());
//		if (T_premier.estValide()) {
//		 Terrain.sauvegarder(T_premier);
//		 P_premier.ajouterTerrain(T_premier);
//		}
//		Parcours.sauvegarder(P_premier);

		//System.out.println();
		//System.out.println("Generation d'une balle...");
		 
		//Connaitre la position de depart et d'arrivee
//		Point2D point_depart = T_premier.getDepart();
//		Point2D point_trou   = T_premier.getTrou();
		 
		//Positionner la balle au point de depart
		//Balle B_j1 = new Balle(-1, -1);
		//Balle B_j2 = new Balle(-1, -1);
		 
		System.out.println();
		System.out.println("Generation du joueur ..."); 
		
		//Liste des joueurs in game
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		
		String nom_joueur = "theodore";
		
		Inventaire inv_1 = Inventaire.charger(nom_joueur + ".save");
		System.out.println(inv_1);
		//
		// Afficher les balles poss�d�es
		// Choisir une balle
		inv_1.addBalles(new Balle());
		Balle balle_J1 = inv_1.getBalles().get(0);
		
		Joueur J_1 = new Joueur(nom_joueur, balle_J1);
		joueurs.add(J_1);
		Balle balle_J2 = new Balle();
		Joueur J_2 = new Joueur("theo", balle_J2);
		joueurs.add(J_2);
		System.out.println("le joueur 1 s'appelle " + J_1.getNom());
		
		//joueurs.add(J_2);
		
		System.out.println();
		System.out.println("Generation de l'arbitre ...");
		
		Scanner sc = new Scanner(System.in);
	    //System.out.print(" entrez le nom du parcours :  ");
		//String nom_parcours  = sc.nextLine();
		String nom_parcours = "parcours";
		Parcours P_encours = Parcours.charger(nom_parcours + ".save");
		
		Arbitre A_classique = new ArbitreClassique(joueurs, P_encours);
		A_classique.arbitrer();
		
		 /*
		 //On joue tant que l'on a pas atteint le trou
		 while ( (int) balle.getX() != (int) point_trou.getX() 
				 || (int) balle.getY() != (int) point_trou.getY()
				 || balle.getVitesse() < 0.1) {
			 
			 //Si la balle ne roule pas on joue un coup
			 if (balle.getVitesse()<0.01) {
				//afficher le terrain
				 afficher_terrain(T_premier, balle);
				
				 
				//jouer un coup
				 String STR_Nouvelle_vitesse, STR_Nouvel_angle;
				 Scanner sc = new Scanner(System.in);
			     System.out.print(" entrez la vitesse de la balle :  ");
				 STR_Nouvelle_vitesse  = sc.nextLine();
				 System.out.print(" choisissez l'angle de la balle en degr� (sens trigo avec 0 vers le bas :  ");
			     STR_Nouvel_angle      = sc.nextLine();
				 
			     float Nouvelle_vitesse = Float.valueOf(STR_Nouvelle_vitesse);
			     float Nouvel_angle     = Float.valueOf(STR_Nouvel_angle);
			     
			     //System.out.println("Avant : [ " + balle.getVx() + " , " + balle.getVy() + " ] ");
				 balle.initVitesse(Nouvelle_vitesse, Nouvel_angle);
				 //System.out.println("Apres : [ " + balle.getVx() + " , " + balle.getVy() + " ] ");
				 //System.out.println();
			 }
			 
			 // Actualiser le mouvement de la bille;
			 System.out.println(balle.getX() + " , " + balle.getY());
			 Surface S_balle = T_premier.getSurface((int) balle.getX(), (int) balle.getY());
			 S_balle.actualiserMouvement(balle, dt);
		 }
		 System.out.println();
		 System.out.println();
		 System.out.println("BRAVO !");
		 System.out.println();
		 System.out.println();
	 */
	}
}
