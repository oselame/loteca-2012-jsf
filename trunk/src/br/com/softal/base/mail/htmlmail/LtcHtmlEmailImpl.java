package br.com.softal.base.mail.htmlmail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.softal.base.mail.LtcEmailProperties;

public abstract class LtcHtmlEmailImpl implements LtcHtmlEmail {
	
	protected HtmlEmail email;
	
	public LtcHtmlEmailImpl(HtmlEmail email) {
		try {
			this.email = email;
			this.configuraEmail( this.email );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public abstract void send() throws EmailException;
	
	public void configuraEmail(HtmlEmail email) throws EmailException {
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
	public void from(String from) throws EmailException {
		email.setFrom(from);
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
	public void setHtmlMsg(String msg) throws EmailException {
		email.setHtmlMsg(msg);
	}
	
	@Override
	public void setTextMsg(String msg) throws EmailException {
		email.setTextMsg(msg);
	}
	
	@Override
	public void addAttach(EmailAttachment attachment) throws EmailException {
		email.attach(attachment);		
	}
	
}
