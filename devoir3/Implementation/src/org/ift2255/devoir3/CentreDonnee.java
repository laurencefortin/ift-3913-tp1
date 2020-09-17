package org.ift2255.devoir3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* La classe CentreDonnee permet d'écrire et de lire l'information des Membres, Services, Inscriptions et Participations du logiciel #GYM.
* Le tout à partir d'une structure de différents fichiers CSVs.
*
* @author  Julien Lanctot et Olivier Provost
* @version 2.0
* @since   2019-11-01
*/
public class CentreDonnee {

	// Délimiteur et saut de ligne utilisé dans les fichiers CSVs.
	private static final String DELIMITER = ", ";
	private static final String NEW_LINE = "\n";

	
	// Nom de fichiers CSVs.
	private static final String FICHIER_MEMBRES = "membres.csv";
	private static final String FICHIER_SERVICES = "services.csv";
	private static final String FICHIER_INSCRIPTIONS = "inscriptions.csv";
	private static final String FICHIER_PARTICIPATIONS = "participations.csv";
	
	
	// Listes.
	private static Map<String, Membre> lstMembres;
	private static Map<String, Service> lstServices;
	
	
	// ----------------- MÉTHODES ----------------- \\
	
	/**
	* Permet la lecture du contenu d'un fichier CSV.
	*
	* @param filename spécifie le fichier CSV à lire.
	* @return chaque lignes du fichier sous forme de tableau de String.
	*/
	private static ArrayList<String> lireCSV(String filename) {
		BufferedReader fileReader = null;
		ArrayList<String> items = new ArrayList<String>();

		try {
			String line = new String();

			// Création du lecteur de fichier.
			fileReader = new BufferedReader(new FileReader(filename));

			// On saute la première ligne du fichier (l'entête).
			fileReader.readLine();

			// On lit le fichier ligne par ligne.
			while ((line = fileReader.readLine()) != null) {
				items.add(line);
			}
			
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture de : " + filename);
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Erreur lors de la fermeture de : " + filename);
				e.printStackTrace();
			}
		}

