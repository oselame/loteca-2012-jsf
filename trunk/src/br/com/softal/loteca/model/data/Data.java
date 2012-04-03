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
import javax.persistence.Transient;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.classifclube.Classifclube;
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
	
	@Column(name = "tpsituacao")
	private Long tpSituacao;
	
	@Column(name = "flatualizoutimes")
	private Long flAtualizoutimes;
	
	@Column(name = "flatualizouclassificacao")
	private Long flAtualizouclassificacao;
	
	@Column(name = "flatualizouresultados")
	private Long flAtualizouresultados;
	
	@Column(name = "flenviouemailjogoliberado")
	private Long flEnviouemailjogoliberado;
	
	@Column(name = "flenviouemailresultado")
	private Long flEnviouemailresultado;
	
	@Column(name = "declassificacao")
	private String deClassificacao;
	
	@OneToMany(mappedBy="data", fetch=FetchType.LAZY)
	private List<Jogo> jogos;
	
	@OneToMany(mappedBy="data", fetch=FetchType.LAZY)
	private List<Classifclube> classifclubes;
	
	public Data() {
		super();
	}
	
	public Data(long cdData, Date dtData, String deObservacao,
			Long tpSituacao, Long flAtualizoutimes, String deClassificacao) {
		this();
		this.inicializaRelacionamentos();
		this.cdData = cdData;
		this.dtData = dtData;
		this.deObservacao = deObservacao;
		this.tpSituacao = tpSituacao;
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

	public Long getTpSituacao() {
		return tpSituacao;
	}

	public void setTpSituacao(Long tpSituacao) {
		this.tpSituacao = tpSituacao;
	}

	public Long getFlAtualizoutimes() {
		return flAtualizoutimes;
	}

	public void setFlAtualizoutimes(Long flAtualizoutimes) {
		this.flAtualizoutimes = flAtualizoutimes;
	}
	
	public Boolean getBlAtualizoutimes() {
		return flAtualizoutimes != null && flAtualizoutimes == 1l;
	}
	
	public void setBlAtualizoutimes(Boolean blAtualizoutimes) {
		this.flAtualizoutimes = (blAtualizoutimes == null) ? 0l : (blAtualizoutimes ?  1l : 0l);
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
	
	public Long getFlAtualizouresultados() {
		return flAtualizouresultados;
	}

	public void setFlAtualizouresultados(Long flAtualizouresultados) {
		this.flAtualizouresultados = flAtualizouresultados;
	}
	
	public Boolean getBlAtualizouresultados() {
		return flAtualizouresultados != null && flAtualizouresultados == 1l;
	}
	
	public void setBlAtualizouresultados(Boolean blAtualizouresultados) {
		this.flAtualizouresultados = (blAtualizouresultados == null) ? 0l : (blAtualizouresultados ?  1l : 0l);
	}
	
	public Long getFlEnviouemailjogoliberado() {
		return flEnviouemailjogoliberado;
	}

	public void setFlEnviouemailjogoliberado(Long flEnviouemailjogoliberado) {
		this.flEnviouemailjogoliberado = flEnviouemailjogoliberado;
	}
	
	public Boolean getBlEnviouemailjogoliberado() {
		return flEnviouemailjogoliberado != null && flEnviouemailjogoliberado == 1l;
	}
	
	public void setBlEnviouemailjogoliberado(Boolean blEnviouemailjogoliberado) {
		this.flEnviouemailjogoliberado = (blEnviouemailjogoliberado == null) ? 0l : (blEnviouemailjogoliberado ?  1l : 0l);
	}

	public Long getFlEnviouemailresultado() {
		return flEnviouemailresultado;
	}

	public void setFlEnviouemailresultado(Long flEnviouemailresultado) {
		this.flEnviouemailresultado = flEnviouemailresultado;
	}
	
	public Boolean getBlEnviouemailresultado() {
		return flEnviouemailresultado != null && flEnviouemailresultado == 1l;
	}
	
	public void setBlEnviouemailresultado(Boolean blEnviouemailresultado) {
		this.flEnviouemailresultado = (blEnviouemailresultado == null) ? 0l : (blEnviouemailresultado ?  1l : 0l);
	}
	
	public List<Classifclube> getClassifclubes() {
		return classifclubes;
	}

	public void setClassifclubes(List<Classifclube> classifclubes) {
		this.classifclubes = classifclubes;
	}
	
	public Long getFlAtualizouclassificacao() {
		return flAtualizouclassificacao;
	}

	public void setFlAtualizouclassificacao(Long flAtualizouclassificacao) {
		this.flAtualizouclassificacao = flAtualizouclassificacao;
	}
	
	public Boolean getBlAtualizouclassificacao() {
		return flAtualizouclassificacao != null && flAtualizouclassificacao == 1l;
	}
	
	public void setBlAtualizouclassificacao(Boolean blAtualizouclassificacao) {
		this.flAtualizouclassificacao = (blAtualizouclassificacao == null) ? 0l : (blAtualizouclassificacao ?  1l : 0l);
	}
	

	/****************************************************************************************************************/

	@Override
	public void inicializaRelacionamentos() {
		setJogos(new ArrayList<Jogo>());
		setClassifclubes(new ArrayList<Classifclube>());
	}
	
}
