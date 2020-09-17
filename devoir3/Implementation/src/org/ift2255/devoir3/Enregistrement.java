package org.ift2255.devoir3;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
* La classe Enregistrement permet d'enregistrer toutes les informations requises lors d'un enregistrement fait au Centre des Données. Elle sert de classe Interface pour les classes Participation et Inscription.
*
* @author  Olivier Provost
* @version 1.0
* @since   2019-12-01
*/
public class Enregistrement {

	// ----------------- PROPRIÉTÉS ----------------- \\
	protected String numeroMembre;
	protected String numeroProfessionnel;
	protected String codeService;
	protected String codeSeance;
	protected LocalDateTime dateCreation;
	protected LocalDate dateSeance;
	protected String commentaires;

	
	// ----------------- CONSTRUCTEUR ----------------- \\
	public Enregistrement(String numeroMembre, String numeroProfessionnel, String codeService, String codeSeance, String commentaires) {
		
		this.setNumeroMembre(numeroMembre);
		this.setNumeroProfessionnel(numeroProfessionnel);
		this.setCodeService(codeService);
		this.setCodeSeance(codeSeance);
		this.setDateCreation(LocalDateTime.now());
		this.setDateSeance(LocalDate.now());
		this.setCommentaires(commentaires);
	}
	
	public Enregistrement(String numeroMembre, String numeroProfessionnel, String codeService, String codeSeance, LocalDateTime dateCreation, String commentaires) {
		
		this.setNumeroMembre(numeroMembre);
		this.setNumeroProfessionnel(numeroProfessionnel);
		this.setCodeService(codeService);
		this.setCodeSeance(codeSeance);
		this.setDateCreation(dateCreation);
		this.setDateSeance(dateCreation.toLocalDate());
		this.setCommentaires(commentaires);
	}

	
	// ----------------- GETTERs & SETTERs ----------------- \\
	
	public String getNumeroMembre() {
		return numeroMembre;
	}


	public void setNumeroMembre(String numeroMembre) {
		this.numeroMembre = numeroMembre;
	}


	public String getNumeroProfessionnel() {
		return numeroProfessionnel;
	}


	public void setNumeroProfessionnel(String numeroProfessionnel) {
		this.numeroProfessionnel = numeroProfessionnel;
	}


	public String getCodeService() {
		return codeService;
	}


	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}


	public String getCodeSeance() {
		return codeSeance;
	}

	public void setCodeSeance(String codeSeance) {
		this.codeSeance = codeSeance;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}


	public LocalDate getDateSeance() {
		return dateSeance;
	}


	public void setDateSeance(LocalDate dateSeance) {
		this.dateSeance = dateSeance;
	}


	public String getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
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
