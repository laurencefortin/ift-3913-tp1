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
		}
    }
	
	public static List<String> readFileInList(String fileName) 
	  { 
	  
	    List<String> lines = Collections.emptyList(); 
	    try
	    { 
	      lines = 
	       Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
	    } 
	  
	    catch (IOException e) 
	    { 
	  
	      // do something 
	      e.printStackTrace(); 
	    } 
	    return lines; 
	  } 


}
