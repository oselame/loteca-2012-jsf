package br.com.softal.loteca.dto;

import br.com.softal.base.util.EmailUtil;

public class ApostaDto {

	private String deAssunto;
	private String deEmaildesafiante;
	private String deEmaildesafiado;
	
	private String deAposta;
	private String deListaemails;
	private String flIncluircanhoto;

	public String getDeAposta() {
		return deAposta;
	}

	public void setDeAposta(String deAposta) {
		this.deAposta = deAposta;
	}

	public String getDeListaemails() {
		return deListaemails;
	}

	public void setDeListaemails(String deListaemails) {
		this.deListaemails = deListaemails;
	}

	public String getDeAssunto() {
		return deAssunto;
	}

	public void setDeAssunto(String deAssunto) {
		this.deAssunto = deAssunto;
	}
	
	public boolean isApostavalida() {
		if ((this.getDeEmaildesafiante() == null) || this.getDeEmaildesafiante().equals("")) {
			return false;
		}
		if ((this.getDeEmaildesafiado() == null) || this.getDeEmaildesafiado().equals("")) {
			return false;
		}
		if ((this.getDeAposta() == null) || this.getDeAposta().equals("")) {
			return false;
		}
		if ((this.getDeListaemails() == null) || this.getDeListaemails().equals("")) {
			return false;
		}
		if ((this.getDeAssunto() == null) || this.getDeAssunto().equals("")) {
			return false;
		}
		return true;
	}
	
	public String getEmailsValidos() {
		String retorno = "";
		if (this.getDeListaemails() != null && !this.getDeListaemails().equals("")) {
			String[] emails = this.getDeListaemails().split(",");
			for (String email : emails) {
				if (EmailUtil.isEmailValido(email.trim())) {
					retorno += "," + email.trim();
				}
			}
		}
		return retorno.length() > 1 ? retorno.substring(1) : "";
	}

	public String getDeEmaildesafiante() {
		return deEmaildesafiante;
	}

	public void setDeEmaildesafiante(String deEmaildesafiante) {
		this.deEmaildesafiante = deEmaildesafiante;
	}

	public String getDeEmaildesafiado() {
		return deEmaildesafiado;
	}

	public void setDeEmaildesafiado(String deEmaildesafiado) {
		this.deEmaildesafiado = deEmaildesafiado;
	}

	public String getFlIncluircanhoto() {
		return flIncluircanhoto;
	}

	public void setFlIncluircanhoto(String flIncluircanhoto) {
		this.flIncluircanhoto = flIncluircanhoto;
	}
	
	
}
