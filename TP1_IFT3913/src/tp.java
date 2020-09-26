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
		    	  
		    	  System.out.println("Nombre de lignes de codes du fichier : " + classe_LOC(l));
		    	  System.out.println("Nombre de lignes de commentaires du fichier : " + classe_CLOC(l));
		    	  List<Method> abc = findMethods(l, test);
		    	  if(abc.size() > 0)
		    	  {
							
		    	  }
			    	  for(Method methode : abc)
			    	  {
			    	    //System.out.println(methode.getSignature());
			    	   // System.out.println(methode.getLine());
			    	  /*  Iterator<String> itr = l.iterator(); 
				    	  while (itr.hasNext()) {
				    		  	 System.out.println(itr.next());
				    	  } */
			    	    methode.findMethodContent();
			    	  //  System.out.println(methode.getContentMethod());
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
		for (String temp : list) {
			if(temp.contains("//")) 
			{
				inComment = true;
				count++;
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
	
	public static int classe_LOC(List list) {
		return list.size();
	}


}
