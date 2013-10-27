package br.com.softal.loteca.model.histusuariodata;

public class Teste {
	
	public static void main(String[] args) {
		String valor = "001010010100010001010100110001001100010001";
		int j = 0;
		for (int i = 0;i<14;i++) {
			String sub = valor.substring(j, j+3);
			char[] charArray = sub.toCharArray();
			j = j+3;
		}
	}
}
