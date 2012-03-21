package br.com.softal.loteca.model.loteca;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcloteca")
public class Loteca extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cdloteca")
	private long cdLoteca;

	@Column(name = "nuano")
	private Long nuAno;

	@Column(name = "flativa")
	private Long flAtiva;
	
	@OneToMany(mappedBy="loteca", fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Clube> clubes;
	
	@OneToMany(mappedBy="loteca", fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Lotecausuario> lotecasusuario;
	
	@Transient
	private String deLoteca;
	
	@Transient
	private Boolean selecionada;
	
	public Loteca() {
		super();
	}
	
	public Loteca(long cdLoteca, Long nuAno, Long flAtiva) {
		super();
		this.cdLoteca = cdLoteca;
		this.nuAno = nuAno;
		this.flAtiva = flAtiva;
	}

	public long getCdLoteca() {
		return cdLoteca;
	}

	public void setCdLoteca(long cdLoteca) {
		this.cdLoteca = cdLoteca;
	}

	public Long getNuAno() {
		return nuAno;
	}

	public void setNuAno(Long nuAno) {
		this.nuAno = nuAno;
	}

	public Long getFlAtiva() {
		return flAtiva;
	}

	public void setFlAtiva(Long flAtiva) {
		this.flAtiva = flAtiva;
	}

	public Boolean getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Boolean selecionada) {
		this.selecionada = selecionada;
	}

	public String getDeLoteca() {
		if (getNuAno() != null) {
			return "Loteca " + getNuAno();
		}
		return deLoteca;
	}

	public void setDeLoteca(String deLoteca) {
		this.deLoteca = deLoteca;
	}

	public List<Clube> getClubes() {
		return clubes;
	}

	public void setClubes(List<Clube> clubes) {
		this.clubes = clubes;
	}

	public List<Lotecausuario> getLotecasusuario() {
		return lotecasusuario;
	}

	public void setLotecasusuario(List<Lotecausuario> lotecasusuario) {
		this.lotecasusuario = lotecasusuario;
	}
	
}
