package br.com.softal.base.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;



public abstract class LtcEmailImpl implements LtcEmail {
	
	@Override
	public abstract void send() throws EmailException;
	
	public void configuraEmail(Email email) throws EmailException {
		email.setDebug( LtcEmailProperties.getInstance().isDebug() );
		email.setFrom( LtcEmailProperties.getInstance().getFrom() );
		email.setHostName(LtcEmailProperties.getInstance().getHostName() );
		email.setAuthentication(
			LtcEmailProperties.getInstance().getAuthenticationUser(), 
			LtcEmailProperties.getInstance().getAuthenticationPassword()
		);  
		email.setSmtpPort(LtcEmailProperties.getInstance().getPort());  
		email.setSSL(LtcEmailProperties.getInstance().isSsl());  
		email.setTLS(LtcEmailProperties.getInstance().isTsl());
	}

	/*@Override
	public void addTo(String to) {
		
	}

	@Override
	public void setSubject(String subject) {
		
	}

	@Override
	public void setMsg(String msg) {
		
	}*/
	
}
