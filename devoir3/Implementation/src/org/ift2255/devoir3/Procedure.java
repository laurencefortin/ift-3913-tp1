package org.ift2255.devoir3;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
* La classe Procedure permet de générer toutes les procédures comptables du logiciel #GYM.
*
* @author  Olivier Provost
* @version 1.0
* @since   2019-12-01 
*/
public class Procedure {

	// ----------------- PROPRIÉTÉS ----------------- \\
	private Map<String, Membre> lstMembres;
	private Map<String, Service> lstServices;
	
	
	// ----------------- CONSTRUCTEUR ----------------- \\
	public Procedure() {}
	
	
	// ----------------- MÉTHODES ----------------- \\
	
	/**
	* Éxecution de la procèdure comptable pour les TEFs, le rapport du Gestionnaire et les rapports de Membres et de Professionnels.
	*/
	public void executer () {
		this.genererTEF();
		this.genererRapportGestionnaire();
		this.genererRapportsMembres();
		this.genererRapportsProfesionnels();
	}
	
	/**
	* Génération des TEFs de la denière semaine.
	*/
    private void genererTEF() {
    	String content;
    	String num;
    	String nom;
    	
		for (Membre membre : lstMembres.values()) {			
			if (membre.estProfessionnel()) {
				
				content = new String();
				num = membre.getNumero();
				nom = membre.getPrenom() + "_" + membre.getNom();
				
				int[] totaux = this.totauxProfessionnel(num);
				
				content += 
					("-----------------------------------------------------------------------------------------\n" +
					 "Numéro : " + num + "\n" +
		    	     "Prénom : " + membre.getPrenom() + "\n" +
		    	     "Nom : " + membre.getNom() + "\n\n" +
		    	     "Nombre de services : " + totaux[0] + "\n" +
		    	     "Paiement dû : " + totaux[1] + "$\n" +
		    		 "-----------------------------------------------------------------------------------------\n\n");
				
				this.ecrireFichier("./TEF/TEF_" + LocalDate.now() + "_" + nom, content);
			}
		}
		
		System.out.println("Les TEF ont été générés.\n");
    }
   
	/**
	* Génération du rapport du Gestionnaire de la dernière semaine.
	*/
    public void genererRapportGestionnaire() {
    	int nbProfessionnels = 0;
    	int nbServices = 0;
    	int paiementTotal = 0;
    	
    	LocalDate aujourdhui = LocalDate.now();
    	
    	String content = "Rapport du Gestionnaire en date du : " + aujourdhui + "\n\n";

    	for (Membre membre : this.lstMembres.values()) {
    		if(membre.estProfessionnel()) {	
    			int[] totaux = this.totauxProfessionnel(membre.getNumero());
    			
    			content += 
    			   ("-----------------------------------------------------------------------------------------\n" +
    				"Numéro : " + membre.getNumero() + "\n" +
    			    "Prénom : " + membre.getPrenom() + "\n" +
    			    "Nom : " + membre.getNom() + "\n\n" +
    			    "Nombre de services : " + totaux[0] + "\n" +
    			    "Paiement dû : " + totaux[1] + "$\n\n");
    			
    			// On ajoute le nombre de service et le total du paiment à ce professionnel au compteur pour tous.
    			nbProfessionnels += 1;
    			nbServices += totaux[0];
    			paiementTotal += totaux[1];
    		}
        }
    	
    	content += 
    	   ("-----------------------------------------------------------------------------------------\n" +
    	    "-----------------------------------------------------------------------------------------\n" +
    	    "Nombre total de Professionnels : " + nbProfessionnels + "\n" +
        	"Nombre total de Services offerts : " + nbServices + "\n" +
            "Total des paiments dûs : " + paiementTotal + "$\n\n");

    	System.out.println("Le rapport du Gestionnaire a été généré.\n");
    	this.ecrireFichier("./rapportsGestionnaire/rapport_" + aujourdhui + ".txt", content);
    }
    
