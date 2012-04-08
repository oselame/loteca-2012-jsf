package br.com.softal.loteca;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.service.LotecaService;

@SuppressWarnings({ "serial", "rawtypes" })
@ManagedBean(name="sistemaBean")
@SessionScoped
public class SistemaBean extends AbstractManegedBean {
	
	private String nomesistema;
	
	public SistemaBean() {}
	
	public String getNomesistema() {
		if (nomesistema == null) {
			try {
				nomesistema = super.getLotecaService().findLotecaAtiva().getDeLoteca();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return nomesistema;
	}

	public void setNomesistema(String nomesistema) {
		this.nomesistema = nomesistema;
	}

	@Override
	protected void initializeEntity() {}
	
}
