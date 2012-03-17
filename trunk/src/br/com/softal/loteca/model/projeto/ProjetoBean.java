package br.com.softal.loteca.model.projeto;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;

@SuppressWarnings("serial")
@ManagedBean(name="projetoBean")
@SessionScoped
public class ProjetoBean extends AbstractManegedBean<Projeto> {
	

	@Override
	protected void initializeEntity() {
		setEntity(new Projeto());
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
		return "eltcCadProjeto";
	}
	
	
	public String editar() {
		getEntity().setStatusUpdate();
		return "eltcCadProjeto";
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
		return "eltcCadProjeto";
	}
	

}
