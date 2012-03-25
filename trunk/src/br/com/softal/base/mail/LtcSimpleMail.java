package br.com.softal.base.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.softal.base.mail.simplemail.LtcEmail;
import br.com.softal.base.mail.simplemail.LtcEmailImpl;

public class LtcSimpleMail extends LtcEmailImpl implements LtcEmail {
	
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
