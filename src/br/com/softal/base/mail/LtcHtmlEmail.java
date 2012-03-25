package br.com.softal.base.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.softal.base.mail.htmlmail.LtcHtmlEmailImpl;


public class LtcHtmlEmail extends LtcHtmlEmailImpl  {

	public LtcHtmlEmail() {
		super(new HtmlEmail());
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