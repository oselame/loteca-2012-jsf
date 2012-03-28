package br.com.softal.loteca.model.grupo;

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
import br.com.softal.loteca.model.clube.Clube;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eseggrupo")
public class Grupo extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cdgrupo")
	//@OneToMany(mappedBy = "grupo")
	private long cdGrupo;

	@Column(name = "degrupo")
	private String deGrupo;

	@Column(name = "nmgrupo")
	private String nmGrupo;
	
	@OneToMany(mappedBy="grupo", fetch=FetchType.LAZY)
	//@Cascade(CascadeType.ALL)
	private List<Grupoaction> grupoactions;
	
	public Grupo() {
		super();
	}

	public Grupo(long cdGrupo, String deGrupo, String nmGrupo) {
		this();
		this.cdGrupo = cdGrupo;
		this.deGrupo = deGrupo;
		this.nmGrupo = nmGrupo;
	}

	public long getCdGrupo() {
		return cdGrupo;
	}

	public void setCdGrupo(long cdGrupo) {
		this.cdGrupo = cdGrupo;
	}

	public String getDeGrupo() {
		return deGrupo;
	}

	public void setDeGrupo(String deGrupo) {
		this.deGrupo = deGrupo;
	}

	public String getNmGrupo() {
		return nmGrupo;
	}

	public void setNmGrupo(String nmGrupo) {
		this.nmGrupo = nmGrupo;
	}

	public List<Grupoaction> getGrupoactions() {
		return grupoactions;
	}

	public void setGrupoactions(List<Grupoaction> grupoactions) {
		this.grupoactions = grupoactions;
	}
	
	@Override
	public void inicializaRelacionamentos() {
	}
	
}
