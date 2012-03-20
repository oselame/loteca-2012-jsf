package br.com.softal.loteca.model.grupoaction;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.action.Action;
import br.com.softal.loteca.model.grupo.Grupo;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eseggrupoaction")
public class Grupoaction extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "nuseqgrupoaction")
	private long nuSeqgrupoaction;
	
	@ManyToOne(optional = false, targetEntity = Grupo.class)
	@JoinColumn(name = "cdGrupo", referencedColumnName = "cdGrupo")
	private Grupo grupo;
	
	@ManyToOne(optional = false, targetEntity = Action.class)
	@JoinColumn(name = "cdAction", referencedColumnName = "cdAction")	
	private Action action;

	public Grupoaction() {
		super();
	}

	public long getNuSeqgrupoaction() {
		return nuSeqgrupoaction;
	}

	public void setNuSeqgrupoaction(long nuSeqgrupoaction) {
		this.nuSeqgrupoaction = nuSeqgrupoaction;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}


}