		return items;
	}

	/**
	* Permet de lire le contenu du fichier membres.csv et retourner les objets Membres décrit dans ce fichier.
	*
	* @return un dictionnaire de Membres avec son numéro comme clé.
	*/
	public static Map<String, Membre> lireMembres() {
		lstMembres = new HashMap<>();
		ArrayList<String> membres = lireCSV(FICHIER_MEMBRES);
		
		membres.forEach((m) -> {
			String[] props = m.split(DELIMITER);
			
			if(props.length > 0) {
				
				Membre membreTemp = new Membre(
					props[0],                       // numero
					props[1],                       // prenom
					props[2],                       // nom
					Boolean.parseBoolean(props[3]), // estProfessionnel
					props[4],                       // courriel
					props[5],                       // adresse
					props[6],                       // ville
					props[7],                       // province
					props[8],                       // codePostal,
					Integer.parseInt(props[9])      // balance
				);
				
				lstMembres.put(membreTemp.getNumero(), membreTemp);
			}
		});
		
		return lstMembres;
	}

	/**
	* Permet de lire le contenu du fichier services.csv et retourner les objets Services décrit dans ce fichier.
	*
	* @return un dictionnaire de Services avec son code comme clé.
	*/
	public static Map<String, Service> lireServices() {
		lstServices = new HashMap<>();
		ArrayList<String> services = lireCSV(FICHIER_SERVICES);
		
		services.forEach((s) -> {
			String[] props = s.split(DELIMITER);
			
			if(props.length > 0) {
				
				String[] recurrencesTemp = props[7].split("-");
				boolean[] recurrences = new boolean[recurrencesTemp.length];
				
				for(int i = 0; i < recurrences.length; i++) {
					recurrences[i] = Boolean.parseBoolean(recurrencesTemp[i]);
				}
				
				Service serviceTemp = new Service(
					props[0],                      // code
					props[1],                      // numero professionnel
					props[2],                      // description
					LocalDateTime.parse(props[3]), // dateCreation
					LocalDate.parse(props[4]),     // dateDebut
					LocalDate.parse(props[5]),     // dateFin
					props[6],                      // heure
					recurrences,                   // recurrence
					Integer.parseInt(props[8]),    // capacite
					Integer.parseInt(props[9]),    // frais
					props[10]                      // commentaires
				);
				
				lstServices.put(serviceTemp.getCode(), serviceTemp);
			}
		});
		
		lireInscriptions();
		lireParticipations();
		
		return lstServices;
	}

	/**
	* Permet de lire le contenu du fichier inscriptions.csv et ajouter les Inscriptions aux bons Services dans la liste des Services.
	*/
	private static void lireInscriptions() {
		ArrayList<String> inscriptions = lireCSV(FICHIER_INSCRIPTIONS);
		
		inscriptions.forEach((i) -> {
			String[] props = i.split(DELIMITER);
			
			if(props.length > 0) {
				
				Inscription inscriptionTemp = new Inscription(
					props[0],                                      // numeroMembre
					props[1],                                      // numeroProfessionnel
					props[2],                                      // codeService
					props[3],									   // codeSeance
					LocalDateTime.parse(props[4]),				   // dateCreation
					props[6]                                       // commentaires
				);
				
				lstServices.get(inscriptionTemp.getCodeService()).ajouterInscription(inscriptionTemp);
			}
		});
	}

	/**
	* Permet de lire le contenu du fichier participations.csv et ajouter les Participations aux bons Services dans la liste des Services.
	*/
	private static void lireParticipations() {
		ArrayList<String> participations = lireCSV(FICHIER_PARTICIPATIONS);
		
		participations.forEach((p) -> {
			String[] props = p.split(DELIMITER);
			
			if(props.length > 0) {
				
				Participation participationTemp = new Participation(
					props[0],                                      // numeroMembre
					props[1],                                      // numeroProfessionnel
					props[2],                                      // codeService
					props[3],									   // codeSeance
					LocalDateTime.parse(props[4]),				   // dateCreation
					props[6]                                       // commentaires
				);
				
				lstServices.get(participationTemp.getCodeService()).ajouterParticipation(participationTemp);
			}
		});
	}
	
	
	/**
	* Permet l'écriture de contenu d'un fichier CSV.
	*
	* @param filename spécifie le fichier CSV à écrire.
	* @param contenu le contenu à écrire dans le fichier.
	*/
	private static void ecrireCSV(String filename, String contenu) {
		
		BufferedWriter fileWriter = null;
		
		try {
			fileWriter = new BufferedWriter(new FileWriter(filename));
			fileWriter.write(contenu);
			fileWriter.close();
		} catch (Exception e) {
			System.out.println("Erreur lors de l'écriture de : " + filename);
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Erreur lors de la fermeture de : " + filename);
				e.printStackTrace();
			}
		}
	}

	/**
	* Permet de générer un entête de fichier CSV à partir des attributs d'une classe
	*
	* @param classe qu'on veut générer l'entête
	* @return l'entête en question.
	*/
	private static <T> String enteteFichier(Class<T> classe) {
		StringBuilder entete = new StringBuilder();
		
	    for (Field field : classe.getDeclaredFields()) {
	    	
	    	if(!field.getName().contains("seances"))
	    		entete.append(field.getName()).append(DELIMITER);
	    }
	    
	    entete.append(NEW_LINE);
	    
	    return entete.toString();
	}
	
	
	/**
	* Permet l'écriture des Membres sur le disque.
	*
	* @param membres à écrire.
	*/
	public static void ecrireMembres(Map<String, Membre> membres) {
		StringBuilder contenu = new StringBuilder();
		
		contenu.append(enteteFichier(Membre.class));
		
		for(Membre m : membres.values()) {
			contenu.append(m.getNumero()).append(DELIMITER);
			contenu.append(m.getPrenom()).append(DELIMITER);
			contenu.append(m.getNom()).append(DELIMITER);
			contenu.append(m.estProfessionnel()).append(DELIMITER);
			contenu.append(m.getCourriel()).append(DELIMITER);
			contenu.append(m.getAdresse()).append(DELIMITER);
			contenu.append(m.getVille()).append(DELIMITER);
			contenu.append(m.getProvince()).append(DELIMITER);
			contenu.append(m.getCodePostal()).append(DELIMITER);
			contenu.append(m.getBalance()).append(DELIMITER);
			contenu.append(NEW_LINE);
		}
		
		ecrireCSV(FICHIER_MEMBRES, contenu.toString());
	}
	
	/**
	* Permet l'écriture des Services sur le disque.
	*
	* @param services à écrire.
	*/
	public static void ecrireServices(Map<String, Service> services) {
		StringBuilder contenuS = new StringBuilder();
		StringBuilder contenuI = new StringBuilder();
		StringBuilder contenuP = new StringBuilder();
		
		contenuS.append(enteteFichier(Service.class));
		contenuI.append(enteteFichier(Enregistrement.class));
		contenuP.append(enteteFichier(Enregistrement.class));
		
		for(Service s : services.values()) {
			
			contenuS.append(ecrireService(s)).append(NEW_LINE);
			
			for(Seance se : s.getSeances().values()) {
				
				for(Inscription i : se.getInscriptions().values()) {
					contenuI.append(ecrireEnregistrement(i)).append(NEW_LINE);
				}
				
				for(Participation p : se.getParticipations().values()) {
					contenuP.append(ecrireEnregistrement(p)).append(NEW_LINE);
				}
			}
		}
		
		ecrireCSV(FICHIER_SERVICES, contenuS.toString());
		ecrireCSV(FICHIER_INSCRIPTIONS, contenuI.toString());
		ecrireCSV(FICHIER_PARTICIPATIONS, contenuP.toString());
	}

	/**
	* Permet de former une String des informations d'un Service.
	*
	* @param Service à transformer en String.
	* @return String du Service.
	*/
	private static String ecrireService(Service service) {
		StringBuilder contenu = new StringBuilder();
				
		contenu.append(service.getCode()).append(DELIMITER);
		contenu.append(service.getNumeroProfessionnel()).append(DELIMITER);
		contenu.append(service.getDescription()).append(DELIMITER);
		contenu.append(service.getDateCreation()).append(DELIMITER);
		contenu.append(service.getDateDebut()).append(DELIMITER);
		contenu.append(service.getDateFin()).append(DELIMITER);
		contenu.append(service.getHeure()).append(DELIMITER);
		contenu.append(service.getRecurrencesToString()).append(DELIMITER);
		contenu.append(service.getCapacite()).append(DELIMITER);
		contenu.append(service.getFrais()).append(DELIMITER);
		contenu.append(service.getCommentaires()).append(DELIMITER);

		return contenu.toString();
	}
	
	/**
	* Permet de former une String des informations d'un Enregistrement.
	*
	* @param Enregistrement à transformer en String.
	* @return String de l'Enregistrement.
	*/
	private static String ecrireEnregistrement(Enregistrement enregistrement) {
		StringBuilder contenu = new StringBuilder();
		
		contenu.append(enregistrement.getNumeroMembre()).append(DELIMITER);
		contenu.append(enregistrement.getNumeroProfessionnel()).append(DELIMITER);
		contenu.append(enregistrement.getCodeService()).append(DELIMITER);
		contenu.append(enregistrement.getCodeSeance()).append(DELIMITER);
		contenu.append(enregistrement.getDateCreation()).append(DELIMITER);
		contenu.append(enregistrement.getDateSeance()).append(DELIMITER);
		contenu.append(enregistrement.getCommentaires()).append(DELIMITER);
		
		return contenu.toString();
	}
	
}