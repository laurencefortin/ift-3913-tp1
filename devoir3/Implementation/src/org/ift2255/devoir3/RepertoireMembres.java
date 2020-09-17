package org.ift2255.devoir3;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Répertoire Membre permet de gérer tous les membre de l'application GYM
 * 
 * @author Laurence Fortin
 * @version 1.6
 * @since 2019-11-12
 */
public class RepertoireMembres {

	// ----------------- PROPRIÉTÉS ----------------- \\
	private Map<String, Membre> ListeMembre;
	private Map<String, String> ListeCourriel;

	// ----------------- CONSTRUCTEUR ----------------- \\
	public RepertoireMembres(Map<String, Membre> listeMembre) {
		this.ListeMembre = listeMembre;

		this.genererListeCourriel();
	}

	// ----------------- MÉTHODES ----------------- \\
	/**
	 * Cette méthode est utilisé pour ajouter un nouveau membre a la liste de membre
	 * existante
	 * 
	 * @param membre à ajouter.
	 */
	public void ajouterMembre(Membre membre) {
		if(membre == null) {
			throw new NullPointerException();
		} else {
			ListeMembre.put(membre.getNumero(), membre);
			System.out.println("Membre validé: " + membre.getPrenom() + " " + membre.getNom()
					+ ", Numéro de membre : " + membre.getNumero() + "\n");
		}

	}

	/**
	 * La méthode supprime le membre
	 * 
	 * @param membre que l'on veut supprimer
	 */
	public void supprimerMembre(Membre membre) {
		if(membre == null) {
			throw new NullPointerException();
		} else if(!ListeMembre.containsKey(membre.getNumero())) {
			throw new NoSuchElementException();
		} else {
			ListeMembre.remove(membre.getNumero());
			System.out.println("Membre supprimé : " + membre.getPrenom() + " " + membre.getNom());
		}
	}

	/**
	 * La méthode modifie certaine informations
	 * 
	 * @param membre que l'on veut modifier ses informations
	 */
	public void modifierMembre(Membre membre) {
		if(membre == null) {
			throw new NullPointerException();
		} else if(!ListeMembre.containsKey(membre.getNumero())) {
			throw new NoSuchElementException();
		} else {
			ListeMembre.replace(membre.getNumero(), membre);
			System.out.println(membre.getPrenom() + " " + membre.getNom() + " a été modifié.");
		}
	}
	/**
	 * La méthode valider les informations relatives a l'ajout de membre
	 * 
	 * @return membre qui va être ajouter
	 */
	public Membre validerAjoutMembre() {
		ValiderFormat valide = new ValiderFormat();
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez le prénom du membre:");
		String prenom = sc.nextLine();
		System.out.println("Veuillez entrez le nom du membre:");
		String nom = sc.nextLine();
		String[] nomComplet = valide.ValiderNom(prenom, nom);
		// retourne le nom complet dans un tableau le prenom en premier et le nom
		// ensuite
		prenom = nomComplet[0];
		nom = nomComplet[1];
		System.out.println("Veuillez entrez le couriel du membre:");
		String couriel = sc.nextLine();
		couriel = valide.ValiderCourielMembre(couriel);
		System.out.println("Veuillez entrez l'adresse du membre.");
		String adresse = sc.nextLine();
		adresse = valide.ValiderAdresse(adresse);
		System.out.println("Veuillez entrez la ville de l'adresse du membre.");
		String ville = sc.nextLine();
		ville = valide.ValiderVille(ville);
		System.out.println("Veuillez entrez la province de l'adresse du membre.");
		String province = sc.nextLine();
		province = valide.ValiderProvince(province);
		System.out.println("Veuillez entrez le code postal de l'adresse du membre.(Exemple : H0H 0H0)");
		String codePostal = sc.nextLine();
		codePostal = valide.ValiderCodePostal(codePostal);

		String idMembre = "000000001";

		if (ListeMembre.size() != 0) {
			String cle = "";
			TreeMap<String, Membre> listeTriee = new TreeMap<>(ListeMembre);
			for (Entry<String, Membre> entry : listeTriee.entrySet())
				cle = entry.getKey();
			idMembre = String.format("%09d", Integer.parseInt(cle) + 1);
		}
		System.out.println("Est-ce un professionnel");
		System.out.println("1 -Oui");
		System.out.println("2- Non");
		Boolean isPro;
		if (Integer.parseInt(sc.nextLine()) == 1) {
			isPro = true;
		} else {
			isPro = false;
		}
		int balanceMembre = 100;

		return new Membre(idMembre, prenom, nom, isPro, couriel, adresse, ville, province, codePostal, balanceMembre);

	}

