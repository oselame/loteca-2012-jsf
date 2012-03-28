package br.com.softal.base.model.usuariogrupo;

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
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.loteca.model.grupo.Grupo;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "esegusuariogrupo")
public class Usuariogrupo extends Entity {

	@Id
	@Column(name = "nuSequsuariogrupo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nuSequsuariogrupo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdgrupo", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private Grupo grupo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdusuario", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private Usuario usuario;

	public Usuariogrupo() {
		super();
	}
	
	public Usuariogrupo(Long nuSequsuariogrupo, Long cdGrupo, Long cdUsuario) {
		this();
		this.inicializaRelacionamentos();
		this.nuSequsuariogrupo = nuSequsuariogrupo;
		getGrupo().setCdGrupo(cdGrupo);
		getUsuario().setCdUsuario(cdUsuario);
	}



	public Long getNuSequsuariogrupo() {
		return nuSequsuariogrupo;
	}

	public void setNuSequsuariogrupo(Long nuSequsuariogrupo) {
		this.nuSequsuariogrupo = nuSequsuariogrupo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void inicializaRelacionamentos() {
		setGrupo(new Grupo());
		setUsuario(new Usuario());	
	}
}
