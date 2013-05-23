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
		this.email.setDebug( LtcEmailProperties.getInstance().isDebug() );
		this.email.setFrom( LtcEmailProperties.getInstance().getFrom() );
		this.email.setHostName(LtcEmailProperties.getInstance().getHostName() );
		this.email.setAuthentication(
			LtcEmailProperties.getInstance().getAuthenticationUser(), 
			LtcEmailProperties.getInstance().getAuthenticationPassword()
		);  
		this.email.setSmtpPort(LtcEmailProperties.getInstance().getPort());  
		this.email.setSSL(LtcEmailProperties.getInstance().isSsl());  
		this.email.setTLS(LtcEmailProperties.getInstance().isTsl());
	}
	
	@Override
	public void from(String from) throws EmailException {
		this.email.setFrom(from);
	}
	
	@Override
	public void addTo(String to) throws EmailException {
		this.email.addTo(to);
	}

	@Override
	public void setSubject(String subject) throws EmailException {
		this.email.setSubject(subject);
	}

	@Override
	public void setHtmlMsg(String msg) throws EmailException {
		this.email.setHtmlMsg(msg);
	}
	
	@Override
	public void setTextMsg(String msg) throws EmailException {
		this.email.setTextMsg(msg);
	}
	
	@Override
	public void addAttach(EmailAttachment attachment) throws EmailException {
		this.email.attach(attachment);		
	}
	
}
