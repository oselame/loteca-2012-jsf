package br.com.softal.base.mail.simplemail;

import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.interfaces.LtcEmail;

public interface LtcSimpleEmail extends LtcEmail {
	
	void send() throws EmailException;
	void addTo(String to) throws EmailException;
	void setSubject(String subject) throws EmailException;
	void setMsg(String msg) throws EmailException;
	
}
