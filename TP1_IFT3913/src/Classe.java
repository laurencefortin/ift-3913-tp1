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
	private List<Methode>  methods;
	
//CONSTRUCTEUR
	public Classe(File file) {
		this.fullFile = file;
		this.fileContent = readFileInList(fullFile.getAbsolutePath());
		this.methods = new ArrayList<Methode>();
		findMethods();
		
	}
	
	public File getFullFile() {
		return fullFile;
	}

	public List<String> getFileContent() {
		return fileContent;
	}

	public List<Methode> getMethods() {
		return methods;
	}

	/**
	 * @param le nom du fichier que l'on veut evaluer
	 * @return retourne une liste de string qui contiennent du text*/
	public List<String> readFileInList(String fileName) 
	  { 
	    List<String> lines = Collections.emptyList(); 
	    try
	    { 
	      lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
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
		String regex = ".*(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\],\\s]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
		
		for (String temp : fileContent) 
		{
			if(temp.matches(regex)) 
			{
				String methodSplit[] = StringUtils.substringBetween(temp, "", "(").split(" ");
				methods.add(new Methode(methodSplit[methodSplit.length - 1], temp, fileContent));
			}
        }		
	}
	
	/**
	 * @return le nombre de ligne de code compter dans la classe*/
	public int classe_LOC() {
		
		System.out.println(fileContent.size());
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
}
