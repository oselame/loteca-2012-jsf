package br.com.softal.loteca.model.loteca;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.service.LotecaService;

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
	
	protected LotecaService getLotecaService() {
		return LtcServiceLocator.getInstance().getLotecaService();
	}
	
	public Loteca getLotecaativa() {
		try {
			return getLotecaService().findLotecaAtiva();
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostConstruct
	public void init() {
		super.findAll();
	}
	
}
