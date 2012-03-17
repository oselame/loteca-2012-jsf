package br.com.softal.loteca.model.usuariodata;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcusuariodata")
public class Usuariodata extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuSequsuariodata")
	@OneToMany(mappedBy = "usuariodata")
	private long nuSequsuariodata;

	@Column(name = "nuSeqlotecausuario")
	private Long nuSeqlotecausuario;

	@Column(name = "cdData")
	private Long cdData;

	@Column(name = "flApostou")
	private Long flApostou;

	@Column(name = "nuTotalpontos")
	private Long nuTotalpontos;

	@Column(name = "flCanhoto")
	private Long flCanhoto;

	@Column(name = "flGeradoaleat")
	private Long flGeradoaleat;

	@Column(name = "nuPosicao")
	private Long nuPosicao;

	@Column(name = "nuTotallistas")
	private Long nuTotallistas;

	@Column(name = "deBytesjogo")
	private String deBytesjogo;

	@Column(name = "flPagou")
	private Long flPagou;

	@Column(name = "nuPosicaofinal")
	private Long nuPosicaofinal;

	public Usuariodata() {
		super();
	}

	public long getNuSequsuariodata() {
		return nuSequsuariodata;
	}

	public void setNuSequsuariodata(long nuSequsuariodata) {
		this.nuSequsuariodata = nuSequsuariodata;
	}

	public Long getNuSeqlotecausuario() {
		return nuSeqlotecausuario;
	}

	public void setNuSeqlotecausuario(Long nuSeqlotecausuario) {
		this.nuSeqlotecausuario = nuSeqlotecausuario;
	}

	public Long getCdData() {
		return cdData;
	}

	public void setCdData(Long cdData) {
		this.cdData = cdData;
	}

	public Long getFlApostou() {
		return flApostou;
	}

	public void setFlApostou(Long flApostou) {
		this.flApostou = flApostou;
	}

	public Long getNuTotalpontos() {
		return nuTotalpontos;
	}

	public void setNuTotalpontos(Long nuTotalpontos) {
		this.nuTotalpontos = nuTotalpontos;
	}

	public Long getFlCanhoto() {
		return flCanhoto;
	}

	public void setFlCanhoto(Long flCanhoto) {
		this.flCanhoto = flCanhoto;
	}

	public Long getFlGeradoaleat() {
		return flGeradoaleat;
	}

	public void setFlGeradoaleat(Long flGeradoaleat) {
		this.flGeradoaleat = flGeradoaleat;
	}

	public Long getNuPosicao() {
		return nuPosicao;
	}

	public void setNuPosicao(Long nuPosicao) {
		this.nuPosicao = nuPosicao;
	}

	public Long getNuTotallistas() {
		return nuTotallistas;
	}

	public void setNuTotallistas(Long nuTotallistas) {
		this.nuTotallistas = nuTotallistas;
	}

	public String getDeBytesjogo() {
		return deBytesjogo;
	}

	public void setDeBytesjogo(String deBytesjogo) {
		this.deBytesjogo = deBytesjogo;
	}

	public Long getFlPagou() {
		return flPagou;
	}

	public void setFlPagou(Long flPagou) {
		this.flPagou = flPagou;
	}

	public Long getNuPosicaofinal() {
		return nuPosicaofinal;
	}

	public void setNuPosicaofinal(Long nuPosicaofinal) {
		this.nuPosicaofinal = nuPosicaofinal;
	}

}
