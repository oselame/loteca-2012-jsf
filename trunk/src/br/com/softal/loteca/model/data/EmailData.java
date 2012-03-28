package br.com.softal.loteca.model.data;

import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.LtcEmailFactory;
import br.com.softal.base.mail.LtcHtmlEmail;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public class EmailData {
	
	private static String montaEmailJogoLiberadoHtml(Data data, Usuario usuario) {
		StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("\t<body>");
		html.append("\t\t<h3>" + data.getDeObservacao() + "</h3><br>");
		
		html.append("\t\t<table border=\"1\" >");
		html.append("\t\t\t<tr>");
		html.append("\t\t\t\t<td colspan=\"2\">Jogo</td>");
		html.append("\t\t\t\t<td align=\"center\">Coluna 1</td>");
		html.append("\t\t\t\t<td align=\"center\">Empate</td>");
		html.append("\t\t\t\t<td align=\"center\">Coluna 2</td>");
		html.append("\t\t\t</tr>");
		
		for (Jogo j : data.getJogos()) {
			html.append("\t\t\t<tr>");
			html.append("\t\t\t\t<td align=\"center\">" + j.getCdJogo() + "</td>");
			html.append("\t\t\t\t<td>" + j.getDeJogo() + "</td>");
			html.append("\t\t\t\t<td align=\"center\">[&nbsp;&nbsp;]</td>");
			html.append("\t\t\t\t<td align=\"center\">[&nbsp;&nbsp;]</td>");
			html.append("\t\t\t\t<td align=\"center\">[&nbsp;&nbsp;]</td>");
			html.append("\t\t\t</tr>");
		}
		html.append("\t\t</table>");
		html.append("\t</body>");
		html.append("</html>");
		return html.toString();
	}
	
	private static String montaEmailJogoLiberadoText(Data data, Usuario usuario) {
		StringBuilder html = new StringBuilder();
		html.append(data.getDeObservacao() + "\n");
		
		for (Jogo j : data.getJogos()) {
			html.append(j.getCdJogo() + ") [  ] [  ] [  ] " + j.getDeJogo() + "\n");
		}
		return html.toString();
	}
	
	public static void enviaEmailJogoLiberado(Data data, List<Lotecausuario> usuarios) {
		try {
			LtcHtmlEmail email = LtcEmailFactory.getInstance().createHtmlEmail();
			email.setSubject("Jogo Liberado");
			for (Lotecausuario lc : usuarios) {
				email.setHtmlMsg( EmailData.montaEmailJogoLiberadoHtml(data, lc.getUsuario()) );
				email.setTextMsg( EmailData.montaEmailJogoLiberadoText(data, lc.getUsuario()) );
				email.addTo(lc.getUsuario().getDeEmail());
			}
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	private static String montaEmailResultadoHtml(Data data, Usuario usuario) {
		StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("\t<body>");
		html.append("\t\t<h3>Loteca - Resultado da Loteca já cadastrado.</h3><br><br>");
		html.append("\t\tAcesso: http://soft030:8029/LotecaJsf");
		html.append("\t</body>");
		html.append("</html>");
		return html.toString();
	}
	
	private static String montaEmailResultadoText(Data data, Usuario usuario) {
		StringBuilder html = new StringBuilder();
		html.append("Loteca - Resultado da Loteca já cadastrado.\n\n");
		html.append("Acesso: http://soft030:8029/LotecaJsf");
		return html.toString();
	}
	
	public static void enviaEmailResultado(Data data, List<Lotecausuario> usuarios) {
		try {
			LtcHtmlEmail email = LtcEmailFactory.getInstance().createHtmlEmail();
			email.setSubject("Loteca - Resultado da Loteca já cadastrado");
			for (Lotecausuario lc : usuarios) {
				email.setHtmlMsg( EmailData.montaEmailResultadoHtml(data, lc.getUsuario()) );
				email.setTextMsg( EmailData.montaEmailResultadoText(data, lc.getUsuario()) );
				email.addTo(lc.getUsuario().getDeEmail());
			}
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}

}
