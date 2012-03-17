package br.com.softal.loteca.model.jogousuario;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcjogousuario")
public class Jogousuario extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "nuseqjogousuario")
	@OneToMany(mappedBy = "jogousuario")
	private long nuSeqjogousuario;

	@Column(name = "nuseqlotecausuario")
	private Long nuSeqlotecausuario;

	@Column(name = "nuseqJogos")
	private Long nuSeqJogos;
	
	@Column(name = "tpjogo")
	private Long tpJogo;
	
	@Column(name = "flcoluna1")
	private Long flColuna1;
	
	@Column(name = "flempate")
	private Long flEmpate;
	
	@Column(name = "flcoluna2")
	private Long flColuna2;
	
	public Jogousuario() {
		super();
	}

	public long getNuSeqjogousuario() {
		return nuSeqjogousuario;
	}

	public void setNuSeqjogousuario(long nuSeqjogousuario) {
		this.nuSeqjogousuario = nuSeqjogousuario;
	}

	public Long getNuSeqlotecausuario() {
		return nuSeqlotecausuario;
	}

	public void setNuSeqlotecausuario(Long nuSeqlotecausuario) {
		this.nuSeqlotecausuario = nuSeqlotecausuario;
	}

	public Long getNuSeqJogos() {
		return nuSeqJogos;
	}

	public void setNuSeqJogos(Long nuSeqJogos) {
		this.nuSeqJogos = nuSeqJogos;
	}

	public Long getTpJogo() {
		return tpJogo;
	}

	public void setTpJogo(Long tpJogo) {
		this.tpJogo = tpJogo;
	}

	public Long getFlColuna1() {
		return flColuna1;
	}

	public void setFlColuna1(Long flColuna1) {
		this.flColuna1 = flColuna1;
	}

	public Long getFlEmpate() {
		return flEmpate;
	}

	public void setFlEmpate(Long flEmpate) {
		this.flEmpate = flEmpate;
	}

	public Long getFlColuna2() {
		return flColuna2;
	}

	public void setFlColuna2(Long flColuna2) {
		this.flColuna2 = flColuna2;
	}
	
}
