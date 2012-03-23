package br.com.softal.base.message;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessagesWeb extends Messages {
	
	public MessagesWeb() {
		super();
	}
	
	@Override
	public void addMessage(String message) {
		String titulo = getMessage("mensagem_sucesso");
		FacesMessage msg = new FacesMessage(titulo, getMessage(message));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@Override
	public void addMessage(Severity severity, String message) {
		String titulo = getMessage("mensagem_sucesso");
		if (severity.equals(FacesMessage.SEVERITY_ERROR)) {
			titulo = getMessage("mensagem_erro");
		} else if (severity.equals(FacesMessage.SEVERITY_FATAL)) {
			titulo = getMessage("mensagem_fatal");
		} else if (severity.equals(FacesMessage.SEVERITY_INFO)) {
			titulo = getMessage("mensagem_informacao");
		} else if (severity.equals(FacesMessage.SEVERITY_WARN)) {
			titulo = getMessage("mensagem_alerta");
		}  
		FacesMessage msg = new FacesMessage(severity, titulo, getMessage(message));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void addSucessMessage(String message) {
		this.addMessage( message );
	}
	
	public void addWarningMessage(String message) {
		this.addMessage(FacesMessage.SEVERITY_WARN, message );
	}
	
	public void addInfoMessage(String message) {
		this.addMessage(FacesMessage.SEVERITY_INFO, message );
	}
	
	public void addFatalMessage(String message) {
		this.addMessage(FacesMessage.SEVERITY_FATAL, message );
	}

}
