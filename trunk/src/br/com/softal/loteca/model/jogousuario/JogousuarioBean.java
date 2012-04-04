package br.com.softal.loteca.model.jogousuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.LtcServiceLocator;

@SuppressWarnings("serial")
@ManagedBean(name="jogousuarioBean")
@SessionScoped
public class JogousuarioBean extends AbstractManegedBean<Jogousuario> implements Serializable {
	
	private Long cdLoteca;
	private List<Jogousuario> jogousuarios; 
	
	public Long getCdLoteca() {
		return cdLoteca;
	}

	public void setCdLoteca(Long cdLoteca) {
		this.cdLoteca = cdLoteca;
	}
	
	public List<Jogousuario> getJogousuarios() {
		return jogousuarios;
	}

	public void setJogousuarios(List<Jogousuario> jogousuarios) {
		this.jogousuarios = jogousuarios;
	}

	public JogousuarioBean() {
		
	}
	
	/*****************************************************************************************************/
	
	@Override
	protected void initializeEntity() {
		setEntity(new Jogousuario());
		setJogousuarios(new ArrayList<Jogousuario>());
	}
	
	public String abrirCadJogousuario() {
		getJogousuarios().add(new Jogousuario());
		this.carregaJogoUsuario();
		return FacesContext.getCurrentInstance().getExternalContext() + "/pages/user/jogousuario/eltcCadJogousuario.xhtml";
	}
	
	private void carregaJogoUsuario() {
		try {
			List<Jogousuario> jogousuarios = LtcServiceLocator.getInstance().getLotecaService().findAllJogoUsuarioDataEmAndamento(getEntity());
			setJogousuarios(jogousuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
