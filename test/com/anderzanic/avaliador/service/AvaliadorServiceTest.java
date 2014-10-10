package com.anderzanic.avaliador.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AvaliadorServiceTest {

	AvaliadorService avaliador = null;
	
	@Before
	public void init() {
		avaliador = new AvaliadorService();
	}
	
	@Test
	public void somenteLetrasTest() {
		assertEquals(new Integer(-3), avaliador.somenteLetras("abc"));
		assertEquals(new Integer(-5), avaliador.somenteLetras("abcAZ"));
		assertNotEquals(new Integer(-3), avaliador.somenteLetras("ABcd"));
	}
	
	@Test
	public void somenteNumerosTest() {
		assertEquals(new Integer(-3), avaliador.somenteNumeros("123"));
		assertEquals(new Integer(-5), avaliador.somenteNumeros("12345"));
		assertNotEquals(new Integer(-3), avaliador.somenteNumeros("1234"));
	}
	
	@Test
	public void caracteresRepetidosTest() {
		assertEquals(new Integer(-12), avaliador.caracteresRepetidos("aaa"));
		assertEquals(new Integer(-27), avaliador.caracteresRepetidos("aaaxxx"));
		assertEquals(new Integer(0), avaliador.caracteresRepetidos("aAbBcC"));
	}
	
	@Test
	public void caracteresConsecutivosTest() {
		assertEquals(new Integer(-3), avaliador.caracteresConsecutivos("abc"));
		assertEquals(new Integer(-6), avaliador.caracteresConsecutivos("ABcd"));
		assertEquals(new Integer(-9), avaliador.caracteresConsecutivos("ABCDE"));
	}
	
	@Test
	public void caracteresMaiusculosConsecutivosTest() {
		assertEquals(new Integer(-4), avaliador.caracteresMaiusculosConsecutivos("ABC"));
		assertEquals(new Integer(-6), avaliador.caracteresMaiusculosConsecutivos("ABCD"));
		assertEquals(new Integer(-8), avaliador.caracteresMaiusculosConsecutivos("ABCDE"));
		assertNotEquals(new Integer(-8), avaliador.caracteresMaiusculosConsecutivos("ABCde"));
	}
	
	@Test
	public void caracteresMinusculosConsecutivosTest() {
		assertEquals(new Integer(0), avaliador.caracteresMinusculosConsecutivos("a"));
		assertEquals(new Integer(0), avaliador.caracteresMinusculosConsecutivos("123"));
		assertEquals(new Integer(-4), avaliador.caracteresMinusculosConsecutivos("abc"));
		assertEquals(new Integer(-6), avaliador.caracteresMinusculosConsecutivos("abcd"));
		assertEquals(new Integer(-8), avaliador.caracteresMinusculosConsecutivos("abcde"));
		assertNotEquals(new Integer(-8), avaliador.caracteresMinusculosConsecutivos("abcDE"));
	}
	
	@Test
	public void numerosConsecutivosTest() {
		assertEquals(new Integer(-4), avaliador.numerosConsecutivos("123"));
		assertEquals(new Integer(-6), avaliador.numerosConsecutivos("1234"));
		assertEquals(new Integer(-8), avaliador.numerosConsecutivos("12345"));
		assertNotEquals(new Integer(-8), avaliador.numerosConsecutivos("123aB"));
	}
	
	@Test
	public void numerosSequenciaisTest() {
		assertEquals(new Integer(0), avaliador.numerosSequenciais("abc"));
		assertEquals(new Integer(0), avaliador.numerosSequenciais("152"));
		assertEquals(new Integer(-3), avaliador.numerosSequenciais("123"));
		assertEquals(new Integer(-6), avaliador.numerosSequenciais("1234"));
		assertEquals(new Integer(-9), avaliador.numerosSequenciais("12345"));
	}
	
	@Test
	public void simbolosSequenciaisTest() {
		assertEquals(new Integer(0), avaliador.simbolosSequenciais("@(%"));
		assertEquals(new Integer(-3), avaliador.simbolosSequenciais("!@#"));
		assertEquals(new Integer(-6), avaliador.simbolosSequenciais("!@#$"));
		assertEquals(new Integer(-9), avaliador.simbolosSequenciais("!@#$%"));
		assertNotEquals(new Integer(-9), avaliador.simbolosSequenciais("12345"));
		assertNotEquals(new Integer(-9), avaliador.simbolosSequenciais("ABCDE"));
	}
	
	@After
	public void destroy() {
		avaliador = null;
	}
}
