package br.com.softal.base.model.grupoaction;

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
import br.com.softal.base.model.action.Action;
import br.com.softal.base.model.grupo.Grupo;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eseggrupoaction")
public class Grupoaction extends Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "nuseqgrupoaction")
	private long nuSeqgrupoaction;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cdgrupo", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private Grupo grupo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cdaction", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	//@Cascade(CascadeType.SAVE_UPDATE)	
	private Action action;

	public Grupoaction() {
		super();
	}
	
	public Grupoaction(long nuSeqgrupoaction, Long cdGrupo, Long cdAction) {
		this();
		this.nuSeqgrupoaction = nuSeqgrupoaction;
		this.inicializaRelacionamentos();
		getGrupo().setCdGrupo(cdGrupo);
		getAction().setCdAction(cdAction);
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
	
	@Override
	public void inicializaRelacionamentos() {
		setGrupo(new Grupo());
		setAction(new Action());		
	}


}
