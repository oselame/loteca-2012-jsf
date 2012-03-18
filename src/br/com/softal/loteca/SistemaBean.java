package br.com.softal.loteca;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.model.loteca.LotecaService;

@SuppressWarnings({ "serial", "rawtypes" })
@ManagedBean(name="sistemaBean")
@SessionScoped
public class SistemaBean extends AbstractManegedBean {
	
	private String nomesistema;
	
	public SistemaBean() {}
	
	public String getNomesistema() {
		if (nomesistema == null) {
			nomesistema = getLotecaService().findLotecaAtiva().getDeLoteca();
		}
		return nomesistema;
	}

	public void setNomesistema(String nomesistema) {
		this.nomesistema = nomesistema;
	}

	@Override
	protected void initializeEntity() {}
	
	private LotecaService getLotecaService() {
		return LtcServiceLocator.getInstance().getLotecaService();
	}
	
}
