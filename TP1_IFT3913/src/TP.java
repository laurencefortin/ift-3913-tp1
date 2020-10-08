import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class TP {
	/**	private Scanner scanner;
	 * @param liste d'arguments
	 * 
	 * */
	public static void main(String[] args) throws IOException 
	{		
			Scanner scanner = new Scanner(System.in);  
			/*if(true)
			{
				//On demande au user de choisir un path
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showDialog(null, "Choisir un dossier");
				//Si le path est bon, on trouve toute les classes
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
				    File yourFolder = fc.getSelectedFile();
				    File[] listeDossier = yourFolder.listFiles();
				    iterateOnFiles(listeDossier);
				} 
			}*/
			String chemin = scanner.nextLine();
			if(isValidPath(chemin))
			{
			    File[] listeDossier = new File(chemin).listFiles();
			    iterateOnFiles(listeDossier);
			}
	}
		
		/*
		 * @param directory Listing qui donne la liste des file a iterer2
		 * 
		 * */
			public static void iterateOnFiles(File[] directoryListing) {
				List<Classe> classes = new ArrayList<Classe>();
				if (directoryListing != null) {
				      for (File child : directoryListing) 
				      {
				    	  String extension = "";
				    	  int i = child.getName().lastIndexOf('.');
				    	  if (i > 0) {
				    	      extension = child.getName().substring(i+1);
				    	  }

				    	  if(!child.isDirectory() && extension.equals("java"))
				    	  {
				    		  
					    	  Classe temp = new Classe(child);
					    	  classes.add(temp);
				    	  }
				    	  else if (child.isDirectory())
				    	  {
				  		    iterateOnFiles(child.listFiles());
				    	  }
				    	  CSV csv = new CSV(classes);
				    	  
				      }
				   }
			}
						
			public static boolean isValidPath(String path) {
			    try {
			        Paths.get(path);
			    } catch (InvalidPathException | NullPointerException ex) {
			        return false;
			    }
			    return true;
			}

	}

