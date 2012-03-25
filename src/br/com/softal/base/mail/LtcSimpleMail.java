package br.com.softal.base.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class LtcSimpleMail extends LtcEmailImpl implements LtcEmail {
	
	private Email email;
	
	public LtcSimpleMail() {
		this(new SimpleEmail());
	}
	
	private LtcSimpleMail(Email email) {
		try {
			this.email = email;
			this.configuraEmail( this.email );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addTo(String to) throws EmailException {
		email.addTo(to);
	}

	@Override
	public void setSubject(String subject) throws EmailException {
		email.setSubject(subject);
	}

	@Override
	public void setMsg(String msg) throws EmailException {
		email.setMsg(msg);
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
	
	public static void main(String[] args) {
		LtcSimpleMail x = new LtcSimpleMail();
		try {
			x.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
