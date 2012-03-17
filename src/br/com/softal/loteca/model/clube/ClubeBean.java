package br.com.softal.loteca.model.clube;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.loteca.LotecaBean;

@SuppressWarnings("serial")
@ManagedBean(name="clubeBean")
@SessionScoped
public class ClubeBean extends AbstractManegedBean<Clube> implements Serializable {
	
	@ManagedProperty(value="#{lotecaBean}")
	private LotecaBean lotecaBean;
	
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
		super.findAll();
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
	

}
