import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodeTest {
	
	private Methode methodeTest;
	private Classe classeTest;
	
	@BeforeEach
	public void setUp() throws IOException {
		String contenuClasse = "public class CentreDonnee	   \r\n"
				+ "{	 \r\n"
				+ "\r\n"
				+ "	private Map<Integer, Membre> ListeMembre;\r\n"
				+ "	private Map<Integer, Service> ListeService;\r\n"
				+ "	\r\n"
				+ "	public CentreDonnee()\r\n"
				+ "	{\r\n"
				+ "		Map<Integer, Membre> ListeMembre = new HashMap<>();\r\n"
				+ "		Map<Integer, Service> ListeService = new HashMap<>();\r\n"
				+ "	}\r\n"
				+ "\r\n"
				+ "public LocalDate StringtoDate(String date) {\r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return LocalDate.parse(date, formatter);\r\n"
				+ "		}\r\n"
				+ "		\r\n"
				+ "		public String DatetoString(LocalDate date) {\r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return formatter.format(date);\r\n"
				+ "		}\r\n"
				+ "		\r\n"
				+ "		public boolean[] StringtoBool(String test1) {\r\n"
				+ "			String[] parts = test1.split(\"-\");\r\n"
				+ "\r\n"
				+ "		    boolean[] array = new boolean[parts.length];\r\n"
				+ "		    for (int i = 0; i = parts.length; i++)\r\n"
				+ "		        array[i] = Boolean.parseBoolean(parts[i]);\r\n"
				+ "			\r\n"
				+ "			return array;\r\n"
				+ "		}\r\n"
				+ "}" 	;    
		File fileClasse = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileClasse);
	    writer.write(contenuClasse);
	    writer.close();
		classeTest = new Classe(fileClasse);
		
	}
	
	@Test
	public void testTrouverContenuMethode_bonContenu() throws IOException
	{
		// Arrange.
		String ligne = "public LocalDate StringtoDate(String date) {\r\n";
		String contenuTemp = "public LocalDate StringtoDate(String date) {\r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return LocalDate.parse(date, formatter);\r\n"
				+ "		}\r\n";		
		File fileTemp = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileTemp);
	    writer.write(contenuTemp);
	    writer.close();
	    methodeTest = new Methode(ligne, fileTemp);
		// Act.
			
		List<String> methodeTemp = classeTest.getMethods().get(0).getContentMethod();
		
		// Assert.
		assertEquals(methodeTemp, methodeTest.getContentMethod());
	}
	
	@Test //On ne trouvera pas le bon contenu de la methode s'il y a plusieurs accolades sur la 1ere ligne
	public void testTrouverContenuMethode_MauvaisContenu() throws IOException
	{
		// Arrange.
		String contenuClasse = "public class CentreDonnee	   \r\n"
				+ "{	 \r\n"
				+ "\r\n"
				+ "	private Map<Integer, Membre> ListeMembre;\r\n"
				+ "	private Map<Integer, Service> ListeService;\r\n"
				+ "	\r\n"
				+ "	public CentreDonnee()\r\n"
				+ "	{\r\n"
				+ "		Map<Integer, Membre> ListeMembre = new HashMap<>();\r\n"
				+ "		Map<Integer, Service> ListeService = new HashMap<>();\r\n"
				+ "	}\r\n"
				+ "\r\n"
				+ "public LocalDate StringtoDate(String date) { if(something{ \r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return LocalDate.parse(date, formatter);\r\n"
				+ "		}\r\n"
				+ "		\r\n"
				+ "		public String DatetoString(LocalDate date) {\r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return formatter.format(date);\r\n"
				+ "		}\r\n"
				+ "		\r\n"
				+ "		public boolean[] StringtoBool(String test1) {\r\n"
				+ "			String[] parts = test1.split(\"-\");\r\n"
				+ "\r\n"
				+ "		    boolean[] array = new boolean[parts.length];\r\n"
				+ "		    for (int i = 0; i = parts.length; i++)\r\n"
				+ "		        array[i] = Boolean.parseBoolean(parts[i]);\r\n"
				+ "			\r\n"
				+ "			return array;\r\n"
				+ "		}\r\n"
				+ "}" 	;      
		File fileClasse = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileClasse);
	    writer.write(contenuClasse);
	    writer.close();
		classeTest = new Classe(fileClasse);
	    
	    // Act.
		Methode methodeTest = classeTest.getMethods().get(0);	
		String ligne = "public LocalDate StringtoDate(String date) { if(something{ \r\n";
		String contenuMethode = "public LocalDate StringtoDate(String date) { if(something{ \r\n"
				+ "	}	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return LocalDate.parse(date, formatter);\r\n"
				+ "		}\r\n";
		File fileMethode = File.createTempFile("test2", ".tmp");
	    FileWriter writer2 = new FileWriter(fileMethode);
	    writer2.write(contenuMethode);
	    writer2.close();
		
		Methode methodeTest2 = new Methode(ligne, fileMethode);
		// Assert.
	    Assertions.assertNotEquals(methodeTest.getContentMethod(), methodeTest2.getContentMethod());
		
	}
	
	@Test
	public void testTrouverSignature_bonneSignature() throws IOException
	{
		// Arrange.
		String contenuTemp = "public LocalDate StringtoDate(String date) {\r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return LocalDate.parse(date, formatter);\r\n"
				+ "		}\r\n";	    
	    String ligne = "public LocalDate StringtoDate(String date) {";
	    File fileTemp = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileTemp);
	    writer.write(contenuTemp);
	    writer.close();
	    methodeTest = new Methode(ligne, fileTemp);
	    String signature = "StringtoDate(String date)";
		// Act.
					
		// Assert.
		assertEquals(signature, methodeTest.getSignature());
	}
	
	@Test // On ne trouvera pas la bonne signature si on a des parenth�ses dans les arguments
	public void testTrouverSignature_mauvaiseSignature() throws IOException
	{
		// Arrange.
		String contenuTemp = "public LocalDate StringtoDate(methode(), String date) {\r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return LocalDate.parse(date, formatter);\r\n"
				+ "		}\r\n";
			    
	    String ligne = "public LocalDate StringtoDate(String date) {";
	    File fileTemp = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileTemp);
	    writer.write(contenuTemp);
	    writer.close();
	    methodeTest = new Methode(ligne, fileTemp);
	    String signature = "StringtoDate(methode(), String date)";
		// Act.
					
		// Assert.
		Assertions.assertNotEquals(signature, methodeTest.getSignature());
	}
	
	@Test
	public void testMethode_CLOC_bonCompte() throws IOException
	{
		// Arrange.
			String contenuTemp = "//Commentaire1\r\n"
					+ "		public String DatetoString(LocalDate date) {\r\n"
					+ "		/*Commentaire 2\r\n"
					+ "		Commentaire3\r\n"
					+ "		\r\n"
					+ "		*/commentaire4\r\n"
					+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
					+ "			return formatter.format(date);\r\n"
					+ "		}";
			String ligne = "		public String DatetoString(LocalDate date) {\r\n";
			File file = File.createTempFile("test", ".tmp");
		    FileWriter writer = new FileWriter(file);
		    writer.write(contenuTemp);
		    writer.close();
		    methodeTest = new Methode(ligne, file);
		// Act.
					
		// Assert.
		assertEquals(4, methodeTest.methode_CLOC());
	}
	
	@Test  //Devrait etre 4 mais ne comptera pas l'ouverture � la ligne /* */ /*Commentaire 2\r\n
	public void testMethode_CLOC_mauvaisCompte() throws IOException
	{
		// Arrange.
			String contenuTemp = "//Commentaire1\r\n"
					+ "		public String DatetoString(LocalDate date) {\r\n"
					+ "		/* */ /*Commentaire 2\r\n"
					+ "		Commentaire3\r\n"
					+ "		\r\n"
					+ "		*/commentaire4\r\n"
					+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
					+ "			return formatter.format(date);\r\n"
					+ "		}";
			String ligne = "		public String DatetoString(LocalDate date) {\r\n";
			File file = File.createTempFile("test", ".tmp");
		    FileWriter writer = new FileWriter(file);
		    writer.write(contenuTemp);
		    writer.close();
		    methodeTest = new Methode(ligne, file);
		// Act.
					
		// Assert.
		assertNotEquals(4, methodeTest.methode_CLOC());
	}
	
	@Test
	public void testTrouverCommentaireAvantMethode_bonContenu() throws IOException
	{
		// Arrange.
				String contenuClasse = "public class CentreDonnee	   \r\n"
						+ "{"
						+"/*Commentaire1 \r\n"
						+"\r\n"
						+ "*/ \r\n"
						+ "//Commentaire 2\r\n"
						+ "		public boolean[] StringtoBool(String test1) {\r\n"
						+ "			String[] parts = test1.split(\"-\");\r\n"
						+ "\r\n"
						+ "		    boolean[] array = new boolean[parts.length];\r\n"
						+ "		    for (int i = 0; i = parts.length; i++)\r\n"
						+ "		        array[i] = Boolean.parseBoolean(parts[i]);\r\n"
						+ "			\r\n"
						+ "			return array;\r\n"
						+ "		}\r\n"
						+ "}" 	;      
				File fileClasse = File.createTempFile("test", ".tmp");
			    FileWriter writer = new FileWriter(fileClasse);
			    writer.write(contenuClasse);
			    writer.close();
				classeTest = new Classe(fileClasse);
			    
			    // Act.
				Methode methodeTest = classeTest.getMethods().get(0);	
				// Assert.
			    Assertions.assertEquals(3, methodeTest.getCommentsBeforeMethod().size());
	}
	
	@Test//Compte seulement le 1er commentaire du genre /* */ ou /** */
	public void testTrouverCommentaireAvantMethode_mauvaisContenu() throws IOException
	{
		// Arrange.
				String contenuClasse = "public class CentreDonnee	   \r\n"
						+ "{"
						+"/*Commentaire1 \r\n"
						+"\r\n"
						+ "*/ \r\n"
						+"/*Commentaire2 \r\n"
						+"\r\n"
						+ "*/ \r\n"
						+ "//Commentaire 3\r\n"
						+ "		public boolean[] StringtoBool(String test1) {\r\n"
						+ "			String[] parts = test1.split(\"-\");\r\n"
						+ "\r\n"
						+ "		    boolean[] array = new boolean[parts.length];\r\n"
						+ "		    for (int i = 0; i = parts.length; i++)\r\n"
						+ "		        array[i] = Boolean.parseBoolean(parts[i]);\r\n"
						+ "			\r\n"
						+ "			return array;\r\n"
						+ "		}\r\n"
						+ "}" 	;      
				File fileClasse = File.createTempFile("test", ".tmp");
			    FileWriter writer = new FileWriter(fileClasse);
			    writer.write(contenuClasse);
			    writer.close();
				classeTest = new Classe(fileClasse);
			    
			    // Act.
				Methode methodeTest = classeTest.getMethods().get(0);	
				// Assert.
			    Assertions.assertNotEquals(5, methodeTest.getCommentsBeforeMethod().size());
	}
	
	@Test
	public void testCC_bonCompte() throws IOException {
		// Arrange.
		String contenuClasse = "public class CentreDonnee	   \r\n"
				+ "{"
				+"/*Commentaire1 \r\n"
				+"\r\n"
				+ "*/ \r\n"
				+ "//Commentaire 2\r\n"
				+ "		public boolean[] StringtoBool(String test1) {\r\n"
				+ "			String[] parts = test1.split(\"-\");\r\n"
				+ "\r\n"
				+"if(something && somethingelse)\r\n{"
				+ "		    boolean[] array = new boolean[parts.length];\r\n"
				+ "		    for (int i = 0; i = parts.length; i++)\r\n"
				+ "		        array[i] = Boolean.parseBoolean(parts[i]);\r\n"
				+ "			\r\n"
				+ "			return array;\r\n"
				+ "		}\r\n"
				+ "		}\r\n"
				+ "}" 	;      
		File fileClasse = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileClasse);
	    writer.write(contenuClasse);
	    writer.close();
		classeTest = new Classe(fileClasse);
	    
	    // Act.
		Methode methodeTest = classeTest.getMethods().get(0);	
		// Assert.
	    Assertions.assertEquals(3, methodeTest.CC());
	}

	@Test
	public void testCC_mauvaisCompte() throws IOException {
		// Arrange.
		String contenuClasse = "public class CentreDonnee	   \r\n"
				+ "{"
				+"/*Commentaire1 \r\n"
				+"\r\n"
				+ "*/ \r\n"
				+ "//Commentaire 2\r\n"
				+ "		public boolean[] StringtoBool(String test1) {\r\n"
				+ "			String[] parts = test1.split(\"-\");\r\n"
				+ "\r\n"
				+"if(something && somethingelse){} else\r\n{"
				+ "		    boolean[] array = new boolean[parts.length];\r\n"
				+ "		    for (int i = 0; i = parts.length; i++)\r\n"
				+ "		        array[i] = Boolean.parseBoolean(parts[i]);\r\n"
				+ "			\r\n"
				+ "			return array;\r\n"
				+ "		}\r\n"
				+ "		}\r\n"
				+ "}" 	;      
		File fileClasse = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileClasse);
	    writer.write(contenuClasse);
	    writer.close();
		classeTest = new Classe(fileClasse);
	    
	    // Act.
		Methode methodeTest = classeTest.getMethods().get(0);	
		// Assert.
	    Assertions.assertNotEquals(4, methodeTest.CC());
	}
	
}
