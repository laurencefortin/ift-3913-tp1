package org.ift2255.devoir3;
import java.util.Scanner;


/**
* La classe Menu permet d'afficher et de gérer le choix d'entré de chacun des menus du logiciel #GYM.
*
* @author  Julien Lanctot et Olivier Provost
* @version 2.0
* @since   2019-11-01
*/
public class Menu {
	
	// ----------------- PROPRIÉTÉS ----------------- \\
	private Scanner scanner;
	

	// ----------------- CONSTRUCTEUR ----------------- \\
	public Menu() {
		this.scanner = new Scanner(System.in);
	}
	
	
	// ----------------- MÉTHODES ----------------- \\
	
	/**
	* Affiche et gère l'entré de l'utilisateur au menu principal.
	*
	* @return le choix au menu principal fait par l'utilisateur.
	*/
	public int choixMenuPrincipal() {
		this.afficherMenuPrincipal();
		return this.validerChoix(0, 6);
	}
	
	/**
	* Affiche les options du menu principal.
	*/
	private void afficherMenuPrincipal() {
		System.out.println("CONSOLE D'ADMINISTRATION DU #GYM\n");
		System.out.println("Faites un choix dans le menu : \n");
		
		System.out.println("1 - Inscire un nouveau Membre");
		System.out.println("2 - Valider le numéro d'un Membre");
		System.out.println("3 - Valider le code QR d'un Membre (Simulation)\n");
		
		System.out.println("4 - Afficher le menu du Gestionnaire\n");
		
		System.out.println("5 - Exécuter la procédure comptable (Simulation)");
		System.out.println("6 - Payer la balance d'un Membre (Simulation)\n");
		
		System.out.println("0 - Quitter");
	}
	
	/**
	* Affiche et gère l'entré de l'utilisateur au menu Membre.
	*
	* @param estProfessionnel si oui ou non le Membre est un Professionnel.
	* @return le choix au menu Membre fait par l'utilisateur.
	*/
	public int choixMenuMembre(boolean estProfessionnel) {
		this.afficherMenuMembre(estProfessionnel);
		
		if(estProfessionnel) {
			this.afficherMenuProfessionnel();
			return this.validerChoix(0, 9);
		} else {
			return this.validerChoix(0, 6);
		}
	}
	
	/**
	* Affiche les options du menu offert au Membre et/ou au Professionnel.
	*
	* @param estProfessionnel si oui ou non le Membre est un Professionnel.
	*/
	private void afficherMenuMembre(boolean estProfessionnel) {
		System.out.println("Faites un choix dans le menu : \n");
		
		System.out.println("1 - Inscire le Membre à un Service");
		System.out.println("2 - Confirmer la présence d'un Membre à un Service");
		System.out.println("3 - Modifier les informations du Membre");
		System.out.println("4 - Supprimer le Membre\n");
		
		if(!estProfessionnel) System.out.println("0 - Retour au Menu Principal");
	}
	
	/**
	* Affiche les options du menu offert au Professionnel.
	*/
	private void afficherMenuProfessionnel() {
		System.out.println("5 - Créer un Service");
		System.out.println("6 - Consulter un Service");
		System.out.println("7 - Modifier un Service");
		System.out.println("8 - Supprimer un Servicer\n");
		
		System.out.println("9 - Confirmer la présence d'un Membre à un Service (Simulation - Code QR)\n");
		
		System.out.println("0 - Retour au Menu Principal");
	}
	
	
	/**
	* Affiche et gère l'entré de l'utilisateur au menu Gestionnaire.
	*
	* @return le choix de menu Gestionnaire fait par l'utilisateur.
	*/
	public int choixMenuGestionnaire() {
		this.afficherMenuGestionnaire();
		return this.validerChoix(0, 1);
	}
	
	/**
	* Affiche les options du menu offert au Gestionnaire.
	*/
	private void afficherMenuGestionnaire() {
		System.out.println("Faites un choix dans le menu : \n");
		
		System.out.println("1 - Générer le rapport de synthèse de la dernière semaine\n");
		
		System.out.println("0 - Retour au Menu Principal");
	}
	
	
	/**
	* Valide le choix entré par l'utilisateur, et demande à l'utilisateur de recommencer tant que le choix n'est pas un entier entre min et max.
	*
	* @param min l'entier le plus petit que l'utilisateur peut entrer.
	* @param max l'entier le plus grand que l'utilisateur peut entrer.
	* @return le choix fait par l'utilisateur.
	*/
	private int validerChoix(int min, int max) {
		Boolean bonChoix = false;
		int choix = 0;
		
		while(!bonChoix) {		
			// On demande le choix.
			String choixTemp = this.scanner.nextLine();
			
			try {  
				choix = Integer.parseInt(choixTemp);  
				bonChoix = true;
			} catch (NumberFormatException e) {  
				System.out.println("Format invalide, veuillez entrer un chiffre");
			}  
			
			if(choix < min || choix > max) {
				bonChoix = false;
				System.out.println("Choix invalide, votre choix doit être entre " + min + " et " + max);
			}
		}
		
		return choix;
	}
	
}
	
	