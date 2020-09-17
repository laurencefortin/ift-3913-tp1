package org.ift2255.devoir3;
import java.time.LocalDateTime;


/**
* La classe Inscription permet de spécifier la classe Enregistrement et donc elle en hérite.
*
* @author  Julien Lanctot et Olivier Provost
* @version 2.0
* @since   2019-12-01
*/
public class Inscription extends Enregistrement {

	// ----------------- CONSTRUCTEURS ----------------- \\
	public Inscription(String numeroMembre, String numeroProfessionnel, String codeService, String codeSeance,
			LocalDateTime dateCreation, String commentaires) {
		super(numeroMembre, numeroProfessionnel, codeService, codeSeance, dateCreation, commentaires);
	}

	
	public Inscription(String numeroMembre, String numeroProfessionnel, String codeService, String codeSeance, String commentaires) {
		super(numeroMembre, numeroProfessionnel, codeService, codeSeance, commentaires);
	}
}
