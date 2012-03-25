package br.com.softal.base.mail;

import br.com.softal.base.mail.simplemail.LtcEmail;


public class LtcEmailFactory {
	
	private static LtcEmailFactory emailFactory;
	private LtcEmail email;
	
	private LtcEmailFactory() {
	}
	
	public static LtcEmailFactory getInstance() {
		if (emailFactory == null) {
			emailFactory = new LtcEmailFactory();
		}
		return emailFactory;
	}
	
	public LtcEmail createSimpleMail() {
		email = new LtcSimpleMail();
		return email;
	}
	
	public LtcEmail createMultiPartEmail() {
		email = new LtcMultiPartEmail();
		return email;
	}
	/*
	public LtcEmail createHtmlEmail() {
		email = new LtcHtmlEmail();
		return email;
	}*/

}
