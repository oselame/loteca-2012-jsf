package br.com.softal.base.model.grupoaction;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.model.action.Action;
import br.com.softal.base.model.action.ActionBean;
import br.com.softal.base.model.grupo.Grupo;
import br.com.softal.base.model.grupo.GrupoBean;

@SuppressWarnings("serial")
@ManagedBean(name = "grupoactionBean")
@SessionScoped
public class GrupoactionBean extends AbstractManegedBean<Grupoaction> {

	private Long cdGrupo;
	private Long cdAction;
	private List<SelectItem> grupos;
	private List<SelectItem> actions;

	@ManagedProperty(value = "#{grupoBean}")
	private GrupoBean grupoBean;

	@ManagedProperty(value = "#{actionBean}")
	private ActionBean actionBean;

	@Override
	protected void initializeEntity() {
		setEntity(new Grupoaction());
		setGrupos(new ArrayList<SelectItem>());
		setActions(new ArrayList<SelectItem>());
	}

	private void carregaCombos() {
		getGrupoBean().init();
		getActionBean().init();
		List<Grupo> listaGrupos = getGrupoBean().getRows();
		List<Action> listaActions = getActionBean().getRows();
		
		getGrupos().clear();
		for (Grupo gr : listaGrupos) {
			getGrupos().add(new SelectItem(gr.getCdGrupo(), gr.getDeGrupo()));
		}
		
		getActions().clear();
		for (Action gr : listaActions) {
			getActions().add(new SelectItem(gr.getCdAction(), gr.getDeAction()));
		}
	}

	@PostConstruct
	public void init() {
		super.findAll();
		this.carregaCombos();
	}

	public Long getCdGrupo() {
		return cdGrupo;
	}

	public void setCdGrupo(Long cdGrupo) {
		this.cdGrupo = cdGrupo;
	}

	public Long getCdAction() {
		return cdAction;
	}

	public void setCdAction(Long cdAction) {
		this.cdAction = cdAction;
	}

	public List<SelectItem> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<SelectItem> grupos) {
		this.grupos = grupos;
	}

	public List<SelectItem> getActions() {
		return actions;
	}

	public void setActions(List<SelectItem> actions) {
		this.actions = actions;
	}

	public GrupoBean getGrupoBean() {
		return grupoBean;
	}

	public void setGrupoBean(GrupoBean grupoBean) {
		this.grupoBean = grupoBean;
	}

	public ActionBean getActionBean() {
		return actionBean;
	}

	public void setActionBean(ActionBean actionBean) {
		this.actionBean = actionBean;
	}
	
	@Override
	public void save() {
		try {
			//if (getEntity().isStatusInsert()) {
			getEntity().setGrupo(new Grupo());
			getEntity().setAction(new Action());
				getEntity().getGrupo().setCdGrupo(this.getCdGrupo());
				getEntity().getAction().setCdAction(this.getCdAction());
				super.save();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			/*} else if (getEntity().isStatusInsert()) {
				super.update();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			}*/
			getEntity().setStatusUpdate();
			initializeEntity();
			setCdGrupo(null);
			setCdAction(null);
		} catch (Exception e) {
			super.getMessages().addMessage(FacesMessage.SEVERITY_INFO, "warning_registro_duplicado");
			e.printStackTrace();
		} finally {
			init();
		}
	}
	
	public String adicionar() {
		initializeEntity();
		getEntity().setStatusInsert();
		return "eltcConGrupoaction";
	}
	
	
	public String editar() {
		getEntity().setStatusUpdate();
		return "eltcConGrupoaction";
	}
	
	public String excluir() {
		try {
			super.delete();
			super.getMessages().addSucessMessage("mensagem_registro_excluido_com_sucesso");
			return adicionar();
		} catch (Exception e) {
			super.getMessages().addSucessMessage("mensagem_ocorreu_um_erro_ao_excluir_o_registro");
		} finally {
			init();
		}
		return "eltcConGrupoaction";
	}

}
