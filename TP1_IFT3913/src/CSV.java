import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class CSV {
	
		//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ", ";
		private static final String NEW_LINE_SEPARATOR = "\n";
	    
	    //CSV file header
	    private static final String FILE_HEADER_CLASSE = "chemin, classe, classe_LOC, classe_CLOC, classe_DC";
	    private static final String FILE_HEADER_METHODE = "chemin, classe, methode, methode_LOC, methode_CLOC, methode_DC";
	    
		Map<Integer, Classe> ListeClasse = new HashMap<>();
		Map<Integer, Methode> ListeMethode = new HashMap<>();
		
		
		  /**
		   * permet d'ecrire le csv de classe 
		   * @return un fichier csv creer remplis d'informations sur les classes
		   * */ 
		    public void ecrireClasse(){

				String fileName = "classes.csv";
		        FileWriter fileWriter = null;
		                 
		        try {
		            fileWriter = new FileWriter(fileName);
		 
		            //Write the CSV file header
		            fileWriter.append(FILE_HEADER_CLASSE.toString());
		             
		            //Add a new line separator after the header
		            fileWriter.append(NEW_LINE_SEPARATOR);
		             
		            //Write a new student object list to the CSV file
		            for (Classe classe : ListeClasse.values()) {
		                fileWriter.append(String.valueOf(classe.getFullFile().getAbsolutePath())); // class Path
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe)); // class name
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.classe_LOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe.classe_CLOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(classe)); // Classe_DC
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

			  /**
			   * permet d'ecrire le csv de methode 
			   * @return un fichier csv creer remplis d'informations sur les methodes*/ 
		    public void ecrireMethod(){

				String fileName = "methodes.csv";
		        FileWriter fileWriter = null;
		                 
		        try {
		            fileWriter = new FileWriter(fileName);
		 
		            //Write the CSV file header
		            fileWriter.append(FILE_HEADER_METHODE.toString());
		             
		            //Add a new line separator after the header
		            fileWriter.append(NEW_LINE_SEPARATOR);
		             
		            //Write a new student object list to the CSV file
		            for (Methode methode : ListeMethode.values()) {
		                fileWriter.append(String.valueOf(methode.getFile())); // class Path (SIGNATURE?)
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode)); // class name
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.getClass()));//method's class
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.methode_LOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode.methode_CLOC()));
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(String.valueOf(methode)); // Classe_DC
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


