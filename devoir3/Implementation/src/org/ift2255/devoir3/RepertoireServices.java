package org.ift2255.devoir3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Répertoire Service permet de gérer tous les services de l'application GYM
 * 
 * @author Julien Lanctot
 * @version 1.0
 * @since 2019-12-01
 */
public class RepertoireServices {

	// ----------------- PROPRIÉTÉS ----------------- \\
	private Map<String, Service> ListeService;

	// ----------------- CONSTRUCTEUR ----------------- \\
	public RepertoireServices(Map<String, Service> ListeService) {
		this.ListeService = ListeService;
	}

	// ----------------- MÉTHODES ----------------- \\

	/**
	 * La méthode affiche les services du jour et demande au membre de choisir un
	 * code de service dans une liste de service qui ont une séance aujourd'hui. On
	 * affiche l'info du service et on confirme avec le membre s'il veut vraiment
	 * s'inscrire. Si oui, on créé une Inscription dans la séance correspondante.
	 * 
	 * @param membre est le membre qui va s'inscrire à la séance
	 * 
	 */
	public void inscrireService(Membre membre) {
		System.out.println("Veuillez entrez le code de service pour l'inscription");
		ListeService.forEach((k, v) -> {
			if (v.getSeances().containsKey(LocalDate.now())) {
				System.out.println(v.getDescription() + ", code de service : " + k);
			}
		});

		Scanner sc = new Scanner(System.in);
		String codeservice = sc.nextLine();

		if (ListeService.containsKey(codeservice)
				&& ListeService.get(codeservice).getSeances().containsKey(LocalDate.now())) {
			if (!ListeService.get(codeservice).getSeances().get(LocalDate.now()).getInscriptions()
					.containsKey(membre.getNumero())) {
				if (ListeService.get(codeservice).getSeances().get(LocalDate.now()).getCapacite() != ListeService
						.get(codeservice).getCapacite()) {
					Seance Seance1 = ListeService.get(codeservice).getSeances().get(LocalDate.now());
					Service Service1 = ListeService.get(codeservice);
					Service1.printAttributs();
					System.out.println("Etes-vous sur de vouloir inscrire le client à cette séance");
					System.out.println("1- Oui");
					System.out.println("2- Non");

					if (sc.nextLine().equals("1")) {
						Inscription Inscription1 = new Inscription(membre.getNumero(),
								Service1.getNumeroProfessionnel(), codeservice, Seance1.getCode(),
								Service1.getCommentaires());
						Seance1.getInscriptions().put(membre.getNumero(), Inscription1);
						membre.incrementerBalance(Service1.getFrais());

						System.out.println("Inscription à la séance " + Seance1.getCode() + " confirmée! \n");
						System.out.println(
								"Des frais de " + Service1.getFrais() + "$ ont été rajouté à la balance du client.");

					} else if (sc.nextLine().equals("2")) {
						System.out.println("Inscription annulée");

					} else {
						nonValide();

					}
				} else {
					System.out.println("Le service est à capacité maximale.");
				}

			} else {
				System.out.println("Le client est déjà inscrit.");
			}

		} else {
			nonValide();
		}
	}

