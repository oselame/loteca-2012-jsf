package br.com.softal.loteca.model.clube;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.loteca.Loteca;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcclube")
public class Clube extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToMany(mappedBy = "clube")
	@Column(name = "nuseqclube")
	private long nuSeqclube;

	@Column(name = "cdclube")
	private Long cdClube;

	@Column(name = "nmclube")
	private String nmClube;

	@ManyToOne(optional = false, targetEntity = Loteca.class)
	@JoinColumn(name = "cdLoteca", referencedColumnName = "cdLoteca")
	private Loteca loteca;

	public Clube() {
		super();
	}

	public long getNuSeqclube() {
		return nuSeqclube;
	}

	public void setNuSeqclube(long nuSeqclube) {
		this.nuSeqclube = nuSeqclube;
	}

	public Long getCdClube() {
		return cdClube;
	}

	public void setCdClube(Long cdClube) {
		this.cdClube = cdClube;
	}

	public String getNmClube() {
		return nmClube;
	}

	public void setNmClube(String nmClube) {
		this.nmClube = nmClube;
	}

	public Loteca getLoteca() {
		return loteca;
	}

	public void setLoteca(Loteca loteca) {
		this.loteca = loteca;
	}

}
