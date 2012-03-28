package br.com.softal.base.model.projeto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.softal.base.model.Entity;
import br.com.softal.base.model.grupoaction.Grupoaction;
import br.com.softal.base.model.usuario.Usuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcprojeto")
public class Projeto extends Entity {

	@Id
	@Column(name = "cdprojeto")
	@OneToMany(mappedBy = "projeto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cdProjeto;

	@Column(name = "nmProjeto")
	private String nmProjeto;

	@Column(name = "sgProjeto")
	private String sgProjeto;
	
	@OneToMany(mappedBy="projeto", fetch=FetchType.LAZY)
	//@Cascade(CascadeType.ALL)
	private List<Usuario> usuarios;

	public Projeto() {
		super();
	}
	
	public Projeto(long cdProjeto, String nmProjeto, String sgProjeto) {
		this();
		this.cdProjeto = cdProjeto;
		this.nmProjeto = nmProjeto;
		this.sgProjeto = sgProjeto;
	}

	public long getCdProjeto() {
		return cdProjeto;
	}

	public void setCdProjeto(long cdProjeto) {
		this.cdProjeto = cdProjeto;
	}

	public String getNmProjeto() {
		return nmProjeto;
	}

	public void setNmProjeto(String nmProjeto) {
		this.nmProjeto = nmProjeto;
	}

	public String getSgProjeto() {
		return sgProjeto;
	}

	public void setSgProjeto(String sgProjeto) {
		this.sgProjeto = sgProjeto;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public void inicializaRelacionamentos() {
	}
	
}
