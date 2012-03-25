package br.com.softal.base.mail;



public class LtcEmailFactory {
	
	private static LtcEmailFactory emailFactory;
	
	private LtcEmailFactory() {}
	
	public static LtcEmailFactory getInstance() {
		if (emailFactory == null) {
			emailFactory = new LtcEmailFactory();
		}
		return emailFactory;
	}
	
	public LtcSimpleMail createSimpleMail() {
		return new LtcSimpleMail();
	}
	
	public LtcMultiPartEmail createMultiPartEmail() {
		return new LtcMultiPartEmail();
	}
	
	public LtcHtmlEmail createHtmlEmail() {
		return new LtcHtmlEmail();
	}

}
