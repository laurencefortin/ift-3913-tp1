package org.ift2255.devoir3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Répertoire Membre permet de gérer tous les membre de l'application GYM
 * 
 * @author Laurence Fortin et Julien Lanctot
 * @version 1.4
 * @since 2019-11-12
 */
public class ValiderFormat {
	/**
	 * Méthode qui de set la regex du format pour le code postal
	 * 
	 * @param codePostal à vérifier.
	 * @return Boolean qui détermine si c'est un bon format ou non pour code postal
	 */
	public boolean FormatCodePostal(String codePostal) {
		String regexCodePostal = "^(?!.*[DFIOQU])[a-vxyA-VXY][0-9][a-zA-Z] ?[0-9][a-zA-Z][0-9]$";
		return isValid(regexCodePostal, codePostal);
	}

	/**
	 * Méthode qui de set la regex du format pour le couriel
	 * 
	 * @param couriel à vérifier.
	 * @return Boolean qui détermine si c'est un bon format ou non pour couriel
	 */
	public boolean FormatCourielMembre(String couriel) {
		String regexCouriel = "[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+";
		return isValid(regexCouriel, couriel);
	}

	/**
	 * Méthode qui de set la regex du format pour l'adresse
	 * 
	 * @param adresse à vérifier.
	 * @return Boolean qui détermine si c'est un bon format ou non pour adresse
	 */
	public boolean FormatAdresse(String adresse) {
		String regexAdresse = "[0-9]+( \\S+)+";
		return isValid(regexAdresse, adresse);
	}

	/**
	 * Méthode qui de set la regex du format de lettre seulement
	 * 
	 * @param entree à valider.
	 * @return Boolean qui détermine si c'est un bon format ou non pour l'entree
	 */
	public boolean FormatLettre(String entree) {
		String regexLettre = "[a-zA-Z]+";
		return isValid(regexLettre, entree);
	}

	/**
	 * Méthode qui de set la regex du format pour la date
	 * 
	 * @param date à valider.
	 * @return Boolean qui détermine si c'est un bon format ou non de la date
	 */
	public boolean FormatDate(String date) {
		String regexDate = "(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}";
		return isValid(regexDate, date);

	}

	/**
	 * Méthode qui de set la regex du format pour la date
	 * 
	 * @param heure à valider.
	 * @return Boolean qui détermine si c'est un bon format ou non de l'heure
	 */
	public boolean FormatHeure(String heure) {
		String regexHeure = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		return isValid(regexHeure, heure);
	}

	/**
	 * Méthode qui de set la regex du format pour les code de service
	 * 
	 * @param code à vérifer.
	 * @return Boolean qui détermine si c'est un bon format ou non de code
	 */

	public boolean FormatCodeService(String code) {
		String regexCode = "\\d{7}";
		return isValid(regexCode, code);
	}

	public boolean FormatFrais(String frais) {
		String regexFrais = "(\\d{0,2}(\\.\\d{1,2})?|100(\\.00?)?)";
		return isValid(regexFrais, frais);
	}

