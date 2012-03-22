package br.com.softal.loteca.model.lotecausuario;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.usuario.Usuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltclotecausuario")
public class Lotecausuario extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuseqlotecausuario")
	//@OneToMany(mappedBy = "lotecausuario")
	private long nuSeqlotecausuario;

	@Column(name = "flativo")
	private Long flAtivo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cdusuario", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cdloteca", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private Loteca loteca;
	
	@OneToMany(mappedBy="lotecausuario", fetch=FetchType.LAZY)
	//@Cascade(CascadeType.ALL)
	private List<Clubeusuario> clubeusuarios;

	public Lotecausuario() {
		super();
	}
	
	public long getNuSeqlotecausuario() {
		return nuSeqlotecausuario;
	}

	public void setNuSeqlotecausuario(long nuSeqlotecausuario) {
		this.nuSeqlotecausuario = nuSeqlotecausuario;
	}

	public Long getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(Long flAtivo) {
		this.flAtivo = flAtivo;
	}
	
	public Boolean getBlAtivo() {
		return flAtivo != null && flAtivo == 1l;
	}
	
	public void setBlAtivo(Boolean blAtivo) {
		this.flAtivo = (blAtivo == null) ? 0l : (blAtivo ?  1l : 0l);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Loteca getLoteca() {
		return loteca;
	}

	public void setLoteca(Loteca loteca) {
		this.loteca = loteca;
	}

	public List<Clubeusuario> getClubeusuarios() {
		return clubeusuarios;
	}

	public void setClubeusuarios(List<Clubeusuario> clubeusuarios) {
		this.clubeusuarios = clubeusuarios;
	}
	
}
