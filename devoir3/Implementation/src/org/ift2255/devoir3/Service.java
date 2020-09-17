package org.ift2255.devoir3;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe service permet de créer les différents Service du Gym
 * @author Julien Lanctot et Olivier Provost
 * @version 3.0
 * @since 2019-09-01
 */
public class Service {

	// ----------------- PROPRIÉTÉS ----------------- \\
	private String code;
	private String numeroProfessionnel;
	private String description;
	private LocalDateTime dateCreation;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String heure;
	private boolean[] recurrences; // lundi = 0, mardi = 1 ... dimanche = 6
	private int capacite;
	private int frais;
	private String commentaires;

	private Map<LocalDate, Seance> seances;

	// ----------------- CONSTRUCTEURS ----------------- \\
	public Service(String code, String numeroProfessionnel, String description, LocalDate dateDebut, LocalDate dateFin,
			String heure, boolean[] recurrences, int capacite, int frais, String commentaires) {

		this.setCode(code);
		this.setNumeroProfessionnel(numeroProfessionnel);
		this.setDescription(description);
		this.setDateCreation(LocalDateTime.now());
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setHeure(heure);
		this.setRecurrences(recurrences);
		this.setCapacite(capacite);
		this.setFrais(frais);
		this.setCommentaires(commentaires);

		this.seances = new HashMap<>();

		genererSeances();
	}

	public Service(String code, String numeroProfessionnel, String description, LocalDateTime dateCreation, LocalDate dateDebut,
			LocalDate dateFin, String heure, boolean[] recurrences, int capacite, int frais, String commentaires) {

		this.setCode(code);
		this.setNumeroProfessionnel(numeroProfessionnel);
		this.setDescription(description);
		this.setDateCreation(dateCreation);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setHeure(heure);
		this.setRecurrences(recurrences);
		this.setCapacite(capacite);
		this.setFrais(frais);
		this.setCommentaires(commentaires);

		this.seances = new HashMap<>();

		genererSeances();
	}

	// ----------------- MÉTHODES ----------------- \\

	/**
	 * Méthode qui génère toutes les séances d'un service
	 * 
	 */	
	public void genererSeances() {

		DecimalFormat formatter = new DecimalFormat("00");
		int count = 0;
		Map<LocalDate, Seance> Seances = new HashMap<>();

		LocalDate date = this.getDateDebut();
		for (int i = 0; i < 7; i++) {
			if (recurrences[i]) {
				date = this.getDateDebut();
				
				// DayofWeek : lundi = 1....dimanche = 7
				while (date.getDayOfWeek().getValue() != i + 1) {
					date = date.plusDays(1);
				}
				
				while (!date.isAfter(this.getDateFin())) {
					String SeanceCourante = formatter.format(count);
					String CodeSeance = this.getCode().substring(0, 3) +
										SeanceCourante + 
										this.getNumeroProfessionnel().substring(7);
					
					Seances.put(date, new Seance(date, CodeSeance));
					date = date.plusWeeks(1);
					count++;
				}
			}
		}
		
		setSeances(Seances);
	}
	/**
	 * Méthode qui permet ajoute les inscription a la séance 
	 * 
	 * @param inscription qui est celle ajouter
	 */
	public void ajouterInscription(Inscription inscription) {
		this.seances.get(inscription.getDateSeance()).ajouterInscription(inscription);
	}
	/**
	 * Méthode qui permet ajoute les participation a la séance 
	 * 
	 * @param participation qui est celle ajouter
	 */
	public void ajouterParticipation(Participation participation) {
		this.seances.get(participation.getDateSeance()).ajouterParticipation(participation);
	}
	
	/**
	 * Méthode qui affiche tous les attributs
	 * du service
	 * 
	 */

	public void printAttributs() {
		System.out.println("Informations du Service : \n");
		System.out.println("Code : " + this.getCode());
		System.out.println("Numéro du professionel : " + this.getNumeroProfessionnel());
		System.out.println("Description : " + getDescription());
		System.out.println("Date de début : " + this.getDateDebut().toString());
		System.out.println("Date de fin : " + this.getDateFin().toString());
		System.out.println("Heure : " + this.getHeure());
		System.out.println("Récurrence : " + BooltoDays());
		System.out.println("Capacité maximum : " + this.getCapacite());
		System.out.println("Frais : " + this.getFrais());
		System.out.println("Commentaires : " + this.getCommentaires() + "\n");
	}

	/**
	 * Méthode qui associe les jours de la semaine
	 * de la récurence du service
	 * @return le jours ou il y a des séances
	 * 
	 */
	public String BooltoDays() {
		String jours = "";
		if (recurrences[0])
			jours += "Lundi, ";
		if (recurrences[1])
			jours += "Mardi, ";
		if (recurrences[2])
			jours += "Mercredi, ";
		if (recurrences[3])
			jours += "Jeudi, ";
		if (recurrences[4])
			jours += "Vendredi, ";
		if (recurrences[5])
			jours += "Samedi, ";
		if (recurrences[6])
			jours += "Dimanche";

		return jours;
	}
	/**
	 * Méthode qui met la résurence en string 
	 * @return la string de la récurence du service
	 */
	public String getRecurrencesToString() {
		String recurrence = new String();
		
		for(int i = 0; i < this.recurrences.length; i++){
			if (this.recurrences[i]) recurrence += "true";
			else recurrence += "false";
			
			if(i != 6) recurrence += "-";
		}
		
		return recurrence;
	}

	// ----------------- GETTERs & SETTERs ----------------- \\
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNumeroProfessionnel() {
		return numeroProfessionnel;
	}

	public void setNumeroProfessionnel(String numeroProfessionnel) {
		this.numeroProfessionnel = numeroProfessionnel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public boolean[] getRecurrences() {
		return recurrences;
	}

	public void setRecurrences(boolean[] recurrences) {
		this.recurrences = recurrences;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getFrais() {
		return frais;
	}

	public void setFrais(int frais) {
		this.frais = frais;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public Map<LocalDate, Seance> getSeances() {
		return seances;
	}

	public void setSeances(Map<LocalDate, Seance> seances) {
		this.seances = seances;
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
