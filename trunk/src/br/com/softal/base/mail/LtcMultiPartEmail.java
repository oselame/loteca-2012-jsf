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
		// TODO Auto-generated method stub
		
	}

}