	/**
	 * La méthode affiche les séances d'aujourd'hui auxquelles le membre est
	 * inscrit. Il sélectionne la séance à laquelle il veut confirmer sa présence.
	 * 
	 * @param membre est le membre qui veut confirmer sa présence à la séance
	 * 
	 */
	public void participerService(Membre membre) {
		System.out.println("Veuillez entrez le code de service confirmer la participation du client");
		ListeService.forEach((k, v) -> {
			if (v.getSeances().containsKey(LocalDate.now())
					&& v.getSeances().get(LocalDate.now()).getInscriptions().containsKey(membre.getNumero())) {
				System.out.println(v.getDescription() + ", code de service : " + k);
			}
		});

		Scanner sc = new Scanner(System.in);
		String codeservice = sc.nextLine();

		if (ListeService.containsKey(codeservice)) {
			if (ListeService.get(codeservice).getSeances().containsKey(LocalDate.now())) {
				if (ListeService.get(codeservice).getSeances().get(LocalDate.now()).getInscriptions()
						.containsKey(membre.getNumero())) {
					if (!ListeService.get(codeservice).getSeances().get(LocalDate.now()).getParticipations()
							.containsKey(membre.getNumero())) {
						Seance Seance1 = ListeService.get(codeservice).getSeances().get(LocalDate.now());
						Service Service1 = ListeService.get(codeservice);
						// Affichage de l'info du service
						Service1.printAttributs();
						System.out.println("Validé");
						Participation participation = new Participation(membre.getNumero(),
								Service1.getNumeroProfessionnel(), codeservice, Seance1.getCode(),
								Service1.getCommentaires());
						Service1.getSeances().get(LocalDate.now()).getParticipations().put(membre.getNumero(),
								participation);
					} else {
						System.out.println("Le client a déjà confirmé sa présence.");
					}
				}

				// Sinon, on dit à l'utilisateur qu'il n'est pas inscrit
				else {
					System.out.println("Le client n'est pas incrit à cette séance \n");

				}
			} else {
				System.out.println("Il n'y a pas de séance aujourd'hui pour ce service.");
			}
		}

		// Sinon, l'entrée est invalide, on retourne au menu des membres
		else {
			nonValide();
		}

	}

	/**
	 * La méthode affiche les services associés au professionnel courant. Le
	 * professionnel peut choisir dans cette liste un service qu'il désire
	 * supprimer. Les services affichés sont seulement ceux qui n'ont pas de séance
	 * le jour même avec des membres déjà inscrits.
	 * 
	 * @param membre est le professionnel qui désire supprimer un service.
	 * @return Service à supprimer
	 */
	public Service demanderServiceSupprimer(Membre membre) {
		System.out.println("Veuillez entrer le code du service à supprimer");
		listeServices(membre, false);
		Scanner sc = new Scanner(System.in);
		String codeservice = sc.nextLine();

		if (ListeService.containsKey(codeservice)
				&& ListeService.get(codeservice).getNumeroProfessionnel() == membre.getNumero()
				&& !ListeService.get(codeservice).getSeances().get(LocalDate.now()).getInscriptions().isEmpty()) {
			return ListeService.get(codeservice);
		} else {
			nonValide();
			return null;
		}
	}

	
	/**
	 * La méthode supprime un Service dans la ListeService.
	 * 
	 * @param service est le Service à supprimer.
	 * 
	 */
	public void supprimerService(Service service) {
		if (service == null) {
			throw new NullPointerException();
		} else if (!ListeService.containsKey(service.getCode())) {
			throw new NoSuchElementException();
		} else {
			ListeService.remove(service.getCode());
			System.out.println("Service supprimé : " + service.getCode());
		}
	}

