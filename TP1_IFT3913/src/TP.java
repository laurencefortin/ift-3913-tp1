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
		    File fichierChoisi = fc.getSelectedFile();
		    File[] listeDossier = fichierChoisi.listFiles();
		    iterateOnFiles(listeDossier);
		    } 
		}
/**
 * @param directory Listing qui donne la liste des file a iterer
 * 
 * */
	public static void iterateOnFiles(File[] listeDossier) {
		if (listeDossier != null) {
		      for (File child : listeDossier) 
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
}