	/**
	 * La méthode valider les informations relatives a la modification d'un membre
	 * 
	 * @param membre est le membre qui va être modifié
	 * @return Membre qui à été modifié
	 */
	public Membre validerModifierMembre(Membre membre) {
		ValiderFormat valide = new ValiderFormat();
		String numMembre = membre.getNumero();
		if (numMembre != "0") {
			System.out.println("Quel information le client veut-il modifier?");
			System.out.println("1 - Prénom");
			System.out.println("2 - Nom");
			System.out.println("3 - Couriel");
			System.out.println("4 - Adresse");
			System.out.println("5 - Ville");
			System.out.println("6 - Province");
			System.out.println("7 - Code postal");
			Scanner sc = new Scanner(System.in);
			String entree = sc.nextLine();
			if (entree.equals("1")) {// modifier prenom
				System.out.println("Veuillez entrer le nouveau prénom");
				System.out.println("Prénom actuel: " + ListeMembre.get(numMembre).getPrenom());
				String prenom = sc.nextLine();
				String[] nomComplet = valide.ValiderNom(prenom, ListeMembre.get(numMembre).getPrenom());
				prenom = nomComplet[0];
				ListeMembre.get(numMembre).setPrenom(prenom);
				System.out.println("Nouveau prénom : " + ListeMembre.get(numMembre).getPrenom());
			} else if (entree.equals("2")) {// modifier nom
				String prenom = ListeMembre.get(numMembre).getNom();
				System.out.println("Veuillez entrer le nouveau nom");
				System.out.println("Nom actuel: " + ListeMembre.get(numMembre).getNom());
				String nom = sc.nextLine();
				String[] nomComplet = valide.ValiderNom(prenom, nom);
				nom = nomComplet[1];
				ListeMembre.get(numMembre).setNom(nom);
				System.out.println("Nouveau nom : " + ListeMembre.get(numMembre).getNom());
			} else if (entree.equals("3")) {// modifier couriel
				System.out.println("Veuillez entrer le nouveau couriel");
				System.out.println("Couriel actuel: " + ListeMembre.get(numMembre).getCourriel());
				String couriel = sc.nextLine();
				couriel = valide.ValiderCourielMembre(couriel);
				ListeMembre.get(numMembre).setCourriel(couriel);
				System.out.println("Nouveau couriel : " + ListeMembre.get(numMembre).getCourriel());
			} else if (entree.equals("4")) {// modifier adresse
				System.out.println("Veuillez entrer la nouvelle adresse");
				System.out.println("Adresse actuelle: " + ListeMembre.get(numMembre).getAdresse());
				String adresse = sc.nextLine();
				adresse = valide.ValiderAdresse(adresse);
				ListeMembre.get(numMembre).setAdresse(adresse);
				System.out.println("Nouvelle adresse : " + ListeMembre.get(numMembre).getAdresse());
			} else if (entree.equals("5")) {// modifier ville
				System.out.println("Veuillez entrer la nouvelle ville");
				System.out.println("Ville actuelle: " + ListeMembre.get(numMembre).getVille());
				String ville = sc.nextLine();
				ville = valide.ValiderVille(ville);
				ListeMembre.get(numMembre).setVille(ville);
				System.out.println("Nouvelle ville : " + ListeMembre.get(numMembre).getVille());
			} else if (entree.equals("6")) {// modifier province
				System.out.println("Veuillez entrer la nouvelle province");
				System.out.println("Province actuelle: " + ListeMembre.get(numMembre).getProvince());
				String province = sc.nextLine();
				province = valide.ValiderProvince(province);
				ListeMembre.get(numMembre).setProvince(province);
				System.out.println("Nouvelle province: " + ListeMembre.get(numMembre).getProvince());
			} else if (entree.equals("7")) {// modifier code postal
				System.out.println("Veuillez entrer le nouveau code postal (Exemple : H0H 0H0)");
				System.out.println("Code postal actuel: " + ListeMembre.get(numMembre).getCodePostal());
				String codePostal = sc.nextLine();
				codePostal = valide.ValiderCodePostal(codePostal);
				ListeMembre.get(numMembre).setCodePostal(codePostal);
				System.out.println("Nouveau code postal : " + ListeMembre.get(numMembre).getCodePostal());
			} else
				nonvalide();

		} else
			nonvalide();
		return membre;
	}

