package com.anderzanic.avaliador.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.anderzanic.avaliador.model.Score;

public class AvaliadorService {
	
	private static final String ALFA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "01234567890";
	private static final String SIMBOLOS = ")!@#$%^&*()";
	private static final Integer MULT_SEQ_CHAR = 3;
	private static final Integer MULT_SEQ_NUMERO = 3;
	private static final Integer MULT_SEQ_SIMBOLO = 3;
	private static final Integer MULT_CONSEC_MAIUSCULAS = 2;
	private static final Integer MULT_CONSEC_MINUSCULAS = 2;
	private static final Integer MULT_CONSEC_NUMEROS = 2;
	private static final Integer MULT_CONSEC_SIMBOLOS = 1;
	private static final Integer MULT_SIMBOLOS = 6;
	private static final Integer MULT_NUMEROS = 4;
	private static final Integer MULT_NUMEROS_OR_SIMBOLO = 2;
	private static final Integer PASSWORD_LENGTH = 8;
	
	private Integer charMaiusculasBonus;
	private Integer charMinusculasBonus;
	private Integer numeroBonus;
	private Integer simboloBonus;
	private Integer midNumeroOrSimbolo;
	private Integer requisitoCaracteres = 0;

	/*
	 * Deductions
	 * 
	 */
	
	public int somenteLetras(String password) {
		Integer tamanho = password.length();
		
		Pattern p = Pattern.compile("[a-zA-Z]{" + tamanho + "}");
		Matcher m = p.matcher(password);

		if(m.matches()) {
			return - tamanho;
		}
		
		return 0;
	}

	public int somenteNumeros(String password) {
		Integer tamanho = password.length();
		
		Pattern p = Pattern.compile("[0-9]{" + tamanho + "}");
		Matcher m = p.matcher(password);

		if(m.matches()) {
			return - tamanho;
		}
		
		return 0;
	}
	
	public int caracteresRepetidos(String password) {
		Integer tamanho = password.length();
		Double reducao = 0.0;
		Integer charRepetidos = 0;
		Integer charUnicos = 0;
		Boolean temRepetido = false;
		
		for(int i = 0; i < tamanho; i++) {
			for(int j = 0; j < tamanho; j++) {
				if(password.charAt(i) == password.charAt(j) && i != j) {
					temRepetido = true;
					reducao += Math.abs(tamanho.doubleValue() / (j - i));
				}
			}
			
			if(temRepetido) {
				charRepetidos++;
				charUnicos = tamanho - charRepetidos;
				reducao = ((charUnicos > 0) ? Math.ceil(reducao/charUnicos) : Math.ceil(reducao));
			}
		}
		
		return -reducao.intValue();
	}

	public int caracteresConsecutivos(String password) {
		Integer sequenciais = 0;
		
		for (int i = 0; i < (ALFA.length() - 3); i++) {
			StringBuffer sFwd = new StringBuffer(ALFA.substring(i, i + 3));
			String trio = sFwd.toString();
			String trioRev = sFwd.reverse().toString();
			if (password.toLowerCase().indexOf(trio) != -1 || password.toLowerCase().indexOf(trioRev) != -1) { 
				sequenciais++;
			}
		}
		
		return -(sequenciais * MULT_SEQ_CHAR);
	}
	
	public int numerosSequenciais(String password) {
		Integer sequenciais = 0;
		
		for (int i = 0; i < (NUMERIC.length() - 2); i++) {
			StringBuffer sFwd = new StringBuffer(NUMERIC.substring(i, i + 3));
			String trio = sFwd.toString();
			String trioRev = sFwd.reverse().toString();
			if (password.toLowerCase().indexOf(trio) != -1 || password.toLowerCase().indexOf(trioRev) != -1) { 
				sequenciais++;
			}
		}
		
		return -(sequenciais * MULT_SEQ_NUMERO);
	}
	
	public int simbolosSequenciais(String password) {
		Integer sequenciais = 0;
		
		for (int i = 0; i < (SIMBOLOS.length() - 2); i++) {
			StringBuffer sFwd = new StringBuffer(SIMBOLOS.substring(i, i + 3));
			String trio = sFwd.toString();
			String trioRev = sFwd.reverse().toString();
			if (password.toLowerCase().indexOf(trio) != -1 || password.toLowerCase().indexOf(trioRev) != -1) { 
				sequenciais++;
			}
		}
		
		return -(sequenciais * MULT_SEQ_SIMBOLO);
	}

	public int caracteresMaiusculosConsecutivos(String password) {
		Integer tamanho = password.length();
		Integer tmp = 0;
		Integer maiusculasConsevutivas = 0;
		charMaiusculasBonus = 0;
		
		for (int i = 0; i < tamanho; i++) {
			String c = String.valueOf(password.charAt(i)); 
			
			if (Pattern.compile("[A-Z]", Pattern.DOTALL).matcher(c).matches()) {
				if (i != 0) {
					if ((tmp + 1) == i) { 
						maiusculasConsevutivas++; 
					} 
				}
				tmp = i;
				charMaiusculasBonus++;
			}
		}
		
		return -(maiusculasConsevutivas * MULT_CONSEC_MAIUSCULAS);
	}

	public int caracteresMinusculosConsecutivos(String password) {
		Integer tamanho = password.length();
		Integer tmp = 0;
		Integer minusculasConsevutivas = 0;
		charMinusculasBonus = 0;
		
		for (int i = 0; i < tamanho; i++) {
			String c = String.valueOf(password.charAt(i)); 
			
			if (Pattern.compile("[a-z]", Pattern.DOTALL).matcher(c).matches()) {
				if (i != 0) {
					if ((tmp + 1) == i) { 
						minusculasConsevutivas++; 
					} 
				}
				tmp = i;
				charMinusculasBonus++;
			}
		}
		
		return -(minusculasConsevutivas * MULT_CONSEC_MINUSCULAS);
	}

