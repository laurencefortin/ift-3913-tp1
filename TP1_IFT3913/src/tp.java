import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class tp {
	public static boolean inComment = false;
	public static boolean inMethod = false;
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
		      for (File child : directoryListing) {
		    	  List l = readFileInList(child.getAbsolutePath()); 
		    	  Iterator<String> itr = l.iterator(); 
		    	  while (itr.hasNext()) {
		    		  	 System.out.println(itr.next());
		    	  } 
		    	  System.out.println("Nombre de lignes de codes du fichier : " + classe_LOC(l));
		    	  System.out.println("Nombre de lignes de commentaires du fichier : " + classe_CLOC(l));
		    	  System.out.println("Nombre de methodes du fichier : " + methode_LOC(l));
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
	
	public static int methode_LOC(List<String> list) {
		String regex = ".*(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\],\\s]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
		int count = 0;
		int countAcco = 0;
		for (String temp : list) {
		if(!inMethod)
			if(temp.matches(regex)) 
			{
				
				count++;
				//inMethod = true;
				countAcco++;
			}
		/*else
		{
			count++;
			
			if(!temp.contains("}") && temp.contains("{"))
			{
				
			}
			if(temp.contains("}") && !temp.contains("{"))
			{
				
			}
		}*/
        }
		return count;
	}
	
	public static int classe_LOC(List list) {
		return list.size();
	}


}
