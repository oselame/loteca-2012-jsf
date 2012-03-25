package br.com.softal.base.mail.multipart;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import br.com.softal.base.mail.LtcEmailProperties;

public abstract class LtcMultiPartEmailImpl implements LtcMultiPartEmail {
	
	protected MultiPartEmail email;
	
	public LtcMultiPartEmailImpl(MultiPartEmail email) {
		try {
			this.email = email;
			this.configuraEmail( this.email );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public abstract void send() throws EmailException;
	
	public void configuraEmail(MultiPartEmail email) throws EmailException {
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
	public void addAttach(EmailAttachment attachment) throws EmailException {
		email.attach(attachment);		
	}
	
}
