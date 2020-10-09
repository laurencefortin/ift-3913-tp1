import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
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
		String contenuTemp = "public LocalDate StringtoDate(String date) {\r\n"
				+ "			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(\"dd-MM-yyyy\");\r\n"
				+ "			return LocalDate.parse(date, formatter);\r\n"
				+ "		}\r\n";
		File fileTemp = File.createTempFile("test", ".tmp");
	    FileWriter writer = new FileWriter(fileTemp);
	    writer.write(contenuTemp);
	    writer.close();		    
	    Classe classeTemp = new Classe(fileTemp);
		List<String> tableauFichierTemp = classeTemp.getFileContent();
		// Act.
			
		List<String> methodeTemp = classeTest.getMethods().get(0).getContentMethod();
		
		// Assert.
		assertEquals(methodeTemp, tableauFichierTemp);
		
		
	}
}
