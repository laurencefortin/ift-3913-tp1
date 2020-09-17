package org.ift2255.devoir3.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.ift2255.devoir3.Membre;
import org.ift2255.devoir3.RepertoireMembres;


public class RepertoireMembresTest {
	
	private RepertoireMembres repertoireMembre;
	
	private Map<String, Membre> lstMembres;
	private Membre membre;
	
	@Before
	public void setUp() {
		lstMembres = new HashMap<>();
		membre = new Membre("999999999", "test", "test", true, "test@test.test", "test", "test", "test", "test", 0);
		lstMembres.put(membre.getNumero(), membre);
		repertoireMembre = new RepertoireMembres(lstMembres);
	}

	
	@Test
	public void testAjouterMembre_fonctionne_bonMembre() {
		// Arrange.
		Membre membreTest = new Membre("999999998", "test", "test", true, "test@test.test", "test", "test", "test", "test", 0);

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

	
	@Test
	public void testSupprimerMembre_fonctionne_bonMembre() {
		// Arrange.
		
		// Act.
		repertoireMembre.supprimerMembre(membre);
		
		// Assert.
		assertEquals(0, repertoireMembre.getListeMembre().size());
		assertFalse(repertoireMembre.getListeMembre().containsKey(membre.getNumero()));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testSupprimerMembre_lanceException_mauvaisMembre() {
		// Arrange.
		Membre membreTest = new Membre("999999998", "test", "test", true, "test@test.test", "test", "test", "test", "test", 0);
		
		// Act.
		
		repertoireMembre.supprimerMembre(membreTest);
	
		// Assert.
		assertEquals(1, repertoireMembre.getListeMembre().size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testSupprimerMembre_lanceException_membreNull() {
		// Arrange.

		
		// Act.
		repertoireMembre.supprimerMembre(null);
		
		// Assert.
		assertEquals(1, repertoireMembre.getListeMembre().size());
	}

	
	@Test
	public void testModifierMembre_fonctionne_bonMembre() {
		// Arrange.
		Membre membreTest = new Membre("999999999", "test1", "test1", true, "test@test.test", "test1", "test1", "test1", "test1", 0);
		
		// Act.
		repertoireMembre.modifierMembre(membreTest);
		
		// Assert.
		assertTrue(repertoireMembre.getListeMembre().containsKey(membreTest.getNumero()));
		assertTrue(repertoireMembre.getListeMembre().get(membreTest.getNumero()).getPrenom().equals("test1"));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testModifierMembre_lanceException_mauvaisMembre() {
		// Arrange.
		Membre membreTest = new Membre("999999998", "test", "test", true, "test@test.test", "test", "test", "test", "test", 0);
		
		// Act.
		repertoireMembre.modifierMembre(membreTest);
		
		// Assert.
		assertFalse(repertoireMembre.getListeMembre().containsKey(membreTest.getNumero()));
	}
	
	@Test(expected = NullPointerException.class)
	public void testModifierMembre_lanceException_membreNull() {
		// Arrange.
		
		// Act.
		repertoireMembre.modifierMembre(null);
		
		// Assert.
	}

}
