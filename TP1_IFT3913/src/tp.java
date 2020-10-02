import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.commons.lang3.StringUtils;



public class tp {
	public static boolean inComment = false;
	public static void main(String[] args) throws IOException 
	{		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showDialog(null, "Choisir un dossier");
		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
		    File yourFolder = fc.getSelectedFile();
		    File[] directoryListing = yourFolder.listFiles();
		    if (directoryListing != null) {
		      for (File child : directoryListing) 
		      {
		    	  List<Method>  test = new ArrayList();
		    	  List<String> l = readFileInList(child.getAbsolutePath()); 
		    	  System.out.println("Nombre de lignes de commentaires du fichier : " + classe_CLOC(l));
		    	  System.out.println("Nombre de lignes de codes du fichier : " + classe_LOC(l));
		    	  List<Method> abc = findMethods(l, test);
		    	  if(abc.size() > 0)
		    	  {
							
		    	  }
			    	  for(Method methode : abc)
			    	  {
			    		System.out.println(methode.getSignature());
			    		System.out.println(methode.getLine());
			    	    methode.findMethodContent();
			    	    methode.findCommentsBefore();
			    	    System.out.println("Nombre de lignes de commentaires de la methode : " + methode.methode_CLOC());
			    	    System.out.println("Nombre de lignes de code de la methode : " + methode.methode_LOC());
			    	    System.out.println("testcc : " + methode.CC());
			   
			    	  }
		    	  }
		      }
		    } 
		}

	
	
	
	public static List<String> readFileInList(String fileName) 
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
	
	public static int classe_CLOC(List<String> list) 
	{
		int count = 0;
		boolean inComment = false;
		for (String temp : list) 
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
	
	public static List<Method> findMethods(List<String> list, List<Method> test) {
		String regex = ".*(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\],\\s]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
		for (String temp : list) 
		{
			if(temp.matches(regex)) 
			{
				String methodSplit[] = StringUtils.substringBetween(temp, "", "(").split(" ");
				test.add(new Method(methodSplit[methodSplit.length - 1], temp, list));
			}
        }		
		return test;
	}
	
	public static int classe_LOC(List<String> list) {
		
		System.out.println(list.size());
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
		    String str = iter.next();
		    if (str == null || str.trim().isEmpty())
		        iter.remove();
		}

		return list.size();

	}


}
