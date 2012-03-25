package br.com.softal.base.mail.interfaces;

import org.apache.commons.mail.EmailException;

public interface LtcEmail {
	
	void send() throws EmailException;
	void addTo(String to) throws EmailException;
	void setSubject(String subject) throws EmailException;
	//void setMsg(String msg) throws EmailException;
	
}
