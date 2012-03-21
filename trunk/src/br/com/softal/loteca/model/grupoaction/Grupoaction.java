package br.com.softal.loteca.model.grupoaction;

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
import br.com.softal.loteca.model.action.Action;
import br.com.softal.loteca.model.grupo.Grupo;
import br.com.softal.loteca.model.loteca.Loteca;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eseggrupoaction")
public class Grupoaction extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "nuseqgrupoaction")
	private long nuSeqgrupoaction;
	
/*	@ManyToOne(optional = false, targetEntity = Grupo.class)
	@JoinColumn(name = "cdGrupo", referencedColumnName = "cdGrupo")*/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cdgrupo", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Grupo grupo;
	
/*	@ManyToOne(optional = false, targetEntity = Action.class)
	@JoinColumn(name = "cdAction", referencedColumnName = "cdAction")	*/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cdaction", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)	
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
