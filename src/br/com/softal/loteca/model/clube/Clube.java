package br.com.softal.loteca.model.clube;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.loteca.Loteca;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcclube")
public class Clube extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuseqclube")
	private long nuSeqclube;

	@Column(name = "cdclube")
	private Long cdClube;

	@Column(name = "nmclube")
	private String nmClube;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cdloteca", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private Loteca loteca;
	
	@OneToMany(mappedBy="clube", fetch=FetchType.LAZY)
	//@Cascade(CascadeType.ALL)
	private List<Clubeusuario> clubeusuarios;
	
	//TODO: fazer mapeamento eltcclassifclube

	public Clube() {
		super();
	}
	
	public Clube(long nuSeqclube, Long cdClube, String nmClube, Long cdLoteca) {
		this();
		this.inicializaRelacionamentos();
		this.nuSeqclube = nuSeqclube;
		this.cdClube = cdClube;
		this.nmClube = nmClube;
		getLoteca().setCdLoteca(cdLoteca);
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

	@Override
	public String toString() {
		return "nuSeqclube=" + getNuSeqclube() +
			" cdClube=" + getCdClube() +
			" nmClube=" + getNmClube();
	}

	public List<Clubeusuario> getClubeusuarios() {
		return clubeusuarios;
	}

	public void setClubeusuarios(List<Clubeusuario> clubeusuarios) {
		this.clubeusuarios = clubeusuarios;
	}
	
	@Override
	public void inicializaRelacionamentos() {
		setLoteca(new Loteca());
		setClubeusuarios(new ArrayList<Clubeusuario>());		
	}
	

}
