import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;




public class tp {
	
	private List<Classe> classes = new ArrayList<Classe>();
	
	public void main(String[] args) throws IOException 
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

	
	
	
	
	
	
	public void iterateOnFiles(File[] directoryListing) {
		if (directoryListing != null) {
		      for (File child : directoryListing) 
		      {
		    	  if(!child.isDirectory())
		    	  {
		    		  
			    	  Classe temp = new Classe(child);
			    	  
			    	  System.out.println("Nombre de lignes de commentaires du fichier : " + temp.classe_CLOC());
			    	  System.out.println("Nombre de lignes de codes du fichier : " + temp.classe_LOC());
			    	  if(temp.getMethods().size() > 0)
			    	  {
								
			    	  }
				    	  for(Method methode : temp.getMethods())
				    	  {
				    		System.out.println(methode.getSignature());
				    		System.out.println(methode.getLine());
				    	    methode.findMethodContent();
				    	    methode.findCommentsBefore();
				    	    System.out.println("Nombre de lignes de commentaires de la methode : " + methode.methode_CLOC());
				    	    System.out.println("Nombre de lignes de code de la methode : " + methode.methode_LOC());
				    	    System.out.println("testcc : " + methode.CC());
				   
				    	  }
				     classes.add(temp);
			    }
		    	  else
		    	  {
		  		    iterateOnFiles(child.listFiles());
		    	  }
		      }
		      }
	}
	

}
