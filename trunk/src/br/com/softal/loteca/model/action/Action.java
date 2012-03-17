package br.com.softal.loteca.model.action;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.softal.base.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "esegaction")
public class Action extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cdaction")
	@OneToMany(mappedBy = "action")
	private long cdAction;

	@Column(name = "nmAction")
	private String nmAction;

	@Column(name = "deAction")
	private String deAction;

	public Action() {
		super();
	}

	public Action(long cdAction, String nmAction) {
		this();
		this.cdAction = cdAction;
		this.nmAction = nmAction;
	}

	public long getCdAction() {
		return cdAction;
	}

	public void setCdAction(long cdAction) {
		this.cdAction = cdAction;
	}

	public String getNmAction() {
		return nmAction;
	}

	public void setNmAction(String nmAction) {
		this.nmAction = nmAction;
	}

	public String getDeAction() {
		return deAction;
	}

	public void setDeAction(String deAction) {
		this.deAction = deAction;
	}

}
