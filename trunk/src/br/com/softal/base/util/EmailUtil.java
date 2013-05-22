package br.com.softal.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {
	
	public static boolean isEmailValido(String email) {
		String EMAIL_PATTERN =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(EMAIL_PATTERN);

		Matcher m = p.matcher(email);

		return  m.matches();
	}
	
}
