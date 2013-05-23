package br.com.softal.loteca.model.jogousuario;

import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.LtcEmailFactory;
import br.com.softal.base.mail.LtcHtmlEmail;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.util.EmailUtil;
import br.com.softal.loteca.dto.ApostaDto;
import br.com.softal.loteca.model.data.Data;

public class EmailJogousuario {
	
	private static String montaEmailJogoLiberadoHtml(List<Jogousuario> jogos, Usuario usuario, Data data) {
		StringBuilder html = new StringBuilder();
		
		html.append("<html>");
		html.append("\t<body>");
		html.append("\t\t<h3>" + data.getDeObservacao() + "</h3><br>");
	
		html.append("\t\t<table border=\"0\" style=\"border-style: solid; border-width: 1px;\" cellpadding=\"0\" cellspacing=\"0\">");
		html.append("\t\t\t<tr style=\"background-color: #2b3a3c; color: white;\">");
		
		html.append("\t\t\t\t<td colspan=\"2\" align=\"center\" style=\"width:40px;\">Jogo</td>");
		html.append("\t\t\t\t<td align=\"center\" style=\"width:60px;\">Coluna 1</td>");
		html.append("\t\t\t\t<td align=\"center\" style=\"width:60px;\">Empate</td>");
		html.append("\t\t\t\t<td align=\"center\" style=\"width:60px;\">Coluna 2</td>");
		html.append("\t\t\t</tr>");
		
		for (Jogousuario j : jogos) {
			html.append("\t\t\t<tr>");
			html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + j.getJogo().getCdJogo() + "</td>");
			html.append("\t\t\t\t<td align=\"left\"   style=\"border-style: solid;border-width: 1px\">&nbsp;&nbsp;" + j.getJogo().getDeJogo() + "&nbsp;&nbsp;</td>");
			html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + (j.getFlColuna1() == 1 ? " X " : "   ") + "</td>");
			html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + (j.getFlEmpate()  == 1 ? " X " : "   ") + "</td>");
			html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + (j.getFlColuna2() == 1 ? " X " : "   ") + "</td>");
			html.append("\t\t\t</tr>");
		}
		html.append("\t\t</table>");
		html.append("\t</body>");
		html.append("</html>");
		return html.toString();
	}
	
	private static String montaEmailJogoLiberadoText(List<Jogousuario> jogos, Usuario usuario, Data data) {
		StringBuilder html = new StringBuilder();
		
		html.append(data.getDeObservacao() + "\n");
		
		for (Jogousuario j : jogos) {
			html.append(j.getJogo().getCdJogo() + 
					") [" + (j.getFlColuna1() == 1 ? " X " : "   ") + 
					"] [" + (j.getFlEmpate() == 1 ? " X " : "   ") + 
					"] [" + (j.getFlColuna2() == 1 ? " X " : "   ") + 
					"] " + j.getJogo().getDeJogo() + "\n");
		}
		return html.toString();
	}
	
	public static void enviaEmailJogousuarioParaEmailPessoal(List<Jogousuario> jogos, Usuario usuario) {
		try {
			LtcHtmlEmail email = LtcEmailFactory.getInstance().createHtmlEmail();
			Data data = jogos.get(0).getJogo().getData();
			email.setSubject( data.getDeObservacao() );
			email.setHtmlMsg( EmailJogousuario.montaEmailJogoLiberadoHtml(jogos, usuario, data) );
			email.setTextMsg( EmailJogousuario.montaEmailJogoLiberadoText(jogos, usuario, data) );
			email.addTo(usuario.getDeEmailpessoal());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}

	public static void enviaEmailJogousuarioParaLista(List<Jogousuario> jogos, Usuario usuario, ApostaDto aposta) {
		try {
			LtcHtmlEmail email = LtcEmailFactory.getInstance().createHtmlEmail();
			Data data = jogos.get(0).getJogo().getData();
			email.setSubject( aposta.getDeAssunto()  );
			email.setHtmlMsg( EmailJogousuario.montaEmailJogoLiberadoHtmlAposta(jogos, usuario, data, aposta) );
			email.from( usuario.getDeEmail() );
			
			String[] emails = aposta.getEmailsValidos().split(",");
			for (String e : emails) {
					email.addTo( e );
			}
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}

	private static String montaEmailJogoLiberadoHtmlAposta(List<Jogousuario> jogos, Usuario usuario, Data data, ApostaDto aposta) {
		StringBuilder html = new StringBuilder();
		
		html.append("<html>");
		html.append("\t<body>");
		
		html.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		html.append("<tr>");
		html.append("<td align=\"right\" style=\"width:60px;\"><strong>Desafiante: </strong></td>");
		html.append("<td>" + aposta.getDeEmaildesafiante() + "</td>");
		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<td align=\"right\"><strong>Desafiado: </strong></td>");
		html.append("<td>" + aposta.getDeEmaildesafiado() + "</td>");
		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<td align=\"right\"><strong>Aposta: </strong></td>");
		html.append("<td>" + aposta.getDeAposta() + "</td>");
		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<td align=\"right\"><strong>Testemunhas: </strong></td>");
		html.append("<td>");
				String[] emails = aposta.getEmailsValidos().split(",");
				for (String e : emails) {
					html.append(e + "<br/>");
				}
		html.append("</td>");
		html.append("</tr>");
		html.append("</table>");
		html.append("<br/><br/>");
		
		
		if (aposta.getFlIncluircanhoto() != null) {
			html.append("\t\t<h3>" + data.getDeObservacao() + "</h3><br>");
		
			html.append("\t\t<table border=\"0\" style=\"border-style: solid; border-width: 1px;\" cellpadding=\"0\" cellspacing=\"0\">");
			html.append("\t\t\t<tr style=\"background-color: #2b3a3c; color: white;\">");
			
			html.append("\t\t\t\t<td colspan=\"2\" align=\"center\" style=\"width:40px;\">Jogo</td>");
			html.append("\t\t\t\t<td align=\"center\" style=\"width:60px;\">Coluna 1</td>");
			html.append("\t\t\t\t<td align=\"center\" style=\"width:60px;\">Empate</td>");
			html.append("\t\t\t\t<td align=\"center\" style=\"width:60px;\">Coluna 2</td>");
			html.append("\t\t\t</tr>");
			
			for (Jogousuario j : jogos) {
				html.append("\t\t\t<tr>");
				html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + j.getJogo().getCdJogo() + "</td>");
				html.append("\t\t\t\t<td align=\"left\"   style=\"border-style: solid;border-width: 1px\">&nbsp;&nbsp;" + j.getJogo().getDeJogo() + "&nbsp;&nbsp;</td>");
				html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + (j.getFlColuna1() == 1 ? " X " : "   ") + "</td>");
				html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + (j.getFlEmpate()  == 1 ? " X " : "   ") + "</td>");
				html.append("\t\t\t\t<td align=\"center\" style=\"border-style: solid;border-width: 1px\">" + (j.getFlColuna2() == 1 ? " X " : "   ") + "</td>");
				html.append("\t\t\t</tr>");
			}
			html.append("\t\t</table>");
		}
		html.append("\t</body>");
		html.append("</html>");
		return html.toString();
	}

}
