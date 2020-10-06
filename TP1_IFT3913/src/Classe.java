import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Classe {
	private File fichierComplet;
	private List<String>  contenueFichier;
	public List<Methode>  methodes;
	
//CONSTRUCTEUR
	public Classe(File file) {
		this.fichierComplet = file;
		this.contenueFichier = lireFicherEnOrdre(fichierComplet.getAbsolutePath());
		this.methodes = new ArrayList<Methode>();
		trouverMethodes();
	}
	
	public File getFullFile() {
		return fichierComplet;
	}

	public List<String> getFileContent() {
		return contenueFichier;
	}

	public List<Methode> getMethods() {
		return methodes;
	}

	/**
	 * @param le nom du fichier que l'on veut evaluer
	 * @return retourne une liste de string qui contiennent du text*/
	public List<String> lireFicherEnOrdre(String nomFichier) 
	  { 
	    List<String> lines = Collections.emptyList(); 
	    try
	    { 
	      lines = Files.readAllLines(Paths.get(nomFichier), StandardCharsets.ISO_8859_1); 
	    } 
	    catch (IOException e) 
	    { 
	    	 
	      e.printStackTrace(); 
	    } 
	    return lines; 
	  } 
	/**
	 * Permet de trouver les methodes d'une classe specifique sur laquelle la fonction est appeler*/
	public void trouverMethodes() {
		String regex = ".*(public|protected|private|static) +[\\w\\<\\>\\[\\],\\s]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
		
		for (String temp : contenueFichier) 
		{
			if(temp.matches(regex)) 
			{
				String separationMethode1[] = StringUtils.substringBetween(temp, "", "(").split(" ");
				String separationMethode2 = StringUtils.substringBetween(temp, separationMethode1[separationMethode1.length - 1], ")");
				String signature = separationMethode1[separationMethode1.length - 1] + separationMethode2 + ")";
				System.out.println(signature);
				methodes.add(new Methode(signature, temp, contenueFichier));
			}
        }		
	}
	
	/**
	 * @return le nombre de ligne de code compter dans la classe*/
	public int classe_LOC() {
		
		Iterator<String> iterateur = contenueFichier.iterator();
		while (iterateur.hasNext()) {
		    String str = iterateur.next();
		    if (str == null || str.trim().isEmpty())
		        iterateur.remove();
		}

		return contenueFichier.size();
	}
	/**
	 * @return le nombre de ligne de commentaire compter dans la classe*/
	public int classe_CLOC() 
	{
		int compteur = 0;
		boolean enCommentaire = false;
		for (String temp : contenueFichier) 
		{
			if(!enCommentaire)
			{
				if(temp.contains("//")) 
				{
					compteur++;
				}	
				if(temp.contains("/*") || temp.contains("/**"))
				{
					compteur++;
					enCommentaire = true;
				}
				if(temp.contains("*/"))
				{
					enCommentaire = false;
				}
			}
			else
			{
				if(temp != null && !temp.trim().isEmpty())
				{
					compteur++;
				}
				if(temp.contains("*/"))
				{
					enCommentaire = false;
				}
			}
        }
		return compteur;
	}
	/**
	 * @return la densit� de commentaires pour une classe*/
	public float classe_DC() {
		return (float)classe_CLOC() / (float)classe_LOC();
	}
	/**
	 * @return le degr� selon lequel une classe est bien comment�e */
	public float classe_BC() {
		return (float)classe_DC() / (float)WMC();
	}
	/**
	 * @return la somme pond�r�e des complexit�s des m�thodes d'une classe */
	public int WMC()
	{
		int compteur = 0;
		for(Methode methode : methodes)
		{
			compteur = compteur + methode.CC();
		}
		return compteur;
	}
}
