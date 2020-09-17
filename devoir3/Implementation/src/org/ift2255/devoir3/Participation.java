package org.ift2255.devoir3;
import java.time.LocalDateTime;


/**
* La classe Participation permet de spécifier la classe Enregistrement et donc elle en hérite.
*
* @author  Julien Lanctot et Olivier Provost
* @version 2.0
* @since   2019-12-01
*/
public class Participation extends Enregistrement {

	// ----------------- CONSTRUCTEURS ----------------- \\
	public Participation(String numeroMembre, String numeroProfessionnel, String codeService, String codeSeance,
			LocalDateTime dateCreation, String commentaires) {
		super(numeroMembre, numeroProfessionnel, codeService, codeSeance, dateCreation, commentaires);
	}

	public Participation(String numeroMembre, String numeroProfessionnel, String codeService, String codeSeance, String commentaires) {
		super(numeroMembre, numeroProfessionnel, codeService, codeSeance, commentaires);
	}

}