	/**
	 * La méthode affiche les services associés au professionnel courant. Le
	 * professionnel peut choisir dans cette liste un service qu'il désire modifier.
	 * Les services affichés sont seulement ceux qui n'ont pas de séance le jour
	 * même avec des membres déjà inscrits.
	 * 
	 * @param membre est le professionnel qui désire modifier un service.
	 * @return Service à modifier.
	 */
	public Service demanderServiceModifier(Membre membre) {
		// On montre la liste des service dispo (Service1 pour le moment)

		ValiderFormat valide = new ValiderFormat();
		System.out.println("Veuillez entrer le code du service à modifier");
		listeServices(membre, false);
		Scanner sc = new Scanner(System.in);
		String codeservice = sc.nextLine();
		
		
		// Si le service n'est pas associé au professionnel courant
		if (!ListeService.containsKey(codeservice)
				&& !ListeService.get(codeservice).getNumeroProfessionnel().equals(membre.getNumero())) {
			nonValide();
			return null;
		}
		
		Service service = ListeService.get(codeservice);

		// Affichage de l'info du service
		ListeService.get(codeservice).printAttributs();
		
		System.out.println("Qu'est-ce que le client veut modifier?\n");
		System.out.println("1 - Date de début");
		System.out.println("2 - Date de fin ");
		System.out.println("3 - Heure de service");
		System.out.println("4 - Récurrence ");
		System.out.println("5 - Capacité maximum ");
		System.out.println("6 - Description");
		System.out.println("7 - Frais du service ");
		System.out.println("8 - Commentaires sur le service ");

		String choix = sc.nextLine();
		if (choix.equals("1")) {
			LocalDate datedebut = valide.ValiderDateDebut();
			LocalDate datefin = valide.ValiderDateFin(datedebut);
			service.setDateDebut(datedebut);
			service.setDateFin(datefin);
			System.out.println("Nouveau date de début : " + datedebut);

		} else if (choix.equals("2")) {
			LocalDate datedebut = valide.ValiderDateDebut();
			LocalDate datefin = valide.ValiderDateFin(datedebut);
			service.setDateDebut(datedebut);
			service.setDateFin(datefin);
			System.out.println("Nouveau date de fin : " + datefin);

		} else if (choix.equals("3")) {
			String nouvelleheure = valide.ValiderHeure();
			service.setHeure(nouvelleheure);
			System.out.println("Nouveau heure de service : " + nouvelleheure);

		} else if (choix.equals("4")) {
			boolean[] Recurrence = recurrence();
			service.setRecurrences(Recurrence);
			System.out.println("Nouvelle récurrence : " + service.BooltoDays());

		} else if (choix.equals("5")) {
			String capacite = valide.ValiderCapacite();
			service.setCapacite(Integer.parseInt(capacite));
			System.out.println("Nouvelle capacité maximum : " + capacite);

		} else if (choix.equals("6")) {
			String description = valide.ValiderDescription();
			service.setDescription(description);
			System.out.println("Nouvelle description : " + description);

		} else if (choix.equals("7")) {
			String frais = valide.ValiderFrais();
			service.setFrais(Integer.parseInt(frais));
			System.out.println("Nouveaux frais : " + frais);

		} else if (choix.equals("8")) {
			String commentaires = valide.ValiderCommentaires();
			service.setCommentaires(commentaires);
			System.out.println("Nouveaux commentaires : " + commentaires);

		} else {
			nonValide();
		}			
			
		service.genererSeances();
		return service;

	}

	/**
	 * La méthode modifie un Service dans la ListeService.
	 * 
	 * @param service est le Service à modifier.
	 * 
	 */
	public void modifierService(Service service) {
		if(service == null) {
			throw new NullPointerException();
		} else if(!ListeService.containsKey(service.getCode())) {
			throw new NoSuchElementException();
		} else {
			ListeService.replace(service.getCode(), service);
			System.out.println(service.getCode() + " a été modifié.");
		}
	}
	
	/**
	 * La méthode affiche les séances d'aujourd'hui associés au professionnel
	 * courant. Le professionnel peut choisir dans cette liste une séance qu'il
	 * désire consulter. Consulter une séance affiche la capacité courante de la
	 * séance et les code de membres insscrits.
	 * 
	 * @param membre est le professionnel qui désire consulter une séance.
	 * 
	 */
	public void consulterInscriptions(Membre membre) {
		// On montre la liste des services du professionnel
		System.out.println("Veuillez entrer le code du service à consulter");
		listeServices(membre, true);
		Scanner sc = new Scanner(System.in);
		String codeservice = sc.nextLine();
		// Si le service est associé au professionnel courant
		if (ListeService.containsKey(codeservice)
				&& (ListeService.get(codeservice).getNumeroProfessionnel().equals(membre.getNumero()))) {

			if (!ListeService.get(codeservice).getSeances().get(LocalDate.now()).getInscriptions().isEmpty()) {
				System.out.println("Liste des personnes inscrites :");
				ListeService.get(codeservice).getSeances().get(LocalDate.now()).getInscriptions().forEach((a, b) -> {
					System.out.println(a);
				});
				System.out.println("Capacité courante :"
						+ ListeService.get(codeservice).getSeances().get(LocalDate.now()).getCapacite());
				System.out.println("Capacité max :" + ListeService.get(codeservice).getCapacite());

			}
			// Sinon, il n'y a pas d'inscriptions pour cet service
			else {
				System.out.println("Il n'y a pas d'inscription à ce service.");
			}
		} else {
			nonValide();
		}
	}

