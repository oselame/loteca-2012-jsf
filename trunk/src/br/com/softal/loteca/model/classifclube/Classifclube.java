package br.com.softal.loteca.model.classifclube;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcclassiftime")
public class Classifclube extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuseqcassifclube")
	@OneToMany(mappedBy = "classifclube")
	private long nuSeqcassifclube;

	@Column(name = "cdData")
	private Long cdData;

	@Column(name = "nuseqclube")
	private Long nuSeqclube;
	
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


	public Classifclube() {
		super();
	}

	public long getNuSeqcassifclube() {
		return nuSeqcassifclube;
	}

	public void setNuSeqcassifclube(long nuSeqcassifclube) {
		this.nuSeqcassifclube = nuSeqcassifclube;
	}

	public Long getCdData() {
		return cdData;
	}

	public void setCdData(Long cdData) {
		this.cdData = cdData;
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

	public Long getNuSeqclube() {
		return nuSeqclube;
	}

	public void setNuSeqclube(Long nuSeqclube) {
		this.nuSeqclube = nuSeqclube;
	}

}