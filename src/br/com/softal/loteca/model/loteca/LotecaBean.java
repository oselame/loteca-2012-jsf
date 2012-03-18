package br.com.softal.loteca.model.loteca;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.LtcServiceLocator;

@SuppressWarnings("serial")
@ManagedBean(name="lotecaBean")
@SessionScoped
public class LotecaBean extends AbstractManegedBean<Loteca> implements Serializable {

	public LotecaBean() {
		super();
	}
	
	@Override
	protected void initializeEntity() {
		setEntity(new Loteca());
	}
	
	
	private LotecaService getLotecaService() {
		return LtcServiceLocator.getInstance().getLotecaService();
	}
	
	public Loteca getLotecaativa() {
		return getLotecaService().findLotecaAtiva();
	}
	
	@PostConstruct
	public void init() {
		super.findAll();
	}
	
}
