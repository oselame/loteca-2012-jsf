package br.com.softal.base.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import br.com.softal.base.mail.multipart.LtcMultiPartEmailImpl;

public class LtcMultiPartEmail extends LtcMultiPartEmailImpl  {

	public LtcMultiPartEmail() {
		super(new MultiPartEmail());
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
