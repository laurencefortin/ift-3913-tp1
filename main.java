package org.ift2255.devoir3;
/**
* La classe Main du #GYM, est une application qui permet de gérer le fonctionnement d'un Gym.
*
* @author  Laurence Fortin, Julien Lanctot et Olivier Prevost
* @version 3.0
* @since   2019-09-31 
*/
public class Main {	
	// Objets principaux du programme.
	private static Menu menu;
	private static Procedure procedure;
	private static RepertoireMembres repertoireMembres;
	private static RepertoireServices repertoireServices;

	public static void main(String[] args) {

		menu = new Menu();
		procedure = new Procedure();
		
		repertoireMembres = new RepertoireMembres(CentreDonnee.lireMembres());
		repertoireServices = new RepertoireServices(CentreDonnee.lireServices());
		
		// Affichage du guide d'utilisation du programme.
		afficherGuide();
		
		// Gestion du menu Principal.
		menuPrincipal();
	}

	private static void menuPrincipal() {
		int choixMenuPrincipal = 1;
		Membre membreValide;

		// Tant que l'utilisateur ne quitte pas (choix == 0) on lui affiche le menu.
		while (choixMenuPrincipal != 0) {
			choixMenuPrincipal = menu.choixMenuPrincipal();

			// 1 - Ajouter un Membre.
			if (choixMenuPrincipal == 1) {
				repertoireMembres.ajouterMembre(repertoireMembres.validerAjoutMembre());
			}
			// 2 - Valider le numéro d'un Membre, pour accéder au Menu du Membre.
			else if (choixMenuPrincipal == 2) {
				membreValide = repertoireMembres.validerNumeroMembre();
				if (membreValide != null) menuMembre(membreValide);
			}
			// 3 - Valider le code QR d'un Membre, pour accéder au Menu du Membre.
			else if (choixMenuPrincipal == 3) {
				membreValide = repertoireMembres.validerCodeQRMembre();
				if (membreValide != null) menuMembre(membreValide);
			}
			// 4 - Accéder au Menu du Gestionnaire.
			else if (choixMenuPrincipal == 4) {
				if (menu.choixMenuGestionnaire() == 1) {
					procedure.setLstMembres(repertoireMembres.getListeMembre());
					procedure.setLstServices(repertoireServices.getListeService());
					procedure.genererRapportGestionnaire();
				}
			}
			// 5 - Exécuter la procédure comptable.
			else if (choixMenuPrincipal == 5) {
				procedure.setLstMembres(repertoireMembres.getListeMembre());
				procedure.setLstServices(repertoireServices.getListeService());
				procedure.executer();
			}
			else if (choixMenuPrincipal == 6) {
				repertoireMembres.payerBalance();
			}
		}
		
		// Si l'utlisateur quitte. On sauvegarde les structures.
		if (choixMenuPrincipal == 0) {
			CentreDonnee.ecrireMembres(repertoireMembres.getListeMembre());
			CentreDonnee.ecrireServices(repertoireServices.getListeService());
		}
	}

	private static void menuMembre(Membre membre) {
		int choixMenuMembre = 1;

		// Tant que l'utilisateur ne quitte pas (choix == 0) on lui affiche le menu.
		while (choixMenuMembre != 0) {
			choixMenuMembre = menu.choixMenuMembre(membre.estProfessionnel());

			// 1 - Inscire un Membre à un Service.
			if (choixMenuMembre == 1) {
				repertoireServices.inscrireService(membre);
			}
			// 2 - Confirmer la présence d'un Membre à un Service.
			else if (choixMenuMembre == 2) {
				repertoireServices.participerService(membre);
			}
			// 3 - Modifier les informations d'un Membre.
			else if (choixMenuMembre == 3) {
				repertoireMembres.modifierMembre(repertoireMembres.validerModifierMembre(membre));
			}
			// 4 - Supprimer un Membre.
			else if (choixMenuMembre == 4) {
				repertoireMembres.supprimerMembre(membre);
			}
			// 5 - Créer un Service (Professionnel).
			else if (choixMenuMembre == 5 && membre.estProfessionnel()) {
				repertoireServices.ajouterService(repertoireServices.creerService(membre));
			}
			// 6 - Consulter un Service (Professionnel).
			else if (choixMenuMembre == 6 && membre.estProfessionnel()) {
				repertoireServices.consulterInscriptions(membre);
			}
			// 7 - Modifier un Service (Professionnel).
			else if (choixMenuMembre == 7 && membre.estProfessionnel()) {
				Service service = repertoireServices.demanderServiceModifier(membre);
				
				if (service != null)
				 repertoireServices.modifierService(service);
			}
			// 8 - Supprimer un Service (Professionnel).
			else if (choixMenuMembre == 8 && membre.estProfessionnel()) {
				Service service = repertoireServices.demanderServiceSupprimer(membre);
				
				if (service != null)
					repertoireServices.supprimerService(service);
			}
			// 9 - Confirmer la présence d'un Membre à un Service. (Professionnel).
			else if (choixMenuMembre == 9 && membre.estProfessionnel()) {
				Membre membreTemp = repertoireMembres.validerCodeQRMembre();
				repertoireServices.participerService(membreTemp);
			}
		}
	}

	// TODO : modifier ...
	private static void afficherGuide() {
		System.out.println("Guide d'utilisation du #GYM :\n");
		System.out.println("Il y a 1 membre et 2 professionnel et 1 membre : Julien, Laurence et Olivier");
		System.out.println("Leurs Id sont 234567890, 123456789 et 333333333, respectivement");
		System.out.println("Il y a également une activité, avec le code d'activité 1234566");
		System.out.println("Cette activité comporte 1 inscription à  cette activité");
		System.out.println("Lors de la création d'un nouveau membre, vous devez payez sa balance avant de pouvoir continuer dans les autres menus.\n\n");
	}
}
