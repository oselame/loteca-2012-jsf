package br.com.softal.loteca.model.jogo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.data.Data;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcjogo")
public class Jogo extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long nuSeqjogo;

	@Column(name = "dejogo")
	private String deJogo;
	
	@Column(name = "tpResultadofinal")
	private long tpResultadofinal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cddata", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	private Data data;
	
	public Jogo() {
		super();
	}
	
	public Jogo(long nuSeqjogo) {
		this();
	}

	public Jogo(long nuSeqjogo, String deJogo, long tpResultadofinal, long cdData) {
		super();
		this.nuSeqjogo = nuSeqjogo;
		this.deJogo = deJogo;
		this.tpResultadofinal = tpResultadofinal;
		this.setData(new Data());
		this.getData().setCdData(cdData);
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

	public long getTpResultadofinal() {
		return tpResultadofinal;
	}

	public void setTpResultadofinal(long tpResultadofinal) {
		this.tpResultadofinal = tpResultadofinal;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	

}