package br.com.softal.loteca.model.grupo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eseggrupo")
public class Grupo extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cdgrupo")
	@OneToMany(mappedBy = "grupo")
	private long cdGrupo;

	@Column(name = "degrupo")
	private String deGrupo;

	@Column(name = "nmgrupo")
	private String nmGrupo;
	
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

}