	/**
	 * La méthode créé un service pour un professionnel et l'ajoute dans la liste
	 * des services.
	 * 
	 * @param membre est le professionnel qui désire créer un service.
	 * @return Service le service créé.
	 */
	public Service creerService(Membre membre) {
		ValiderFormat valide = new ValiderFormat();
		LocalDate datedebut = valide.ValiderDateDebut();
		LocalDate datefin = valide.ValiderDateFin(datedebut);
		String heure = valide.ValiderHeure();
		boolean[] Recurrence = recurrence();
		String capacite = valide.ValiderCapacite();
		String description = valide.ValiderDescription();
		String codeservice = valide.ValiderCodeService(ListeService);
		String frais = valide.ValiderFrais();
		String commentaires = valide.ValiderCommentaires();
		Service service = new Service(codeservice, membre.getNumero(), description, datedebut, datefin, heure,
				Recurrence, Integer.parseInt(capacite), Integer.parseInt(frais), commentaires);

		return service;
	}

	/**
	 * La méthode permet d'ajouter un Service à la liste de Services.
	 * 
	 * @param service a ajouté à la liste de Services.
	 */
	public void ajouterService(Service service) {
		if (service == null) {
			throw new NullPointerException();
		} else {
			ListeService.put(service.getCode(), service);
			System.out.println("Votre service a été créé ! Il débutera le " + service.getDateDebut() + " et finira le "
					+ service.getDateFin() + ".  Il commencera à " + service.getHeure()
					+ " et aura une récurrence tous les " + service.BooltoDays() + " avec une capacité maximale de "
					+ service.getCapacite() + " personnes et sa description est la suivante : "
					+ service.getDescription() + " Commentaires : " + service.getCommentaires());
		}
	}

	/**
	 * La méthode affiche la liste des séances d'aujourd'hui associé à un
	 * professionnel.
	 * 
	 * @param membre est le professionnel qui désire créer un service, C
	 * @param consulter estle booléen qui change l'affichage dépendemment de la méthode qui appele listeServices.
	 */
	private void listeServices(Membre membre, boolean consulter) {
		if (consulter) {
			ListeService.forEach((k, v) -> {
				if ((v.getNumeroProfessionnel().equals(membre.getNumero()))) {
					System.out.println(v.getDescription() + ", code de service : " + k);
				}

			});
		} else {
			ListeService.forEach((k, v) -> {
				if ((v.getNumeroProfessionnel().equals(membre.getNumero()))) {
					if (v.getSeances().get(LocalDate.now()).getInscriptions().isEmpty()) {
						System.out.println(v.getDescription() + ", code de service : " + k);
					}
				}
			});
		}

	}

	private void nonValide() {
		System.out.println("Entrée invalide \n");
	}

	private boolean[] recurrence() {
		System.out.println("Veuillez choisir les jours pour la récurrence hebdomadaire du service:");
		Scanner sc = new Scanner(System.in);
		String[] jours = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
		boolean[] Recurrence = new boolean[7];
		for (int i = 0; i < 7; i++) {

			System.out.println(jours[i] + "?");
			System.out.println("1- Oui");
			System.out.println("2- Non");
			String Entree = sc.nextLine();
			while (!Entree.equals("1") && !Entree.equals("2")) {
				nonValide();
				System.out.println(jours[i] + "?");
				System.out.println("1- Oui");
				System.out.println("2- Non");
				Entree = sc.nextLine();
			}
			if (Entree.equals("1")) {
				Recurrence[i] = true;
			} else if (Entree.equals("2")) {
				Recurrence[i] = false;
			}
		}
		return Recurrence;
	}

	// ----------------- GETTERs & SETTERs ----------------- \\
	public Map<String, Service> getListeService() {
		return ListeService;
	}

	public void setListeService(Map<String, Service> listeService) {
		ListeService = listeService;
	}

}
