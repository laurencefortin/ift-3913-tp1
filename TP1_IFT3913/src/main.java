import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

public class main {
	
	public static void main(String[] args) throws IOException {
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 0){
		    System.out.println(fd.getFiles()[0].getAbsolutePath());
		}
    }

}
