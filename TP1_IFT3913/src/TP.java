import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

public class TP {
	
	private static List<Classe> classes = new ArrayList<Classe>();
	/**
	 * @param liste d'arguments
	 * 
	 * */
	public static void main(String[] args) throws IOException 
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
		    File[] directoryListing = yourFolder.listFiles();
		    iterateOnFiles(directoryListing);
		    } 
		}
/**
 * @param directory Listing qui donne la liste des file a iterer
 * 
 * */
	public static void iterateOnFiles(File[] directoryListing) {
		if (directoryListing != null) {
		      for (File child : directoryListing) 
		      {
		    	  if(!child.isDirectory())
		    	  {
		    		  
			    	  Classe temp = new Classe(child);
				     classes.add(temp);
		    	  }
		    	  else
		    	  {
		  		    iterateOnFiles(child.listFiles());
		    	  }
		    	  CSV csv = new CSV(classes);
		    	  
		      }
		   }
	}
	

}
