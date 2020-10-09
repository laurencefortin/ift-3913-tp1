import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClasseTest {	
	
		public Classe classeTest;
		@BeforeEach
		public void setUp() throws IOException {
			String contenu = "public class Main {	\r\n"
					+ "	\r\n"
					+ "	/* Objets principaux du programme.*/\r\n"
					+ "	public static void main(String[] args) {\r\n"
					+ "		\r\n"
					+ "		menu = new Menu();\r\n"
					+ "		procedure = new Procedure();\r\n"
					+ "		\r\n"
					+ "		repertoireMembres = new RepertoireMembres(CentreDonnee.lireMembres());\r\n"
					+ "		repertoireServices = new RepertoireServices(CentreDonnee.lireServices());\r\n"
					+ "		\r\n"
					+ "		// Affichage du guide d'utilisation du programme.\r\n"
					+ "		afficherGuide();\r\n"
					+ "		\r\n"
					+ "		// Gestion du menu Principal.\r\n"
					+ "		menuPrincipal();\r\n"
					+ "	}\r\n"
					+ "}";
			
			File file = File.createTempFile("test", ".tmp");
		    FileWriter writer = new FileWriter(file);
		    writer.write(contenu);
		    writer.close();
		    
			this.classeTest = new Classe(file);
		}

	
		@Test
		public void testClasse_CLOC_fonctionne() {
			// Arrange.
				
			// Act.
			
			// Assert.
			assertEquals(3, classeTest.classe_CLOC());
		}
	
		@Test //Devrait etre 15 mais ne comptera pas l'ouverture à la ligne /* */ /*
		public void testClasse_CLOC_mauvaisCompte() throws IOException {
			// Arrange.
			String contenuCLOC = "public class Main {	\r\n"
					+ "/* */ /* \r\n"
					+ "aa\r\n"
					+ "aaa\r\n"
					+ "bbbb\r\n"
					+ " \r\n"
					+ "	//// Objets principaux du programme.\r\n"
					+ "	public static void main(String[] args) {\r\n"
					+ "		\r\n"
					+ "		menu = new Menu();\r\n"
					+ "		procedure = new Procedure();\r\n"
					+ "		\r\n"
					+ "		repertoireMembres = new RepertoireMembres(CentreDonnee.lireMembres());\r\n"
					+ "		repertoireServices = new RepertoireServices(CentreDonnee.lireServices());\r\n"
					+ "		\r\n"
					+ "		//// Affichage du guide d'utilisation du programme.\r\n"
					+ "		afficherGuide();\r\n"
					+ "		\r\n"
					+ "		//// Gestion du menu Principal.\r\n"
					+ "		menuPrincipal();\r\n"
					+ "	}\r\n"
					+ "}";
			File fileCLOC = File.createTempFile("test", ".tmp");
		    FileWriter writer = new FileWriter(fileCLOC);
		    writer.write(contenuCLOC);
		    writer.close();
			
			// Act.
			
		    Classe classeTemp = new Classe(fileCLOC);

			// Assert.
		    
			assertEquals(8, classeTemp.classe_CLOC());
		}
		
		
		@Test
		public void testTrouverMethodes_bonContenu() {
			// Arrange.
			String contenuMethode = "\"	public static void main(String[] args) {\\r\\n\"\r\n"
					+ "					+ \"		\\r\\n\"\r\n"
					+ "					+ \"		menu = new Menu();\\r\\n\"\r\n"
					+ "					+ \"		procedure = new Procedure();\\r\\n\"\r\n"
					+ "					+ \"		\\r\\n\"\r\n"
					+ "					+ \"		repertoireMembres = new RepertoireMembres(CentreDonnee.lireMembres());\\r\\n\"\r\n"
					+ "					+ \"		repertoireServices = new RepertoireServices(CentreDonnee.lireServices());\\r\\n\"\r\n"
					+ "					+ \"		\\r\\n\"\r\n"
					+ "					+ \"		// Affichage du guide d'utilisation du programme.\\r\\n\"\r\n"
					+ "					+ \"		afficherGuide();\\r\\n\"\r\n"
					+ "					+ \"		\\r\\n\"\r\n"
					+ "					+ \"		// Gestion du menu Principal.\\r\\n\"\r\n"
					+ "					+ \"		menuPrincipal();\\r\\n\"\r\n"
					+ "					+ \"	}\\r\\n\"";
			
			// Act.
						
			// Assert.
			assertEquals(3, classeTest.classe_CLOC());
		}

		

		
	

		

	}


