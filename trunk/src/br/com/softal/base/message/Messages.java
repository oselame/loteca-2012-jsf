package br.com.softal.base.message;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage.Severity;

public abstract class Messages {
	
	private static ResourceBundle messages;

	public Messages() {
		messages = ResourceBundle.getBundle("br/com/softal/loteca/bundle/messages");
	}

	protected String getMessage(String typeMessage) {
		return messages.getString(typeMessage);
	}

	public abstract void addMessage(String message);
	
	public abstract void addMessage(Severity severity, String message);
}