	public boolean isValid(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public boolean isInteger(String nombre) {
		try {
			Integer.parseInt(nombre);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isValidDate(String date) {
		String temp = DatetoString(StringtoDate(date));
		if (temp.equals(date))
			return true;

		else
			return false;
	}

	public String[] ValiderNom(String prenomMembre, String nomMembre) {
		Scanner sc = new Scanner(System.in);
		String prenom = prenomMembre;
		String nom1 = nomMembre;
		String nom = prenomMembre + nomMembre;
		while (nom.length() > 25 || !FormatLettre(nom)) {
			if (nom.length() > 25)
				System.out.println("Nombre de caractères trop élévés, maximum de 25 lettres");
			else if (!FormatLettre(nom))
				System.out.println("Mauvais format");
			System.out.println("Veuillez entrez le prénom du membre:");
			prenom = sc.nextLine();
			System.out.println("Veuillez entrez le nom du membre:");
			nom1 = sc.nextLine();
		}
		String nomComplet[] = new String[2];
		nomComplet[0] = prenom;
		nomComplet[1] = nom1;

		return (nomComplet);
	}

	public String ValiderAdresse(String adresseMembre) {
		Scanner sc = new Scanner(System.in);
		String adresse = adresseMembre;
		while (adresseMembre.length() > 25 || !FormatAdresse(adresseMembre)) {
			if (adresseMembre.length() > 25)
				System.out.println("Nombre de caractères trop élévés, maximum de 25 lettres");
			else if (!FormatAdresse(adresseMembre))
				System.out.println("Mauvais format");
			System.out.println("Veuillez entrez l'adresse du membre.");
			adresse = sc.nextLine();
		}
		return adresse;
	}

	public String ValiderVille(String villeMembre) {
		Scanner sc = new Scanner(System.in);
		String ville = villeMembre;
		while (ville.length() > 14) {
			System.out.println("Nombre de caractères trop élévés, maximum de 14 caracteres");
			System.out.println("Veuillez entrez la ville de l'adresse du membre.");
			ville = sc.nextLine();
		}
		return ville;
	}

	public String ValiderProvince(String provinceMembre) {
		Scanner sc = new Scanner(System.in);
		String province = provinceMembre;
		while (province.length() > 2 || !FormatLettre(province)) {
			if (province.length() > 2)
				System.out.println("Nombre de caractÃ¨res trop élévés, maximum de 2 lettres");
			else if (!FormatLettre(province))
				System.out.println("Mauvais format");
			System.out.println("Veuillez entrez la province de l'adresse du membre.");
			province = sc.nextLine();
		}
		return province;
	}

	public String ValiderCodePostal(String codePostalMembre) {
		Scanner sc = new Scanner(System.in);
		String codePostal = codePostalMembre;
		while (!FormatCodePostal(codePostal)) {
			System.out.println("Mauvais format");
			System.out.println("Veuillez entrez le code postal de l'adresse du membre.");
			codePostal = sc.nextLine();
		}
		return codePostal;
	}

	public String ValiderCourielMembre(String courielMembre) {
		Scanner sc = new Scanner(System.in);
		String couriel = courielMembre;
		while (!FormatCourielMembre(couriel)) {
			System.out.println("Mauvais format");
			System.out.println("Veuillez entrez le couriel du membre:");
			couriel = sc.nextLine();
		}
		return couriel;
	}

	public LocalDate ValiderDateDebut() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez la date de début du service(Exemple : 01-12-1999)");
		String datedebut = sc.nextLine();
		while (!FormatDate(datedebut) || !isValidDate(datedebut) || StringtoDate(datedebut).isBefore(LocalDate.now())) {
			if (!FormatDate(datedebut)) {
				System.out.println("Mauvais format");
				System.out.println("Veuillez entrez la date de début du service(Exemple : 01-12-1999)");
				datedebut = sc.nextLine();
			} else if (!isValidDate(datedebut)) {
				System.out.println("La date n'existe pas");
				System.out.println("Veuillez entrez la date de début du service(Exemple : 01-12-1999)");
				datedebut = sc.nextLine();
			}

			else if (StringtoDate(datedebut).isBefore(LocalDate.now())) {
				System.out.println("La date de début ne peut pas être avant aujourd'hui");
				System.out.println("Veuillez entrez la date de début du service(Exemple : 01-12-1999)");
				datedebut = sc.nextLine();
			}
		}
		return StringtoDate(datedebut);
	}

	public LocalDate ValiderDateFin(LocalDate datedebut) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez la date de fin du service (Exemple : 01-12-1999)");
		String datefin = sc.nextLine();
		while (!FormatDate(datefin) || !isValidDate(datefin) || StringtoDate(datefin).isBefore(LocalDate.now())
				|| StringtoDate(datefin).isBefore(datedebut)) {
			if (!FormatDate(datefin)) {
				System.out.println("Mauvais format");
				System.out.println("Veuillez entrez la date de fin du service (Exemple : 01-12-1999)");
				datefin = sc.nextLine();
			} else if (!isValidDate(datefin)) {
				System.out.println("La date n'existe pas");
				System.out.println("Veuillez entrez la date de fin du service(Exemple : 01-12-1999)");
				datefin = sc.nextLine();
			} else if (StringtoDate(datefin).isBefore(LocalDate.now())) {
				System.out.println("La date de fin ne peut pas être avant aujourd'hui");
				System.out.println("Veuillez entrez la date de fin du service(Exemple : 01-12-1999)");
				datefin = sc.nextLine();
			} else if (StringtoDate(datefin).isBefore(datedebut)) {
				System.out.println("La date de fin ne peut pas être avant la date de debut");
				System.out.println("Veuillez entrez la date de fin du service(Exemple : 01-12-1999)");
				datefin = sc.nextLine();
			}
		}
		return StringtoDate(datefin);
	}

	public String ValiderHeure() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez l'heure du service(Exemple : 11:00)");
		String heure = sc.nextLine();
		while (!FormatHeure(heure)) {
			System.out.println("Mauvais format");
			System.out.println("Veuillez entrez l'heure du service(Exemple : 11:00)");
			heure = sc.nextLine();
		}
		return heure;
	}

