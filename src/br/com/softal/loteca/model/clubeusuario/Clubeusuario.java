package br.com.softal.loteca.model.clubeusuario;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcclubeusuario")
public class Clubeusuario extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuseqclubeusuario")
	@OneToMany(mappedBy = "clubeusuario")
	private long nuSeqclubeusuario;

	@Column(name = "nuposicao")
	private Long nuPosicao;

	@Column(name = "flrebaixado")
	private Long flRebaixado;

	@Column(name = "nupontos")
	private Long nuPontos;

	@ManyToOne(optional = false, targetEntity = Clube.class)
	@JoinColumn(name = "nuSeqclube", referencedColumnName = "nuSeqclube") //--, insertable=false, updatable=false
	private Clube clube;

	@ManyToOne(optional = false, targetEntity = Lotecausuario.class)
	@JoinColumn(name = "nuSeqlotecausuario", referencedColumnName = "nuSeqlotecausuario") //--, insertable=false, updatable=false
	private Lotecausuario lotecausuario;

	public Clubeusuario() {
		super();
	}

	public long getNuSeqclubeusuario() {
		return nuSeqclubeusuario;
	}

	public void setNuSeqclubeusuario(long nuSeqclubeusuario) {
		this.nuSeqclubeusuario = nuSeqclubeusuario;
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}

	public Lotecausuario getLotecausuario() {
		return lotecausuario;
	}

	public void setLotecausuario(Lotecausuario lotecausuario) {
		this.lotecausuario = lotecausuario;
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