	/**
	* Génération des rapports des Membres de la dernière semaine.
	*/
    private void genererRapportsMembres() {
		LocalDate demain = LocalDate.now().plusDays(1);
		LocalDate dernierSamedi = this.dernierSamedi();
		String contenu;
		String contenuS;
		
		for(Membre membre : this.lstMembres.values()) {
			
			contenu = new String();
			contenuS = new String();
			
			for(Service service : this.lstServices.values()) {
				for(Seance seance : service.getSeances().values()) {
					LocalDate date = seance.getDate();
					
					// Si le service a eu lieu durant la dernière semaine.
					if(!seance.getParticipations().isEmpty() && (date.isAfter(dernierSamedi) && date.isBefore(demain))) {

						// Si le membre a participé au service.
						if(seance.getParticipations().containsKey(membre.getNumero())) {
							Membre professionnel = this.lstMembres.get(service.getNumeroProfessionnel());
							
							contenuS += ("\t----------------------------------------------\n\t" +
										 "Date du service : " + date + "\n\t" +
										 "Nom du profesionnel : " + professionnel.getPrenom() + " " + professionnel.getNom() + "\n\t" +
										 "Description du service : " + service.getDescription() + "\n\n");
						}
					}
				}
			}
			
			// Le membre a participé à au moins 1 service durant la dernière semaine.
			if(!contenuS.isEmpty()) {
				contenu = "Rapport du Membre en date du : " + LocalDate.now() + "\n\n";
				contenu += "-----------------------------------------------------------------------------------------\n";
				contenu += membre.printAttributs();
				contenu += "\n\tServices fournis durant la dernière semaine (du " + dernierSamedi + " au " + LocalDate.now() + ") : \n";
				contenu += contenuS;
				
				this.ecrireFichier("./rapportsMembres/" + membre.getPrenom() + "_" + membre.getNom() + "_" + LocalDate.now() + ".txt", contenu);
			}
		}
		
		System.out.println("Les rapports des Membres ont été générés.\n");
    }

    
	/**
	* Génération des rapports des Professionnels de la dernière semaine.
	*/
    private void genererRapportsProfesionnels() {
    	LocalDate demain = LocalDate.now().plusDays(1);
		LocalDate dernierSamedi = this.dernierSamedi();
		
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		String contenu;
		String contenuS;
		
		for(Membre professionnel : this.lstMembres.values()) {
			
			if(professionnel.estProfessionnel()) {				
				contenu = new String();
				contenuS = new String();
				
				for(Service service : this.lstServices.values()) {
					
					if(service.getNumeroProfessionnel().equals(professionnel.getNumero())) {
						
						for(Seance seance : service.getSeances().values()) {
							LocalDate date = seance.getDate();
							
							// Si le service a eu lieu durant la dernière semaine.
							if(!seance.getParticipations().isEmpty() && (date.isAfter(dernierSamedi) && date.isBefore(demain))) {
								
								contenuS += (
									"\t----------------------------------------------\n\t" +
									"Date du service : " + date + "\n\t" +
									"Date création : " + service.getDateCreation().format(formatDateTime) + "\n\t" +
									"Code de la Séance : " + seance.getCode() + "\n\t" +
									"Montant : " + service.getFrais() + "$\n\t" +
									"Membres auxquels le Service a été fourni : \n\n" 
								);
								
								for(Participation participation : seance.getParticipations().values()) {
									
									Membre membreTemp = lstMembres.get(participation.getNumeroMembre());
									
									contenuS += (
										"\t\tNom du Membre : " + membreTemp.getPrenom() + " " + membreTemp.getNom() + "\n\t\t" +
										"Numéro du Membre : " + membreTemp.getNumero() + "\n\n"
									);
								}
							}
						}
					}
					
				}
				
				// Le membre a participé à au moins 1 service durant la dernière semaine.
				if(!contenuS.isEmpty()) {
					contenu = "Rapport du Professionnel en date du : " + LocalDate.now() + "\n\n";
					contenu += "-----------------------------------------------------------------------------------------\n";
					contenu += professionnel.printAttributs();
					contenu += "\n\tServices fournis durant la dernière semaine (du " + dernierSamedi + " au " + LocalDate.now() + ") : \n";
					contenu += contenuS;
					
					this.ecrireFichier("./rapportsProfessionnels/" + professionnel.getPrenom() + "_" + professionnel.getNom() + "_" + LocalDate.now() + ".txt", contenu);
				}
			}
			
		}
		
		System.out.println("Les rapports des Professionnels ont été générés.\n");
    }
    
	/**
	* Écriture d'un fichier quelconque sur le disque.
	* 
	* @param filename spécifie le chemin et le nom du fichier à écrire.
	* @param contenu spécifie le contenu à écrire dans le fichier.
	*/
    private void ecrireFichier(String filename, String contenu) {
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
	* Compte les totaux du nombre de Services et des frais de Service associé à un Professionnel durant la dernière semaine.
	* 
	* @param numeroProfessionnel du professionnel auquel on veut connaître les totaux.
	* @return un tableau de deux entiers, 
	* 	le premier correspond au nombre total de Services et 
	* 	le deuxième au total des frais de Services.
	*/
	private int[] totauxProfessionnel(String numeroProfessionnel) {
		LocalDate demain = LocalDate.now().plusDays(1);
		LocalDate dernierSamedi = this.dernierSamedi();
		int[] total = new int[] {0, 0};

		
		for (Service service : this.lstServices.values()) {
			// On trouve les Services du Professionnel.
			if (service.getNumeroProfessionnel().equals(numeroProfessionnel)) {
				
				// Si ce Service s'est donné durant la dernière semaine.
				for(Seance seance: service.getSeances().values()) {
					
					LocalDate date = seance.getDate();
					if(!seance.getInscriptions().isEmpty()) {
						if (date.isAfter(dernierSamedi) && date.isBefore(demain)) {
							total[0] += 1;
							total[1] += service.getFrais() * seance.getCapacite();
							
						}
					}
				}
			}
		}
		
		return total;
	}
    	
	
	private LocalDate dernierSamedi() {
		LocalDate dernierSamedi = LocalDate.now();
		
		// On recule la date jusqu'au dernier samedi (6).
		while (dernierSamedi.getDayOfWeek().getValue() != 6) {
			dernierSamedi = dernierSamedi.minusDays(1);
		}
		
		return dernierSamedi;
	}
	
	// ----------------- SETTERs ----------------- \\
	public void setLstMembres(Map<String, Membre> lstMembres) {
		this.lstMembres = lstMembres;
	}

	public void setLstServices(Map<String, Service> lstServices) {
		this.lstServices = lstServices;
	}
}