	public String ValiderCapacite() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez la capacité maximale du service  (Max de 30)");
		String capacite = sc.nextLine();
		while (!isInteger(capacite) || 0 == Integer.parseInt(capacite) || 0 > Integer.parseInt(capacite)
				|| 30 < Integer.parseInt(capacite)) {
			if (!isInteger(capacite)) {
				System.out.println("Ce n'est pas un nombre");
				System.out.println("Veuillez entrez la capacité maximale du service  (Max de 30)");
				capacite = sc.nextLine();
			}

			else if (0 > Integer.parseInt(capacite) || 0 == Integer.parseInt(capacite)) {
				System.out.println("Capacité trop petite");
				System.out.println("Veuillez entrez la capacité maximale du service  (Max de 30)");
				capacite = sc.nextLine();
			} else if (30 < Integer.parseInt(capacite)) {
				System.out.println("Capacité trop grande");
				System.out.println("Veuillez entrez la capacité maximale du service  (Max de 30)");
				capacite = sc.nextLine();
			}

		}
		return capacite;
	}

	public String ValiderDescription() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez la description du service");
		String description = sc.nextLine();
		while (description.isEmpty()) {
			System.out.println("La description ne peut pas être vide.");
			System.out.println("Veuillez entrez la description du service");
			description = sc.nextLine();
		}
		return description;
	}

	public String ValiderCodeService(Map<String, Service> ListeService) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez le code du service (7 chiffres)");
		String codeservice = sc.nextLine();
		while (!FormatCodeService(codeservice) || ListeService.containsKey(codeservice)) {
			if (!FormatCodeService(codeservice)) {
				System.out.println("Mauvais format");
				System.out.println("Veuillez entrez le code du service (7 chiffres)");
				codeservice = sc.nextLine();
			} else if (ListeService.containsKey(codeservice)) {
				System.out.println("Ce code est déjà utilisé, veuillez en entrez un autre (7 chiffres)");
				codeservice = sc.nextLine();
			}
		}
		return codeservice;
	}

	public String ValiderFrais() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez les frais du service (Jusqu'à  100)");
		String frais = sc.nextLine();
		while (!FormatFrais(frais)) {
			System.out.println("Non valide, doit être un chiffre entre 0 et 100.");
			System.out.println("Veuillez entrez les frais du service (Jusqu'à  100)");
			frais = sc.nextLine();
		}
		return frais;
	}

	public String ValiderCommentaires() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrez les commentaires du service (facultatifs, 100 caractères max)");
		String commentaires = sc.nextLine();
		while (commentaires.length() > 100) {
			System.out.println("Trop long, 100 caractères max");
			System.out.println("Veuillez entrez les commentaires du service (facultatifs)");
			commentaires = sc.nextLine();
		}
		if (commentaires.equals("")) {
			commentaires = "Pas de commentaires";
		}
		return commentaires;
	}

	public LocalDate StringtoDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(date, formatter);
	}

	public String DatetoString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return formatter.format(date);
	}

}
