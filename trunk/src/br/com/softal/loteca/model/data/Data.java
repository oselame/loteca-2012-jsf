package br.com.softal.loteca.model.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.jogo.Jogo;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcdata")
public class Data extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cddata")
	private long cdData;

	@Column(name = "dtdata")
	@Temporal(TemporalType.DATE)
	private Date dtData;

	@Column(name = "deobservacao")
	private String deObservacao;
	
	@Column(name = "flsituacao")
	private Long flSituacao;
	
	@Column(name = "flatualizoutimes")
	private Long flAtualizoutimes;
	
	@Column(name = "declassificacao")
	private String deClassificacao;
	
	@OneToMany(mappedBy="data", fetch=FetchType.LAZY)
	private List<Jogo> jogos;
	
	public Data() {
		super();
	}
	
	public Data(long cdData, Date dtData, String deObservacao,
			Long flSituacao, Long flAtualizoutimes, String deClassificacao) {
		this();
		this.inicializaRelacionamentos();
		this.cdData = cdData;
		this.dtData = dtData;
		this.deObservacao = deObservacao;
		this.flSituacao = flSituacao;
		this.flAtualizoutimes = flAtualizoutimes;
		this.deClassificacao = deClassificacao;
	}

	public long getCdData() {
		return cdData;
	}

	public void setCdData(long cdData) {
		this.cdData = cdData;
	}

	public Date getDtData() {
		return dtData;
	}

	public void setDtData(Date dtData) {
		this.dtData = dtData;
	}

	public String getDeObservacao() {
		return deObservacao;
	}

	public void setDeObservacao(String deObservacao) {
		this.deObservacao = deObservacao;
	}

	public Long getFlSituacao() {
		return flSituacao;
	}

	public void setFlSituacao(Long flSituacao) {
		this.flSituacao = flSituacao;
	}

	public Long getFlAtualizoutimes() {
		return flAtualizoutimes;
	}

	public void setFlAtualizoutimes(Long flAtualizoutimes) {
		this.flAtualizoutimes = flAtualizoutimes;
	}

	public String getDeClassificacao() {
		return deClassificacao;
	}

	public void setDeClassificacao(String deClassificacao) {
		this.deClassificacao = deClassificacao;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	@Override
	public void inicializaRelacionamentos() {
		setJogos(new ArrayList<Jogo>());
		
	}
	
}
