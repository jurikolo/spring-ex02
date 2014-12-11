package org.talestats.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CityExtractTest {
	@Test
	public void testGetSize() throws Exception {
		CityExtract cityExtract = new CityExtract();
		assertEquals(0, cityExtract.getSize("Размер города сорок два"));
		assertEquals(6, cityExtract.getSize("Размер города: 6"));
		assertEquals(10, cityExtract.getSize("Размер города 10"));
		//assertEquals(10, cityExtract.getSize("10"));
	}
}