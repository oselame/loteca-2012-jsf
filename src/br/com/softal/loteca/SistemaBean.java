package br.com.softal.loteca;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.util.Constantes;

@SuppressWarnings({ "serial", "rawtypes" })
@ManagedBean(name="sistemaBean")
@SessionScoped
public class SistemaBean extends AbstractManegedBean {
	
	private String nomesistema;
	private String complemento;
	private Boolean exibirLogin;
	private Boolean exibirDashboard;
	private Loteca lotecaativa;
	
	public SistemaBean()  {
		setExibirLogin(false);
		setExibirDashboard(false);
		try {
			lotecaativa = super.getLotecaativa();
			setNomesistema("Loteca");
			if (lotecaativa != null) {
				nomesistema = lotecaativa.getDeLoteca();
				if (lotecaativa.getTpSituacao() == Constantes.lOTECA_SITUACAO_CADASTRAMENTO) {
					//setComplemento("( em cadastro )");
				} else if (lotecaativa.getTpSituacao() == Constantes.lOTECA_SITUACAO_ANDAMENTO) {
					setExibirLogin( true );
				}
				setExibirDashboard(true);
			} else {
				//getMessages().addWarningMessage("msg_warning_nao_exite_campeonato_vigente");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		//FIXME: TEMPORARIAMENTE
		setExibirLogin( true );
	}
	
	public String getNomesistema() {
		return nomesistema;
	}

	public void setNomesistema(String nomesistema) {
		this.nomesistema = nomesistema;
	}
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public Boolean getExibirLogin() {
		if (exibirLogin == null) {
			return false;
		}
		return exibirLogin;
	}
	
	public Boolean getExibirDashboard() {
		return exibirDashboard;
	}

	public void setExibirDashboard(Boolean exibirDashboard) {
		this.exibirDashboard = exibirDashboard;
	}

	public void setExibirLogin(Boolean exibirLogin) {
		this.exibirLogin = exibirLogin;
	}
	
	public Loteca getLotecaativa() {
		return lotecaativa;
	}

	public void setLotecaativa(Loteca lotecaativa) {
		this.lotecaativa = lotecaativa;
	}

	@Override
	protected void initializeEntity() {}
	
}
