package br.com.softal.loteca.model.clubeusuario;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcclubeusuario")
public class Clubeusuario extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuseqclubeusuario")
	@OneToMany(mappedBy = "clubeusuario")
	private long nuSeqclubeusuario;

	@Column(name = "nuseqlotecausuario")
	private Long nuSeqlotecausuario;

	@Column(name = "nuseqclube")
	private Long nuSeqclube;

	@Column(name = "nuposicao")
	private Long nuPosicao;

	@Column(name = "flrebaixado")
	private Long flRebaixado;

	@Column(name = "nupontos")
	private Long nuPontos;

	public Clubeusuario() {
		super();
	}

	public long getNuSeqclubeusuario() {
		return nuSeqclubeusuario;
	}

	public void setNuSeqclubeusuario(long nuSeqclubeusuario) {
		this.nuSeqclubeusuario = nuSeqclubeusuario;
	}

	public Long getNuSeqlotecausuario() {
		return nuSeqlotecausuario;
	}

	public void setNuSeqlotecausuario(Long nuSeqlotecausuario) {
		this.nuSeqlotecausuario = nuSeqlotecausuario;
	}

	public Long getNuSeqclube() {
		return nuSeqclube;
	}

	public void setNuSeqclube(Long nuSeqclube) {
		this.nuSeqclube = nuSeqclube;
	}

	public Long getNuPosicao() {
		return nuPosicao;
	}

	public void setNuPosicao(Long nuPosicao) {
		this.nuPosicao = nuPosicao;
	}

	public Long getFlRebaixado() {
		return flRebaixado;
	}

	public void setFlRebaixado(Long flRebaixado) {
		this.flRebaixado = flRebaixado;
	}

	public Long getNuPontos() {
		return nuPontos;
	}

	public void setNuPontos(Long nuPontos) {
		this.nuPontos = nuPontos;
	}

}
