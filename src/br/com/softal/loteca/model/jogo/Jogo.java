package br.com.softal.loteca.model.jogo;

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
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.Jogousuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcjogo")
public class Jogo extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nuSeqjogo;

	@Column(name = "cdjogo")
	private Long cdJogo;
	
	@Column(name = "dejogo")
	private String deJogo;

	@Column(name = "tpResultadofinal")
	private Long tpResultadofinal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cddata", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Data data;

	@OneToMany(mappedBy="jogo", fetch=FetchType.LAZY)
	private List<Jogousuario> jogousuarios;

	public Jogo() {
		super();
	}

	public Jogo(long nuSeqjogo) {
		this();
	}

	public Jogo(long nuSeqjogo, Long cdJogo, String deJogo, Long tpResultadofinal,
			long cdData) {
		super();
		this.inicializaRelacionamentos();
		this.nuSeqjogo = nuSeqjogo;
		this.cdJogo = cdJogo;
		this.deJogo = deJogo;
		this.tpResultadofinal = tpResultadofinal;
		this.getData().setCdData(cdData);
	}
	
	public Long getCdJogo() {
		return cdJogo;
	}

	public void setCdJogo(Long cdJogo) {
		this.cdJogo = cdJogo;
	}

	public long getNuSeqjogo() {
		return nuSeqjogo;
	}

	public void setNuSeqjogo(long nuSeqjogo) {
		this.nuSeqjogo = nuSeqjogo;
	}

	public String getDeJogo() {
		return deJogo;
	}

	public void setDeJogo(String deJogo) {
		this.deJogo = deJogo;
	}


	public Long getTpResultadofinal() {
		return tpResultadofinal;
	}

	public void setTpResultadofinal(Long tpResultadofinal) {
		this.tpResultadofinal = tpResultadofinal;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	

	public List<Jogousuario> getJogousuarios() {
		return jogousuarios;
	}

	public void setJogousuarios(List<Jogousuario> jogousuarios) {
		this.jogousuarios = jogousuarios;
	}

	@Override
	public void inicializaRelacionamentos() {
		setData(new Data());
		setJogousuarios(new ArrayList<Jogousuario>());

	}

}