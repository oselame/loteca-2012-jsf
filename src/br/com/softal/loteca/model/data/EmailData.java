package br.com.softal.loteca.model.data;

import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.softal.base.mail.LtcEmailFactory;
import br.com.softal.base.mail.LtcHtmlEmail;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.Usuariodata;
import br.com.softal.loteca.util.Constantes;

public class EmailData {
	
	private static String montaEmailJogoLiberadoHtml(Data data, Usuario usuario) {
		StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("\t<body>");
		html.append("\t\t<h3>" + data.getDeObservacao() + "</h3><br>");
	
		html.append("\t\t<table border=\"0\" style=\"border-style: solid; border-width: 1px;\">");
		html.append("\t\t\t<tr style=\"background-color: #2b3a3c; color: white;\">");
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
			html.append(j.getCdJogo() + ") [   ] [   ] [   ] " + j.getDeJogo() + "\n");
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
		html.append("\t\tAcesso: http://" + Constantes.SERVIDOR_PORTA + "/loteca");
		html.append("\t</body>");
		html.append("</html>");
		return html.toString();
	}
	
	private static String getDescricaoColuna(Long valor) {
		String retorno = "";
		switch (valor.intValue()) {
			case 0: retorno = "Empate"; break;
			case 1: retorno = "Coluna 1"; break;
			case 2: retorno = "Coluna 2"; break;
		}
		return retorno;
	}
	
	private static String verificaAcerto(Jogousuario ju) {
		String retorno = "";
		if (ju.getJogo().getTpResultadofinal() == 1 && ju.getFlColuna1() == 1) {
			retorno = "X";
		} 
		
		if (ju.getJogo().getTpResultadofinal() == 0 && ju.getFlEmpate() == 1) {
			retorno = "X";
		} 
		
		if (ju.getJogo().getTpResultadofinal() == 2 && ju.getFlColuna2() == 1) {
			retorno = "X";
		}
		return retorno;
	}
	
	private static String montaEmailResultadoHtmlForaEmpresa(Data data, Usuario usuario, List<Usuariodata> rankingLotecaAtiva, List<Jogousuario> listaJogousuario) {
		StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("\t<body>");
		
		if (rankingLotecaAtiva.size() > 0) {
			html.append("\t\t<h3>Ranking</h3><br><br>");
			html.append("\t\t<table border=\"0\" width=\"100%\">");
			
			html.append("\t\t\t<tr>");
			html.append("\t\t\t\t<td>Pos.</td>");
			html.append("\t\t\t\t<td>Participante</td>");
			html.append("\t\t\t\t<td>PR</td>");
			html.append("\t\t\t\t<td>PL</td>");
			html.append("\t\t\t\t<td>PT</td>");
			html.append("\t\t\t</tr>");
			for (Usuariodata ud : rankingLotecaAtiva) {
				html.append("\t\t\t<tr>");
				
				html.append("\t\t\t\t<td>" + ud.getNuPosicaofinal() + "</td>");
				html.append("\t\t\t\t<td>" + ud.getLotecausuario().getUsuario().getNmUsuario() + "</td>");
				html.append("\t\t\t\t<td>" + ud.getNuPontosrodada() + "</td>");
				html.append("\t\t\t\t<td>" + ud.getNuPontoslista() + "</td>");
				html.append("\t\t\t\t<td>" + ud.getNuPontosfinal() + "</td>");
				
				html.append("\t\t\t</tr>");
			}
			html.append("\t\t</table>");
			html.append("\t\t<br><br>");
		}
		
		if (listaJogousuario.size() > 0) {
			html.append("\t\t<h3>Resultado rodada</h3><br><br>");
			html.append("\t\t<table border=\"0\" width=\"100%\">");
			html.append("\t\t\t<tr>");
			html.append("\t\t\t\t<td>C�digo</td>");
			html.append("\t\t\t\t<td>Jogo</td>");
			html.append("\t\t\t\t<td align=\"center\">Coluna 1</td>");
			html.append("\t\t\t\t<td align=\"center\">Empate</td>");
			html.append("\t\t\t\t<td align=\"center\">Coluna 2</td>");
			html.append("\t\t\t\t<td align=\"center\">Resultado</td>");
			html.append("\t\t\t\t<td align=\"center\">Acerto</td>");
			html.append("\t\t\t</tr>");
			for (Jogousuario ju : listaJogousuario) {
				html.append("\t\t\t<tr>");
				html.append("\t\t\t\t<td>" + ju.getJogo().getCdJogo() + "</td>");
				html.append("\t\t\t\t<td>" + ju.getJogo().getDeJogo() + "</td>");
				html.append("\t\t\t\t<td align=\"center\">" + (ju.getFlColuna1() == 1 ? "X" : "") + "</td>");
				html.append("\t\t\t\t<td align=\"center\">" + (ju.getFlEmpate() == 1 ? "X" : "") + "</td>");
				html.append("\t\t\t\t<td align=\"center\">" + (ju.getFlColuna2() == 1 ? "X" : "") + "</td>");
				html.append("\t\t\t\t<td align=\"center\">" + EmailData.getDescricaoColuna(ju.getJogo().getTpResultadofinal()) + "</td>");
				html.append("\t\t\t\t<td align=\"center\">" + EmailData.verificaAcerto(ju) + "</td>");
				html.append("\t\t\t</tr>");
			}
			html.append("\t\t</table>");
		}
		
		html.append("\t</body>");
		html.append("</html>");
		return html.toString();
	}
	
