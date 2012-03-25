package br.com.softal.base.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import br.com.softal.base.mail.simplemail.LtcEmailImpl;



public class LtcMultiPartEmail extends LtcEmailImpl  {

	public LtcMultiPartEmail(Email email) {
		super(new MultiPartEmail());
	}

	@Override
	public void send() throws EmailException {
		
	}

}
