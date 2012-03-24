package br.com.softal.loteca.model.clube;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.loteca.LotecaBean;

@SuppressWarnings("serial")
@ManagedBean(name="clubeBean")
@SessionScoped
public class ClubeBean extends AbstractManegedBean<Clube> implements Serializable {
	
	private Long cdLoteca;
	private List<SelectItem> lotecas;
	
	@ManagedProperty(value="#{lotecaBean}")
	private LotecaBean lotecaBean;
	
	public ClubeBean() {
		setLotecas(new ArrayList<SelectItem>());
	}
	
	public Long getCdLoteca() {
		return cdLoteca;
	}

	public void setCdLoteca(Long cdLoteca) {
		this.cdLoteca = cdLoteca;
	}

	private LotecaBean getLotecaBean() {
		return lotecaBean;
	}

	public void setLotecaBean(LotecaBean lotecaBean) {
		this.lotecaBean = lotecaBean;
	}

	@Override
	protected void initializeEntity() {
		setEntity(new Clube());
	}
	
	@PostConstruct
	public void init() {
		//--super.findAll();
		this.carregaLotecas();
	}
	
	@Override
	public void save() {
		try {
			if (getEntity().isStatusInsert()) {
				super.save();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			} else if (getEntity().isStatusUpdate()) {
				super.update();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			}
			getEntity().setStatusUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
	}
	
	public void carregaClubesloteca(ValueChangeEvent event) {
		try {
			if ((Long)event.getNewValue() != null) {
				long cdLoteca = (Long) event.getNewValue();
				List<Clube> lista = LtcServiceLocator.getInstance().getLotecaService().findAllClubeByLoteca(cdLoteca);
				setRows(lista);
				setCdLoteca(cdLoteca);
			} else {
				setCdLoteca(null);
				setRows(new ArrayList<Clube>());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void carregaLotecas() {
		getLotecaBean().init();
		List<Loteca> listaLotecas = getLotecaBean().getRows();
		setLotecas(new ArrayList<SelectItem>());
		for (Loteca p : listaLotecas) {
			getLotecas().add(new SelectItem(p.getCdLoteca(), p.getNuAno().toString()));
		}
	}
	
	private Loteca getLotecaAtiva() {
		return getLotecaBean().getLotecaativa();
	}
	
	public String adicionar() {
		initializeEntity();
		getEntity().setStatusInsert();
		getEntity().setLoteca( this.getLotecaAtiva() );
		return "eltcCadClube";
	}
	
	
	public String editar() {
		getEntity().setStatusUpdate();
		return "eltcCadClube";
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
		return "eltcCadClube";
	}
	
	public List<SelectItem> getLotecas() {
		return lotecas;
	}

	public void setLotecas(List<SelectItem> lotecas) {
		this.lotecas = lotecas;
	}

}
