import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ClasseTest {	
	
		private Classe classeTest;
		@Before
		public void setUp() {
			String contenu = "";
			File file = new File(contenu);
			classeTest = new Classe(file);
		}

		
		@Test
		public void testClasse_CLOC_fonctionne() {
			// Arrange.

			// Act.
			repertoireMembre.ajouterMembre(membreTest);
			
			// Assert.
			assertEquals(2, repertoireMembre.getListeMembre().size());
			assertTrue(repertoireMembre.getListeMembre().containsKey(membreTest.getNumero()));
		}
		
		@Test(expected = NullPointerException.class)
		public void testAjouterMembre_lanceException_membreNull() {
			// Arrange.

			// Act.
			repertoireMembre.ajouterMembre(null);

			// Assert.
			assertEquals(1, repertoireMembre.getListeMembre().size());
		}

		
	

		

	}


