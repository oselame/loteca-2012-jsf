package br.com.softal.base.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.interfaces.LtcEmail;
import br.com.softal.base.mail.simplemail.LtcSimpleEmail;

public class TesteEmail {
	
	public void enviaSimpleMail() {
		LtcSimpleMail email = LtcEmailFactory.getInstance().createSimpleMail();
		try {
			email.addTo("adriano.ozelame@gmail.com");
			email.setSubject("sfddsafdsafds");
			email.setMsg("Email de teste do oselame");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public void enviaAttachMail() {
		LtcMultiPartEmail email = LtcEmailFactory.getInstance().createMultiPartEmail();
		try {
			email.addTo("adriano.ozelame@gmail.com");
			email.setSubject("sfddsafdsafds");
			email.setMsg("Email de teste do oselame");
			
			EmailAttachment anexo1 = new EmailAttachment();
			anexo1.setPath("c:/temp/lotecaatual.sql");
	        anexo1.setDisposition(EmailAttachment.ATTACHMENT);  
	        anexo1.setDescription("Exemplo de arquivo anexo");  
	        anexo1.setName("teste.txt");   
			
			email.addAttach(anexo1);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public void enviaHtmlMail() {
		LtcHtmlEmail email = LtcEmailFactory.getInstance().createHtmlEmail();
		try {
			email.addTo("adriano.ozelame@gmail.com");
			email.setSubject("sfddsafdsafds");
			email.setTextMsg("Email de teste do oselame");
			
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<body>");
			html.append("<h1>Teste</h1>");
			html.append("</body>");
			html.append("</html>");
			email.setHtmlMsg( html.toString() );
			
			EmailAttachment anexo1 = new EmailAttachment();
			anexo1.setPath("c:/temp/lotecaatual.sql");
			anexo1.setDisposition(EmailAttachment.ATTACHMENT);  
			anexo1.setDescription("lotecaatual.sql");  
			anexo1.setName("lotecaatual.sql");  
			email.addAttach(anexo1);
			
			EmailAttachment anexo2 = new EmailAttachment();
			anexo2.setPath("c:/temp/t.txt");
			anexo2.setDisposition(EmailAttachment.ATTACHMENT);  
			anexo2.setDescription("t.txt");  
			anexo2.setName("t.txt");  
			email.addAttach(anexo2);
			
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public TesteEmail() {
		
	}
	
	public static void main(String[] args) {
		TesteEmail t = new TesteEmail();
		//-- t.enviaSimpleMail();
		//-- t.enviaAttachMail();
		t.enviaHtmlMail();
	}
}