	private static String montaEmailResultadoText(Data data, Usuario usuario) {
		StringBuilder html = new StringBuilder();
		html.append("Loteca - Resultado da Loteca já cadastrado.\n\n");
		html.append("\t\tAcesso: http://" + Constantes.SERVIDOR_PORTA + "/loteca");
		return html.toString();
	}
	
	private static String montaEmailResultadoTextForaEmpresa(Data data, Usuario usuario, List<Usuariodata> rankingLotecaAtiva, List<Jogousuario> listaJogousuario) {
		StringBuilder html = new StringBuilder();
		html.append("Loteca - Resultado da Loteca já cadastrado.\n\n");
		html.append("\t\tAcesso: http://" + Constantes.SERVIDOR_PORTA + "/loteca");
		return html.toString();
	}
	
	public static void enviaEmailResultado(Data data, List<Lotecausuario> usuarios) {
		try {
			for (Lotecausuario lc : usuarios) {
				if (!lc.getUsuario().isForaempresa()) {
					LtcHtmlEmail email = LtcEmailFactory.getInstance().createHtmlEmail();
					email.setSubject("Loteca - Resultado da Loteca já cadastrado");
					email.setHtmlMsg( EmailData.montaEmailResultadoHtml(data, lc.getUsuario()) );
					email.setTextMsg( EmailData.montaEmailResultadoText(data, lc.getUsuario()) );
					email.addTo(lc.getUsuario().getDeEmail());
					email.send();
				}
			}
			
			
			List<Usuariodata> listaRankingLotecaAtiva = LtcServiceLocator.getInstance().getLotecaService().findAllDadosRankingLotecaAtiva();
			for (Lotecausuario lc : usuarios) {
				if (lc.getUsuario().isForaempresa()) {
					List<Jogousuario> listaJogousuario = LtcServiceLocator.getInstance().getLotecaService().findAllJogoUsuario(data, lc);
					
					LtcHtmlEmail email2 = LtcEmailFactory.getInstance().createHtmlEmail();
					email2.setSubject("Loteca - Resultado da Loteca já cadastrado");
					email2.setHtmlMsg( EmailData.montaEmailResultadoHtmlForaEmpresa(data, lc.getUsuario(), listaRankingLotecaAtiva, listaJogousuario) );
					email2.setTextMsg( EmailData.montaEmailResultadoTextForaEmpresa(data, lc.getUsuario(), listaRankingLotecaAtiva, listaJogousuario) );
					email2.addTo(lc.getUsuario().getDeEmailpessoal());
					email2.send();
				}
			}
		} catch (EmailException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
