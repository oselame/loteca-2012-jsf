package br.com.softal.base.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.softal.base.message.MessagesWeb;

public class EmailValidator implements Validator {
	

	@Override
	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {
		MessagesWeb messages = new MessagesWeb();
		String enteredEmail = (String) object;
		String EMAIL_PATTERN =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(EMAIL_PATTERN);

		// Match the given string with the pattern
		Matcher m = p.matcher(enteredEmail);

		// Check whether match is found
		boolean matchFound = m.matches();

		if (!matchFound) {
			FacesMessage message = new FacesMessage();
			message.setDetail(messages.getMessage("msg_warnig_email_invalido"));
			message.setSummary(messages.getMessage("msg_warnig_email_invalido"));
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}

}
