import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ClasseTest {	
	
		private Classe classeTest;
		@Before
		public void setUp() {
			String contenu = "public class Main {	\r\n"
					+ "	\r\n"
					+ "	// Objets principaux du programme.\r\n"
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
			File file = new File(contenu);
			classeTest = new Classe(file);
		}

		
		@Test
		public void testClasse_CLOC_fonctionne() {
			// Arrange.
				
			// Act.
			
			// Assert.
			assertEquals(3, classeTest.classe_CLOC());
		}
		
		@Test
		public void testClasse_CLOC_mauvaisCompte()) {
			// Arrange.
			String contenu = "public class Main {	\r\n"
					+ "/* */ /*\r\n"
					+ "aa\r\n"
					+ "aaa\r\n"
					+ "bbbb\r\n"
					+ "*/\r\n"
					+ "	// Objets principaux du programme.\r\n"
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
			File file = new File(contenu);

			// Act.
			classeTest.setFichierComplet(file);

			// Assert.
			assertEquals(1, classeTest.classe_CLOC());
		}

		
	

		

	}


