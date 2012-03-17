package br.com.softal.loteca.model.grupo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;

@SuppressWarnings("serial")
@ManagedBean(name="grupoBean")
@SessionScoped
public class GrupoBean extends AbstractManegedBean<Grupo> {
	

	@Override
	protected void initializeEntity() {
		setEntity(new Grupo());
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
	
	public String adicionar() {
		initializeEntity();
		getEntity().setStatusInsert();
		return "eltcCadGrupo";
	}
	
	
	public String editar() {
		getEntity().setStatusUpdate();
		return "eltcCadGrupo";
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
		return "eltcCadGrupo";
	}
	

}
