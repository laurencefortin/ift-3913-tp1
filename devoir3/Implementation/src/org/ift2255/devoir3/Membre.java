package org.ift2255.devoir3;

/**
 * La classe Membre permet d'enregistrer toutes les informations d'un Membre du
 * #GYM.
 *
 * @author Julien Lanctot et Olivier Provost
 * @version 3.0
 * @since 2019-09-01
 */
public class Membre {

	// ----------------- PROPRIÉTÉS ----------------- \\
	private String numero;
	private String prenom;
	private String nom;
	private boolean estProfessionnel;
	private String courriel;
	private String adresse;
	private String ville;
	private String province;
	private String codePostal;
	private int balance;

	// ----------------- CONSTRUCTEUR ----------------- \\
	public Membre(String numero, String prenom, String nom, boolean estProfessionnel, String courriel, String adresse,
			String ville, String province, String codePostal, int balance) {
		this.setNumero(numero);
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setEstProfessionnel(estProfessionnel);
		this.setCourriel(courriel);
		this.setAdresse(adresse);
		this.setVille(ville);
		this.setProvince(province);
		this.setCodePostal(codePostal);
		this.setBalance(balance);
	}

	// ----------------- MÉTHODES ----------------- \\

	/**
	 * Méthode qui permet incrémenter la balance du membre
	 * 
	 * @param frais qui indique le montant de l'incémentation
	 */
	public void incrementerBalance(int frais) {
		this.balance += frais;
	}

	/**
	 * Méthode qui permet décrémenter la balance du membre
	 * 
	 * @param paiement qui indique le montant de la décrémentation
	 */
	public void decrementerBalance(int paiement) {
		this.balance -= paiement;
	}

	/**
	 * Méthode qui permet de retourner les informations d'un Membre pour impression.
	 * 
	 * @return une string avec les attributs du Membre.
	 */
	public String printAttributs() {
		String membre = new String();

		membre += ("Nom : " + this.prenom + " " + this.nom + "\n" + "Numéro : " + this.numero + "\n" + "Adresse : "
				+ this.adresse + "\n" + "Ville : " + this.ville + "\n" + "Province: " + this.province + "\n"
				+ "Code postal : " + this.codePostal + "\n");

		return membre;
	}

	// ----------------- GETTERs & SETTERs ----------------- \\
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean estProfessionnel() {
		return estProfessionnel;
	}

	public void setEstProfessionnel(boolean estProfessionnel) {
		this.estProfessionnel = estProfessionnel;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
