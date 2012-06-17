package br.com.softal.loteca.model.usuariodata;

public class UsuariodataDTO {

	private Long cdData;
	private Long nuSeqlotecausuario;
	private Long nuPontoscartao;
	private Long nuPontosranking;
	private Long nuPontosfinal;

	public Long getCdData() {
		return cdData;
	}

	public void setCdData(Long cdData) {
		this.cdData = cdData;
	}

	public Long getNuSeqlotecausuario() {
		return nuSeqlotecausuario;
	}

	public void setNuSeqlotecausuario(Long nuSeqlotecausuario) {
		this.nuSeqlotecausuario = nuSeqlotecausuario;
	}

	public Long getNuPontosfinal() {
		return nuPontosfinal;
	}

	public void setNuPontosfinal(Long nuPontosfinal) {
		this.nuPontosfinal = nuPontosfinal;
	}

	public Long getNuPontosranking() {
		return nuPontosranking;
	}

	public void setNuPontosranking(Long nuPontosranking) {
		this.nuPontosranking = nuPontosranking;
	}

	public Long getNuPontoscartao() {
		return nuPontoscartao;
	}

	public void setNuPontoscartao(Long nuPontoscartao) {
		this.nuPontoscartao = nuPontoscartao;
	}

}
