package br.com.softal.base.mail;

import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.simplemail.LtcEmail;

public class TesteEmail {
	public TesteEmail() {
		LtcEmail email = LtcEmailFactory.getInstance().createSimpleMail();
		try {
			email.addTo("adriano.ozelame@gmail.com");
			email.setSubject("sfddsafdsafds");
			email.setMsg("Email de teste do oselame");
			email.
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TesteEmail t = new TesteEmail();
	}
}