	public int numerosConsecutivos(String password) {
		Integer tamanho = password.length();
		Integer tmp = 0;
		Integer numerosConsevutivos = 0;
		numeroBonus = 0;
		midNumeroOrSimbolo = 0;
		
		for (int i = 0; i < tamanho; i++) {
			String c = String.valueOf(password.charAt(i)); 
			
			if (Pattern.compile("[0-9]", Pattern.DOTALL).matcher(c).matches()) {
				if (i > 0 && i < (tamanho - 1)) { 
					midNumeroOrSimbolo++; 
				}
				
				if (i != 0) {
					if ((tmp + 1) == i) { 
						numerosConsevutivos++; 
					} 
				}
				tmp = i;
				numeroBonus++;
			}
		}
		
		return -(numerosConsevutivos * MULT_CONSEC_NUMEROS);
	}
	
	public int simbolosConsecutivos(String password) {
		Integer tamanho = password.length();
		Integer tmp = 0;
		Integer simbolosConsevutivos = 0;
		simboloBonus = 0;
		
		if(null == midNumeroOrSimbolo) {
			midNumeroOrSimbolo = 0;
		}
		
		for (int i = 0; i < tamanho; i++) {
			String c = String.valueOf(password.charAt(i)); 
			
			if (Pattern.compile("[^a-zA-Z0-9_]", Pattern.DOTALL).matcher(c).matches()) {
				if (i > 0 && i < (tamanho - 1)) { 
					midNumeroOrSimbolo++; 
				}
				
				if (i != 0) {
					if ((tmp + 1) == i) { 
						simbolosConsevutivos++; 
					} 
				}
				tmp = i;
				simboloBonus++;
			}
		}
		
		return -(simbolosConsevutivos * MULT_CONSEC_SIMBOLOS);
	}
	
	/*
	 * Adictions
	 * 
	 */

	public int numeroCaracteres(String password) {
		return password.length() * 4;
	}

	public int caracteresMaiusculos(String password) {
		caracteresMaiusculosConsecutivos(password);
		return (charMaiusculasBonus == 0) ? charMaiusculasBonus : ((password.length() - charMaiusculasBonus) * 2);
	}

	public int caracteresMinusculos(String password) {
		caracteresMinusculosConsecutivos(password);
		return (charMinusculasBonus == 0) ? charMinusculasBonus : ((password.length() - charMinusculasBonus) * 2);
	}

	public int numeros(String password) {
		int bonus = 0;
		numerosConsecutivos(password);
		
		if (numeroBonus > 0 && numeroBonus < password.length()) {
			bonus = numeroBonus * MULT_NUMEROS;
		}
		return bonus;
	}

	public int simbolos(String password) {
		int bonus = 0;
		simbolosConsecutivos(password);
		
		if (simboloBonus > 0) {	
			bonus = simboloBonus * MULT_SIMBOLOS;
		}
		return bonus;
	}

	public int midNumeroOrSimbolos(String password) {
		int bonus = 0;
		
		numerosConsecutivos(password);
		simbolosConsecutivos(password);
		
		if (midNumeroOrSimbolo > 0) {	
			bonus = midNumeroOrSimbolo * MULT_NUMEROS_OR_SIMBOLO;
		}
		return bonus;
	}

	public int requisito(String password) {
		int resultado = 0;
		
		numerosConsecutivos(password);
		caracteresMaiusculosConsecutivos(password);
		caracteresMinusculosConsecutivos(password);
		simbolosConsecutivos(password);
		
		int[] arrChars = { password.length()
				, charMaiusculasBonus
				, charMinusculasBonus
				, numeroBonus
				, simboloBonus };
		
		String[] arrCharsIds = {"nLength","nAlphaUC","nAlphaLC","nNumber","nSymbol"};
		int arrCharsLen = arrChars.length;
		
		for (int c = 0; c < (arrCharsLen - 1); c++) {
			int minVal = 0;
			
			if (arrCharsIds[c] == "nLength") { 
				minVal = PASSWORD_LENGTH - 1; 
			}
			
			if (arrChars[c] == minVal + 1) {
				requisitoCaracteres++;
			} else if(arrChars[c] > minVal + 1) {
				requisitoCaracteres++;
			}
		}
		
		int nMinReqChars = 0;
		
		if (password.length() >= PASSWORD_LENGTH) {
			nMinReqChars = 3; 
		} else { 
			nMinReqChars = 4;
		}
		
		if (requisitoCaracteres > nMinReqChars) {
			resultado = requisitoCaracteres * 2; 
		}
		return resultado;
	}
	
	public String avaliar(String password) {
		return new Score(pontuacao(password)).toString();
	}

	public int pontuacao(String password) {
		int pontos = verificarBonus(password) + verificarDescontos(password);
		return pontos;
	}

	private int verificarDescontos(String password) {
		return somenteLetras(password)
			   + somenteNumeros(password)
			   + caracteresRepetidos(password)
			   + caracteresConsecutivos(password)
			   + numerosSequenciais(password)
			   + simbolosSequenciais(password)
			   + caracteresMaiusculosConsecutivos(password)
			   + caracteresMinusculosConsecutivos(password)
			   + numerosConsecutivos(password)
			   + simbolosConsecutivos(password);
	}

	private int verificarBonus(String password) {
		return numeroCaracteres(password) 
			   + caracteresMaiusculos(password)
			   + caracteresMinusculos(password)
			   + numeros(password)
			   + simbolos(password)
			   + midNumeroOrSimbolos(password)
			   + requisito(password);
	}
}
