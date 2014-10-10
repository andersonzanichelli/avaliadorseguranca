package com.anderzanic.avaliador.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvaliadorService {
	
	private static final String ALFA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "01234567890";
	private static final String SYMBOLS = ")!@#$%^&*()";
	private static final Integer MULT_SEQ_ALFA = 3;
	private static final Integer MULT_SEQ_NUMBER = 3;
	private static final Integer MULT_SEQ_SYMBOL = 3;
	private static final Integer MULT_CONSEC_MAIUSCULAS = 2;
	private static final Integer MULT_CONSEC_MINUSCULAS = 2;
	private static final Integer MULT_CONSEC_NUMEROS = 2;

	public Integer somenteLetras(String password) {
		Integer tamanho = password.length();
		
		Pattern p = Pattern.compile("[a-zA-Z]{" + tamanho + "}");
		Matcher m = p.matcher(password);

		if(m.matches()) {
			return - tamanho;
		}
		
		return 0;
	}

	public Integer somenteNumeros(String password) {
		Integer tamanho = password.length();
		
		Pattern p = Pattern.compile("[0-9]{" + tamanho + "}");
		Matcher m = p.matcher(password);

		if(m.matches()) {
			return - tamanho;
		}
		
		return 0;
	}

	public Integer caracteresRepetidos(String password) {
		Integer tamanho = password.length();
		Integer reducao = 0;
		Integer charRepetidos = 0;
		Integer charUnicos = 0;
		Boolean temRepetido = false;
		
		for(int i = 0; i < tamanho; i++) {
			for(int j = 0; j < tamanho; j++) {
				if(password.charAt(i) == password.charAt(j) && i != j) {
					temRepetido = true;
					reducao += Math.abs(tamanho / (j - i));
				}
			}
			
			if(temRepetido) {
				charRepetidos++;
				charUnicos = tamanho - charRepetidos;
				reducao = (int) ((charUnicos > 0) ? Math.ceil(reducao/charUnicos) : Math.ceil(reducao));
			}
		}
		
		return -reducao;
	}

	public Integer caracteresConsecutivos(String password) {
		Integer sequenciais = 0;
		
		for (int i = 0; i < (ALFA.length() - 3); i++) {
			StringBuffer sFwd = new StringBuffer(ALFA.substring(i, i + 3));
			String trio = sFwd.toString();
			String trioRev = sFwd.reverse().toString();
			if (password.toLowerCase().indexOf(trio) != -1 || password.toLowerCase().indexOf(trioRev) != -1) { 
				sequenciais++;
			}
		}
		
		return -(sequenciais * MULT_SEQ_ALFA);
	}
	
	public Integer numerosSequenciais(String password) {
		Integer sequenciais = 0;
		
		for (int i = 0; i < (NUMERIC.length() - 2); i++) {
			StringBuffer sFwd = new StringBuffer(NUMERIC.substring(i, i + 3));
			String trio = sFwd.toString();
			String trioRev = sFwd.reverse().toString();
			if (password.toLowerCase().indexOf(trio) != -1 || password.toLowerCase().indexOf(trioRev) != -1) { 
				sequenciais++;
			}
		}
		
		return -(sequenciais * MULT_SEQ_NUMBER);
	}
	
	public Integer simbolosSequenciais(String password) {
		Integer sequenciais = 0;
		
		for (int i = 0; i < (SYMBOLS.length() - 2); i++) {
			StringBuffer sFwd = new StringBuffer(SYMBOLS.substring(i, i + 3));
			String trio = sFwd.toString();
			String trioRev = sFwd.reverse().toString();
			if (password.toLowerCase().indexOf(trio) != -1 || password.toLowerCase().indexOf(trioRev) != -1) { 
				sequenciais++;
			}
		}
		
		return -(sequenciais * MULT_SEQ_SYMBOL);
	}

	public Integer caracteresMaiusculosConsecutivos(String password) {
		Integer tamanho = password.length();
		Integer tmp = 0;
		Integer maiusculasConsevutivas = 0;
		
		for (int i = 0; i < tamanho; i++) {
			String c = String.valueOf(password.charAt(i)); 
			
			if (Pattern.compile("[A-Z]", Pattern.DOTALL).matcher(c).matches()) {
				if (i != 0) {
					if ((tmp + 1) == i) { 
						maiusculasConsevutivas++; 
					} 
				}
				tmp = i;
			}
		}
		
		return -(maiusculasConsevutivas * MULT_CONSEC_MAIUSCULAS);
	}

	public Integer caracteresMinusculosConsecutivos(String password) {
		Integer tamanho = password.length();
		Integer tmp = 0;
		Integer minusculasConsevutivas = 0;
		
		for (int i = 0; i < tamanho; i++) {
			String c = String.valueOf(password.charAt(i)); 
			
			if (Pattern.compile("[a-z]", Pattern.DOTALL).matcher(c).matches()) {
				if (i != 0) {
					if ((tmp + 1) == i) { 
						minusculasConsevutivas++; 
					} 
				}
				tmp = i;
			}
		}
		
		return -(minusculasConsevutivas * MULT_CONSEC_MINUSCULAS);
	}

	public Integer numerosConsecutivos(String password) {
		Integer tamanho = password.length();
		Integer tmp = 0;
		Integer numerosConsevutivos = 0;
		
		for (int i = 0; i < tamanho; i++) {
			String c = String.valueOf(password.charAt(i)); 
			
			if (Pattern.compile("[0-9]", Pattern.DOTALL).matcher(c).matches()) {
				if (i != 0) {
					if ((tmp + 1) == i) { 
						numerosConsevutivos++; 
					} 
				}
				tmp = i;
			}
		}
		
		return -(numerosConsevutivos * MULT_CONSEC_NUMEROS);
	}
}
