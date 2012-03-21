package br.com.softal.loteca.model.usuariogrupo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.grupo.Grupo;
import br.com.softal.loteca.model.usuario.Usuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "esegusuariogrupo")
public class Usuariogrupo extends Entity {

	@Id
	@Column(name = "nuSequsuariogrupo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nuSequsuariogrupo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cdgrupo", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Grupo grupo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cdusuario", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Usuario usuario;

	public Usuariogrupo() {
		super();
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

	
}
