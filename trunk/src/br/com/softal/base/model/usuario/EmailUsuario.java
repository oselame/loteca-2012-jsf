package br.com.softal.base.model.usuario;

import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.LtcEmailFactory;
import br.com.softal.base.mail.LtcHtmlEmail;

public class EmailUsuario {
	
	private static String montaEmailJogoLiberadoHtml(Usuario usuario) {
		StringBuilder html = new StringBuilder();
		
		html.append("<html>");
		html.append("\t<body>");
		html.append("\t\t<h3>Recuperação de Senha</h3><br>");
		html.append("\t\t<br><br>");
		html.append("\t\tCaro " + usuario.getNmUsuario());
		html.append("\t\t<br><br>");
		html.append("\t\tSua senha é <strong>" + usuario.getDeSenha() + "</strong>");
		html.append("\t\t<br><br>");
		html.append("\t\tAtenciosamente");
		html.append("\t\t<br><br>");
		html.append("\t\tOrganização da loteca");
	
		html.append("\t</body>");
		html.append("</html>");
		return html.toString();
	}
	
	private static String montaEmailJogoLiberadoText(Usuario usuario) {
		StringBuilder html = new StringBuilder();
		html.append("Recuperação de Senha");
		html.append("----------------------------------");
		html.append("\t\tCaro " + usuario.getNmUsuario());
		html.append("\t\t\n\n");
		html.append("\t\tSua senha é <strong>" + usuario.getDeSenha() + "</strong>");
		html.append("\t\t\n\n");
		html.append("\t\tAtenciosamente");
		html.append("\t\t\n\n");
		html.append("\t\tOrganização da loteca");
		
		
		return html.toString();
	}
	
	public static void enviaEmailEsqueciMinhaSenha(Usuario usuario) {
		try {
			LtcHtmlEmail email = LtcEmailFactory.getInstance().createHtmlEmail();
			email.setSubject( "Loteca - Recuperação de Senha" );
			email.setHtmlMsg( EmailUsuario.montaEmailJogoLiberadoHtml(usuario) );
			email.setTextMsg( EmailUsuario.montaEmailJogoLiberadoText(usuario) );
			email.addTo(usuario.getDeEmail());
			if (usuario != null) {
				email.send();
			}
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}

}
