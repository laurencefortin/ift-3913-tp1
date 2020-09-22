import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

public class main {
	
	public static void main(String[] args) throws IOException {
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 0) {
			
		    System.out.println(fd.getFiles()[0].getAbsolutePath());
		    List l = readFileInList(fd.getFiles()[0].getAbsolutePath()); 
			Iterator<String> itr = l.iterator(); 
			while (itr.hasNext()) {
			      System.out.println(itr.next()); 
			  } 
			System.out.println("Nombre de lignes de codes du fichier : " + classe_LOC(l));
<<<<<<< HEAD
			System.out.println("Nombre de lignes de commentaires du fichier : " + classe_CLOC(l));
=======
			
>>>>>>> main
			
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
	
<<<<<<< HEAD
	public static int classe_CLOC(List<String> list) {
		int count = 0;
		for (String temp : list) {
			if(temp.contains("//"))
			{
				count++;
			}	
        }
		return count;
	}
	
	
=======
	public static void commentLinesCount(String fileName) {
		
	}
>>>>>>> main
	public static int classe_LOC(List list) {
		return list.size();
	}


}
