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
	private File fullFile;
	private List<String>  fileContent;
	public List<Methode>  methodes;
	
//CONSTRUCTEUR
	public Classe(File file) {
		this.fullFile = file;
		this.fileContent = readFileInList(fullFile.getAbsolutePath());
		this.methodes = new ArrayList<Methode>();
		findMethods();
		
	}
	
	public File getFullFile() {
		return fullFile;
	}

	public List<String> getFileContent() {
		return fileContent;
	}

	public List<Methode> getMethods() {
		return methodes;
	}

	/**
	 * @param le nom du fichier que l'on veut evaluer
	 * @return retourne une liste de string qui contiennent du text*/
	public List<String> readFileInList(String fileName) 
	  { 
	    List<String> lines = Collections.emptyList(); 
	    try
	    { 
	      lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.ISO_8859_1); 
	    } 
	    catch (IOException e) 
	    { 
	    	 
	      e.printStackTrace(); 
	    } 
	    return lines; 
	  } 
	/**
	 * Permet de trouver les methodes d'une classe specifique sur laquelle la fonction est appeler*/
	public void findMethods() {
		String regex = ".*(public|protected|private|static) +[\\w\\<\\>\\[\\],\\s]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
		
		for (String temp : fileContent) 
		{
			if(temp.matches(regex)) 
			{
				System.out.println(temp);
				String methodSplit[] = StringUtils.substringBetween(temp, "", "(").split(" ");
				String methodSplit2 = StringUtils.substringBetween(temp, methodSplit[methodSplit.length - 1], ")");
				String signature = methodSplit[methodSplit.length - 1] + methodSplit2 + ")";
				System.out.println(signature);
				methodes.add(new Methode(signature, temp, fileContent));
			}
        }		
	}
	
	/**
	 * @return le nombre de ligne de code compter dans la classe*/
	public int classe_LOC() {
		
		Iterator<String> iter = fileContent.iterator();
		while (iter.hasNext()) {
		    String str = iter.next();
		    if (str == null || str.trim().isEmpty())
		        iter.remove();
		}

		return fileContent.size();
	}
	/**
	 * @return le nombre de ligne de commentaire compter dans la classe*/
	public int classe_CLOC() 
	{
		int count = 0;
		boolean inComment = false;
		for (String temp : fileContent) 
		{
			if(!inComment)
			{
				if(temp.contains("//")) 
				{
					count++;
				}	
				if(temp.contains("/*") || temp.contains("/**"))
				{
					count++;
					inComment = true;
				}
				if(temp.contains("*/"))
				{
					inComment = false;
				}
			}
			else
			{
				if(temp != null && !temp.trim().isEmpty())
				{
					count++;
				}
				if(temp.contains("*/"))
				{
					inComment = false;
				}
			}
        }
		return count;
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
		int count = 0;
		for(Methode methode : methodes)
		{
			count = count + methode.CC();
		}
		return count;
	}
}
