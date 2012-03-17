package br.com.softal.loteca.model.loteca;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.sets.SpringFactory;

@SuppressWarnings("serial")
@ManagedBean(name="lotecaBean")
@SessionScoped
public class LotecaBean extends AbstractManegedBean<Loteca> implements Serializable {
	
	private LotecaService lotecaService;

	public LotecaBean() {
		super();
		lotecaService = (LotecaServiceImpl) SpringFactory.getInstance().getBean("lotecaService"); 
	}
	
	public String getNomesistema() {
		try {
			return "Loteca " + getLotecaService().findLotecaAtiva().getNuAno();
		} catch (Exception e) {
			return "Registro da Loteca nao definido.";
		}
	}
	
	@Override
	protected void initializeEntity() {
		setEntity(new Loteca());
	}
	
	public void setLotecaService(LotecaServiceImpl lotecaService) {
		this.lotecaService = lotecaService;
	}
	
	private LotecaService getLotecaService() {
		return lotecaService;
	}
	
	public Loteca getLotecaativa() {
		return getLotecaService().findLotecaAtiva();
	}
	
	@PostConstruct
	public void init() {
		super.findAll();
	}
	
}
