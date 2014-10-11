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
		assertEquals(-3, avaliador.somenteLetras("abc"));
		assertEquals(-5, avaliador.somenteLetras("abcAZ"));
		assertNotEquals(-3, avaliador.somenteLetras("ABcd"));
	}
	
	@Test
	public void somenteNumerosTest() {
		assertEquals(-3, avaliador.somenteNumeros("123"));
		assertEquals(-5, avaliador.somenteNumeros("12345"));
		assertNotEquals(-3, avaliador.somenteNumeros("1234"));
	}
	
	@Test
	public void caracteresRepetidosTest() {
		assertEquals(-14, avaliador.caracteresRepetidos("aaa"));
		assertEquals(-28, avaliador.caracteresRepetidos("aaaxxx"));
		assertEquals(0, avaliador.caracteresRepetidos("aAbBcC"));
	}
	
	@Test
	public void caracteresConsecutivosTest() {
		assertEquals(-3, avaliador.caracteresConsecutivos("abc"));
		assertEquals(-6, avaliador.caracteresConsecutivos("ABcd"));
		assertEquals(-9, avaliador.caracteresConsecutivos("ABCDE"));
	}
	
	@Test
	public void caracteresMaiusculosConsecutivosTest() {
		assertEquals(-4, avaliador.caracteresMaiusculosConsecutivos("ABC"));
		assertEquals(-6, avaliador.caracteresMaiusculosConsecutivos("ABCD"));
		assertEquals(-8, avaliador.caracteresMaiusculosConsecutivos("ABCDE"));
		assertNotEquals(-8, avaliador.caracteresMaiusculosConsecutivos("ABCde"));
	}
	
	@Test
	public void caracteresMinusculosConsecutivosTest() {
		assertEquals(0, avaliador.caracteresMinusculosConsecutivos("a"));
		assertEquals(0, avaliador.caracteresMinusculosConsecutivos("123"));
		assertEquals(-4, avaliador.caracteresMinusculosConsecutivos("abc"));
		assertEquals(-6, avaliador.caracteresMinusculosConsecutivos("abcd"));
		assertEquals(-8, avaliador.caracteresMinusculosConsecutivos("abcde"));
		assertNotEquals(-8, avaliador.caracteresMinusculosConsecutivos("abcDE"));
	}
	
	@Test
	public void numerosConsecutivosTest() {
		assertEquals(-4, avaliador.numerosConsecutivos("123"));
		assertEquals(-6, avaliador.numerosConsecutivos("1234"));
		assertEquals(-8, avaliador.numerosConsecutivos("12345"));
		assertNotEquals(-8, avaliador.numerosConsecutivos("123aB"));
	}
	
	@Test
	public void numerosSequenciaisTest() {
		assertEquals(0, avaliador.numerosSequenciais("abc"));
		assertEquals(0, avaliador.numerosSequenciais("152"));
		assertEquals(-3, avaliador.numerosSequenciais("123"));
		assertEquals(-6, avaliador.numerosSequenciais("1234"));
		assertEquals(-9, avaliador.numerosSequenciais("12345"));
	}
	
	@Test
	public void simbolosSequenciaisTest() {
		assertEquals(0, avaliador.simbolosSequenciais("@(%"));
		assertEquals(-3, avaliador.simbolosSequenciais("!@#"));
		assertEquals(-6, avaliador.simbolosSequenciais("!@#$"));
		assertEquals(-9, avaliador.simbolosSequenciais("!@#$%"));
		assertNotEquals(-9, avaliador.simbolosSequenciais("12345"));
		assertNotEquals(-9, avaliador.simbolosSequenciais("ABCDE"));
	}
	
	@Test
	public void numeroCaracteresTest() {
		assertEquals(0, avaliador.numeroCaracteres(""));
		assertEquals(4, avaliador.numeroCaracteres("1"));
		assertEquals(12, avaliador.numeroCaracteres("123"));
		assertEquals(16, avaliador.numeroCaracteres("123x"));
	}
	
	@Test
	public void caracteresMaiusculosTest() {
		assertEquals(0, avaliador.caracteresMaiusculos("ABC"));
		assertEquals(2, avaliador.caracteresMaiusculos("ABCx"));
		assertEquals(4, avaliador.caracteresMaiusculos("ABCxy"));
		assertEquals(4, avaliador.caracteresMaiusculos("ABCxyT"));
		assertEquals(6, avaliador.caracteresMaiusculos("abcX"));
	}
	
	@Test
	public void caracteresMinusculosTest() {
		assertEquals(0, avaliador.caracteresMinusculos("abc"));
		assertEquals(2, avaliador.caracteresMinusculos("abcX"));
		assertEquals(4, avaliador.caracteresMinusculos("abcXY"));
		assertEquals(4, avaliador.caracteresMinusculos("abcXYt"));
		assertEquals(6, avaliador.caracteresMinusculos("ABCx"));
	}
	
	@Test
	public void numerosTest() {
		assertEquals(0, avaliador.numeros("123"));
		assertEquals(12, avaliador.numeros("123x"));
		assertEquals(16, avaliador.numeros("1234x"));
		assertEquals(24, avaliador.numeros("123x123"));
	}
	
	@Test
	public void simbolosTest() {
		assertEquals(6, avaliador.simbolos("!"));
		assertEquals(6, avaliador.simbolos("!x"));
		assertEquals(12, avaliador.simbolos("!@"));
		assertEquals(18, avaliador.simbolos("!@#"));
	}
	
	@Test
	public void midNumerosOrSimbolosTest() {
		assertEquals(2, avaliador.midNumeroOrSimbolos("!1#"));
		assertEquals(4, avaliador.midNumeroOrSimbolos("!1#x"));
		assertEquals(6, avaliador.midNumeroOrSimbolos("!1#xab!x"));
	}
	
	@Test
	public void requisitoTest() {
		assertEquals(0, avaliador.requisito("123"));
		assertEquals(10, avaliador.requisito("!2qTx$aZ"));
	}
	
	@Test
	public void pontuacaoTest(){
		assertEquals(2, avaliador.pontuacao("abc"));
		assertEquals(4, avaliador.pontuacao("123"));
		assertEquals(84, avaliador.pontuacao("!2qTx$aZ"));
		assertEquals(57, avaliador.pontuacao("!@#QWE"));
	}
	
	@Test
	public void avaliarTest() {
		assertEquals("{\"porcentagem\":\"2%\", \"complexidade\":\"Muito Fraca\", \"classe\":\"label label-danger\"}", avaliador.avaliar("abc"));
		assertEquals("{\"porcentagem\":\"4%\", \"complexidade\":\"Muito Fraca\", \"classe\":\"label label-danger\"}", avaliador.avaliar("123"));
		assertEquals("{\"porcentagem\":\"84%\", \"complexidade\":\"Muito Forte\", \"classe\":\"label label-success\"}", avaliador.avaliar("!2qTx$aZ"));
		assertEquals("{\"porcentagem\":\"57%\", \"complexidade\":\"Boa\", \"classe\":\"label label-info\"}", avaliador.avaliar("!@#QWE"));
	}
	
	@After
	public void destroy() {
		avaliador = null;
	}
}
