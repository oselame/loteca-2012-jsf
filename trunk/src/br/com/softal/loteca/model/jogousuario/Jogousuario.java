package br.com.softal.loteca.model.jogousuario;

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
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcjogousuario")
public class Jogousuario extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "nuseqjogousuario")
	@OneToMany(mappedBy = "jogousuario")
	private long nuSeqjogousuario;

	@Column(name = "tpjogo")
	private Long tpJogo;
	
	@Column(name = "flcoluna1")
	private Long flColuna1;
	
	@Column(name = "flempate")
	private Long flEmpate;
	
	@Column(name = "flcoluna2")
	private Long flColuna2;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nuseqlotecausuario", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	private Lotecausuario lotecausuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nuseqjogos", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	private Jogo jogo;
	
	public Jogousuario() {
		super();
	}
	
	public void inicializaRelacionamentos() {
		setLotecausuario(new Lotecausuario());
		setJogo(new Jogo());
	}
	
	public Jogousuario(long nuSeqjogousuario, Long tpJogo, Long flColuna1,
			Long flEmpate, Long flColuna2, Long nuSeqlotecausuario, Long nuSeqjogo) {
		super();
		this.nuSeqjogousuario = nuSeqjogousuario;
		this.tpJogo = tpJogo;
		this.flColuna1 = flColuna1;
		this.flEmpate = flEmpate;
		this.flColuna2 = flColuna2;
		this.inicializaRelacionamentos();
		getLotecausuario().setNuSeqlotecausuario(nuSeqlotecausuario);
		getJogo().setNuSeqjogo(nuSeqjogo);
	}



	public long getNuSeqjogousuario() {
		return nuSeqjogousuario;
	}

	public void setNuSeqjogousuario(long nuSeqjogousuario) {
		this.nuSeqjogousuario = nuSeqjogousuario;
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

	public Lotecausuario getLotecausuario() {
		return lotecausuario;
	}

	public void setLotecausuario(Lotecausuario lotecausuario) {
		this.lotecausuario = lotecausuario;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
}
