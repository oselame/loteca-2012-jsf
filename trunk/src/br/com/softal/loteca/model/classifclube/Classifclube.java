package br.com.softal.loteca.model.classifclube;

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
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.data.Data;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcclassifclube")
public class Classifclube extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuSeqclassifclube")
	@OneToMany(mappedBy = "classifclube")
	private long nuSeqclassifclube;

	@Column(name = "nuclassificacao")
	private Long nuClassificacao;

	@Column(name = "nupontos")
	private Long nuPontos;

	@Column(name = "nujogos")
	private Long nuJogos;

	@Column(name = "nuvitorias")
	private Long nuVitorias;

	@Column(name = "nuempates")
	private Long nuEmpates;

	@Column(name = "nuderrotas")
	private Long nuDerrotas;

	@Column(name = "nugolspro")
	private Long nuGolspro;

	@Column(name = "nugolscontra")
	private Long nuGolscontra;

	@Column(name = "nusaldogols")
	private Long nuSaldogols;

	@Column(name = "nupercaprov")
	private Long nuPercaprov;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cddata", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	private Data data;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nuseqclube", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	private Clube clube;

	public Classifclube() {
		super();
	}
	
	public Classifclube(long nuSeqclassifclube, Long nuClassificacao,
			Long nuPontos, Long nuJogos, Long nuVitorias, Long nuEmpates,
			Long nuDerrotas, Long nuGolspro, Long nuGolscontra,
			Long nuSaldogols, Long nuPercaprov, Long cdData, Long nuSeqclube) {
		this();
		this.inicializaRelacionamentos();
		this.nuSeqclassifclube = nuSeqclassifclube;
		this.nuClassificacao = nuClassificacao;
		this.nuPontos = nuPontos;
		this.nuJogos = nuJogos;
		this.nuVitorias = nuVitorias;
		this.nuEmpates = nuEmpates;
		this.nuDerrotas = nuDerrotas;
		this.nuGolspro = nuGolspro;
		this.nuGolscontra = nuGolscontra;
		this.nuSaldogols = nuSaldogols;
		this.nuPercaprov = nuPercaprov;
		getData().setCdData(cdData);
		getClube().setNuSeqclube(nuSeqclube);
	}

	public long getNuSeqclassifclube() {
		return nuSeqclassifclube;
	}

	public void setNuSeqclassifclube(long nuSeqclassifclube) {
		this.nuSeqclassifclube = nuSeqclassifclube;
	}

	public Long getNuClassificacao() {
		return nuClassificacao;
	}

	public void setNuClassificacao(Long nuClassificacao) {
		this.nuClassificacao = nuClassificacao;
	}

	public Long getNuPontos() {
		return nuPontos;
	}

	public void setNuPontos(Long nuPontos) {
		this.nuPontos = nuPontos;
	}

	public Long getNuJogos() {
		return nuJogos;
	}

	public void setNuJogos(Long nuJogos) {
		this.nuJogos = nuJogos;
	}

	public Long getNuVitorias() {
		return nuVitorias;
	}

	public void setNuVitorias(Long nuVitorias) {
		this.nuVitorias = nuVitorias;
	}

	public Long getNuEmpates() {
		return nuEmpates;
	}

	public void setNuEmpates(Long nuEmpates) {
		this.nuEmpates = nuEmpates;
	}

	public Long getNuDerrotas() {
		return nuDerrotas;
	}

	public void setNuDerrotas(Long nuDerrotas) {
		this.nuDerrotas = nuDerrotas;
	}

	public Long getNuGolspro() {
		return nuGolspro;
	}

	public void setNuGolspro(Long nuGolspro) {
		this.nuGolspro = nuGolspro;
	}

	public Long getNuGolscontra() {
		return nuGolscontra;
	}

	public void setNuGolscontra(Long nuGolscontra) {
		this.nuGolscontra = nuGolscontra;
	}

	public Long getNuSaldogols() {
		return nuSaldogols;
	}

	public void setNuSaldogols(Long nuSaldogols) {
		this.nuSaldogols = nuSaldogols;
	}

	public Long getNuPercaprov() {
		return nuPercaprov;
	}

	public void setNuPercaprov(Long nuPercaprov) {
		this.nuPercaprov = nuPercaprov;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}

	@Override
	public void inicializaRelacionamentos() {
		setData(new Data());
		setClube(new Clube());
	}
	
}