package org.ift2255.devoir3;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Seance est la classe qui permet de désigner une représentation d'un Service à une Date donnée.
 * 
 * @author Julien Lanctot
 * @version 2.0
 * @since 2019-12-01
 */
public class Seance {

	// ----------------- PROPRIÉTÉS ----------------- \\
	private String code;
	private LocalDate date;
	private Map<String, Inscription> inscriptions;
	private Map<String, Participation> participations;
	
	
	// ----------------- CONSTRUCTEUR ----------------- \\
	public Seance(LocalDate date, String code) {
		this.setCode(code);
		this.setDate(date);
		
		this.inscriptions = new HashMap<>();
		this.participations = new HashMap<>();
	}
	
	
	// ----------------- MÉTHODES ----------------- \\
	/*Méthode qui ajoute les inscription a la sénace
	 * 
	 * @param inscription qui est a ajouter
	 */
	public void ajouterInscription(Inscription inscription) {
		this.inscriptions.put(inscription.getNumeroMembre(), inscription);
	}
	/*Méthode qui ajoute les participations a la sénace
	 * 
	 * @param participation qui est a ajouter
	 */
	public void ajouterParticipation(Participation participation) {
		this.participations.put(participation.getNumeroMembre(), participation);
	}
	/*Méthode qui verifie le nombre d'inscription 
	 * 
	 * @param int num inscription
	 */
	public int getCapacite() {
		return inscriptions.size();
	}

	
	// ----------------- GETTERs & SETTERs ----------------- \\
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Map<String, Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(Map<String, Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public Map<String, Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Map<String, Participation> participations) {
		this.participations = participations;
	}

}