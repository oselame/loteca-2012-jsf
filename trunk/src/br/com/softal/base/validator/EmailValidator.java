package br.com.softal.base.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.softal.base.message.MessagesWeb;
import br.com.softal.base.util.EmailUtil;

public class EmailValidator implements Validator {
	

	@Override
	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {
		MessagesWeb messages = new MessagesWeb();
		String enteredEmail = (String) object;
		if (!EmailUtil.isEmailValido( enteredEmail )) {
			FacesMessage message = new FacesMessage();
			message.setDetail( enteredEmail );
			//--message.setDetail(messages.getMessage("msg_warnig_email_invalido"));
			message.setSummary(messages.getMessage("msg_warnig_email_invalido"));
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}

}
