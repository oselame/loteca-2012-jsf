package br.com.softal.base.mail;

import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.simplemail.LtcEmail;

public class TesteEmail {
	
	public void enviaSimpleMail() {
		LtcEmail email = LtcEmailFactory.getInstance().createSimpleMail();
		try {
			email.addTo("adriano.ozelame@gmail.com");
			email.setSubject("sfddsafdsafds");
			email.setMsg("Email de teste do oselame");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public void enviaAttachMail() {
		LtcEmail email = LtcEmailFactory.getInstance().createSimpleMail();
		try {
			email.addTo("adriano.ozelame@gmail.com");
			email.setSubject("sfddsafdsafds");
			email.setMsg("Email de teste do oselame");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public TesteEmail() {
		
	}
	
	public static void main(String[] args) {
		TesteEmail t = new TesteEmail();
	}
}
