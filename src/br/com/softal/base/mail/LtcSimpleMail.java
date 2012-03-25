package br.com.softal.base.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.softal.base.mail.simplemail.LtcSimpleEmailImpl;

public class LtcSimpleMail extends LtcSimpleEmailImpl {
	
	public LtcSimpleMail() {
		super(new SimpleEmail());
	}

	@Override
	public void send() throws EmailException {
		if (email.getToAddresses() == null || email.getToAddresses().isEmpty()) {
			throw new EmailException("Destinatario(s) nao informado");
		}
		if (email.getSubject() == null || email.getSubject().equalsIgnoreCase("")) {
			throw new EmailException("Assunto nao informado");
		}
		email.send();
	}
	
}
