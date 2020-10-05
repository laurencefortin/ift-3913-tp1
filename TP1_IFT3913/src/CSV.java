import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class CSV {
	
		//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ", ";
		private static final String NEW_LINE_SEPARATOR = "\n";
	    
	    //CSV file header
	    private static final String FILE_HEADER_CLASSE = "WMC, classe_BC, chemin, classe, classe_LOC, classe_CLOC, classe_DC";
	    private static final String FILE_HEADER_METHODE = "CC, methode_BC, chemin, classe, methode, methode_LOC, methode_CLOC, methode_DC";
	    
		
		 
		public CSV(List<Classe> ListeClasse) {
			 ecrireClasse(ListeClasse);
		 }
		
		  /**
		   * permet d'ecrire le csv de classe 
		   * @return un fichier csv creer remplis d'informations sur les classes
		   * */ 
		    public void ecrireClasse(List<Classe> ListeClasse){
 
				String fileName = "classes.csv";
		        FileWriter fileWriter = null;
		                 
		        try {
		            fileWriter = new FileWriter(fileName);
		 
		            //Write the CSV file header
		            fileWriter.append(FILE_HEADER_CLASSE.toString());
		             
		            //Add a new line separator after the header
		            fileWriter.append(NEW_LINE_SEPARATOR);
		             
		            //Write a new student object list to the CSV file
		            for (Classe classe : ListeClasse) {
		            	fileWriter.append(String.valueOf(classe.WMC()));  
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.classe_BC())); 
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.getFullFile().getAbsolutePath())); // class Path
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.getFullFile().getName())); // class name
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.classe_LOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.classe_CLOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.classe_DC())); // Classe_DC
		                fileWriter.append(NEW_LINE_SEPARATOR);
		                ecrireMethode(classe);
		            }    
		        } catch (Exception e) {
		            System.out.println("Error in CsvFileWriter !!!");
		            e.printStackTrace();
		        } finally {
		             
		            try {
		                fileWriter.flush();
		                fileWriter.close();
		            } catch (IOException e) {
		                System.out.println("Error while flushing/closing fileWriter !!!");
		                e.printStackTrace();
		            } 
		        }
		    }

			  /**
			   * permet d'ecrire le csv de methode 
			   * @return un fichier csv creer remplis d'informations sur les methodes*/ 
		    public void ecrireMethode(Classe classe){

				String fileName = "methodes.csv";
		        FileWriter fileWriter = null;
		                 
		        try {
		            fileWriter = new FileWriter(fileName);
		 
		            //Write the CSV file header
		            fileWriter.append(FILE_HEADER_METHODE.toString());
		             
		            //Add a new line separator after the header
		            fileWriter.append(NEW_LINE_SEPARATOR);
		             
		            //Write a new student object list to the CSV file
		            for (Methode methode : classe.methodes) {
		  
		            	fileWriter.append(String.valueOf(methode.CC())); 
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.methode_BC())); 
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.getFullFile().getAbsoluteFile())); // class Path (SIGNATURE?)
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.getFullFile().getName())); // class name
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.getSignature()));//method's class
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.methode_LOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.methode_CLOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.methode_DC())); // Classe_DC
		                fileWriter.append(NEW_LINE_SEPARATOR);
		            }    
		        } catch (Exception e) {
		            System.out.println("Error in CsvFileWriter !!!");
		            e.printStackTrace();
		        } finally {
		             
		            try {
		                fileWriter.flush();
		                fileWriter.close();
		            } catch (IOException e) {
		                System.out.println("Error while flushing/closing fileWriter !!!");
		                e.printStackTrace();
		            } 
		        }
		    }
	}