	/**
	 * La méthode valide le numéro de membre entré
	 * 
	 * @return Membre qui indique celui qui à été validé
	 */
	public Membre validerNumeroMembre() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez le numéro du membre. Vous pouvez ommetre les 0 inutiles :");
		String numMembre = sc.nextLine();
		while (numMembre.length() < 9) {
			numMembre = "0" + numMembre;
		}
		if (ListeMembre.containsKey(numMembre) && ListeMembre.get(numMembre).getBalance() <= 0) {
			System.out.println("Membre validé \nBienvenue " + ListeMembre.get(numMembre).getPrenom() + " "
					+ ListeMembre.get(numMembre).getNom());
			return ListeMembre.get(numMembre);

		} else if (ListeMembre.containsKey(numMembre)) {

			System.out.println("Membre suspendu, balanceMembre non payée");
			return null;
		} else {
			System.out.println("Aucun membre associé à  ce numéro de membre.");
			return null;
		}
	}

	/**
	 * La méthode valide le membre grace à l'adresse couriel entré
	 * 
	 * @return Membre qui a été validé
	 */
	public Membre validerCodeQRMembre() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez l'adresse couriel du membre :");
		String couriel = sc.nextLine();

		// parcours la liste de membre, membre par membre pour ensuite comparer son
		// couriel a celui entree manuellement
		
		if(this.ListeCourriel.containsKey(couriel)) {
			// Le membre existe.
			
			// Dans membreTemp on a maintenant l'objet membre, qu'on peut vérifier si sa balance a été payée ou non.
			Membre membreTemp = this.ListeMembre.get(this.ListeCourriel.get(couriel));
			 if (membreTemp.getCourriel().compareTo(couriel) == 0 && membreTemp.getBalance() <= 0) {
				 System.out.println("Membre validé \nBienvenue " + membreTemp.getPrenom() + " " + membreTemp.getNom());
					return membreTemp;
				} else if (membreTemp.getBalance() > 0) {
					System.out.println("Membre suspendu, balanceMembre non payée");
					return null;
				}
			 }else {
			// Le membre n'existe pas
			System.out.println("Aucun membre associé à cette adresse couriel.");
			return null;
		}
		
		return null;
	}

	/**
	 * La méthode complete le numéro de membre lorsqu'il contient des 0 en préfixe
	 * 
	 * @param numMembre à compléter.
	 * @return String num de membre complété
	 */
	private String completerNumeroMembre(String numMembre) {
		while (numMembre.length() < 9) {
			numMembre = "0" + numMembre;
		}
		return numMembre;

	}

	/**
	 * Méthode qui permet au membre de payer leur balance
	 */
	public void payerBalance() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez le numéro du membre. Vous pouvez ommetre les 0 inutiles :");
		String numMembre = sc.nextLine();
		while (numMembre.length() < 9) {
			numMembre = "0" + numMembre;
		}
		Membre membre = ListeMembre.get(numMembre);
		if (membre.getBalance() > 0) {
			System.out.println("Votre balance est de " + membre.getBalance());
			System.out.println("Quel montant voulez-vous payer? ");
			String montant = sc.nextLine();
			membre.setBalance(membre.getBalance() - Integer.parseInt(montant));
			System.out.println("Votre balance est maintenant de " + membre.getBalance());
		} else if (membre.getBalance() == 0) {
			System.out.println("Votre balance est null, vous n'avez rien a payer");
		}
	}
	/**
	 * Methode qui crée un Hashmap avec le couriel
	 * comme clé, et le numMembre comme valeur
	 */
	
	private void genererListeCourriel() {
		this.ListeCourriel = new HashMap<>();
		
		for(Membre membre : this.ListeMembre.values()) {
			ListeCourriel.put(membre.getCourriel(), membre.getNumero());
		}
	}
	
	/**
	 * Méthode qui affiche la string d'entrée invalide
	 */
	public void nonvalide() {
		System.out.println("Entrée invalide \n");
	}

	// ----------------- GETTERs & SETTERs ----------------- \\
	public Map<String, Membre> getListeMembre() {
		return ListeMembre;
	}

	public void setListeMembre(Map<String, Membre> listeMembre) {
		ListeMembre = listeMembre;
	}
}
