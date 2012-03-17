package br.com.softal.loteca.model.data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcdata")
public class Data extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cddata")
	@OneToMany(mappedBy = "data")
	private long cdData;

	@Column(name = "dtdata")
	private String dtData;

	@Column(name = "deobservacao")
	private String deObservacao;
	
	@Column(name = "flsituacao")
	private Long flSituacao;
	
	@Column(name = "flatualizoutimes")
	private Long flAtualizoutimes;
	
	@Column(name = "declassificacao")
	private String deClassificacao;
	
	public Data() {
		super();
	}

	public long getCdData() {
		return cdData;
	}

	public void setCdData(long cdData) {
		this.cdData = cdData;
	}

	public String getDtData() {
		return dtData;
	}

	public void setDtData(String dtData) {
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
	
}
