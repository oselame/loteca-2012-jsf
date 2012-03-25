package br.com.softal.base.mail.htmlmail;

import org.apache.commons.mail.EmailException;

public interface LtcHtmlEmail {
	
	void send() throws EmailException;
	void addTo(String to) throws EmailException;
	void setSubject(String subject) throws EmailException;
	void setHtmlMsg(String msg) throws EmailException;
	void setTextMsg(String msg) throws EmailException;
	
}
