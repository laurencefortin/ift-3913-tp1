package org.ift2255.devoir3.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.ift2255.devoir3.Service;
import org.ift2255.devoir3.RepertoireServices;

public class RepertoireServicesTest {

	private RepertoireServices repertoireService;

	private Map<String, Service> lstServices;
	private Service service;
	private boolean[] recurrences = { true, true, true, true, true, true, true };

	@Before
	public void setUp() {
		lstServices = new HashMap<>();
		service = new Service("9999999", "999999999", "test", LocalDateTime.now(), LocalDate.now(), LocalDate.now(),
				"00:00", recurrences, 0, 0, "test");
		lstServices.put(service.getCode(), service);
		repertoireService = new RepertoireServices(lstServices);
	}

	@Test
	public void testAjouterService_fonctionne_bonService() {
		// Arrange.
		Service serviceTest = new Service("9999998", "999999999", "test", LocalDateTime.now(), LocalDate.now(),
				LocalDate.now(), "00:00", recurrences, 0, 0, "test");

		// Act.
		repertoireService.ajouterService(serviceTest);

		// Assert.
		assertEquals(2, repertoireService.getListeService().size());
		assertTrue(repertoireService.getListeService().containsKey(serviceTest.getCode()));
	}

	@Test(expected = NullPointerException.class)
	public void testAjouterService_lanceException_serviceNull() {
		// Arrange.

		// Act.
		repertoireService.ajouterService(null);

		// Assert.
		assertEquals(1, repertoireService.getListeService().size());
	}

	@Test
	public void testSupprimerService_fonctionne_bonService() {
		// Arrange.
		// Act.
		repertoireService.supprimerService(service);

		// Assert.
		assertEquals(0, repertoireService.getListeService().size());
		assertFalse(repertoireService.getListeService().containsKey(service.getCode()));
	}

	@Test(expected = NoSuchElementException.class)
	public void testSupprimerService_lanceException_mauvaisService() {
		// Arrange.
		Service serviceTest = new Service("9999998", "999999999", "test", LocalDateTime.now(), LocalDate.now(),
				LocalDate.now(), "00:00", recurrences, 0, 0, "test");

		// Act.
		repertoireService.modifierService(serviceTest);

		// Assert.
	}

	@Test(expected = NullPointerException.class)
	public void testSupprimerService_lanceException_serviceNull() {
		// Arrange.

		// Act.
		repertoireService.modifierService(null);

		// Assert.
	}

	@Test
	public void testModifierService_fonctionne_bonService() {
		// Arrange.
		Service serviceTest = new Service("9999999", "999999999", "test", LocalDateTime.now(), LocalDate.now(),
				LocalDate.now(), "12:00", recurrences, 0, 0, "test");

		// Act.
		repertoireService.modifierService(serviceTest);

		// Assert.

		assertTrue(repertoireService.getListeService().containsKey(serviceTest.getCode()));
		assertTrue(repertoireService.getListeService().get(serviceTest.getCode()).getHeure().equals("12:00"));
	}

	@Test(expected = NoSuchElementException.class)
	public void testModifierService_lanceException_mauvaisService() {
		// Arrange.
		Service serviceTest = new Service("9999998", "999999999", "test", LocalDateTime.now(), LocalDate.now(),
				LocalDate.now(), "12:00", recurrences, 0, 0, "test");

		// Act.
		repertoireService.modifierService(serviceTest);

		// Assert.
	}

	@Test(expected = NullPointerException.class)
	public void testModifierService_lanceException_serviceNull() {
		// Arrange.

		// Act.
		repertoireService.modifierService(null);

		// Assert.
	}

}
