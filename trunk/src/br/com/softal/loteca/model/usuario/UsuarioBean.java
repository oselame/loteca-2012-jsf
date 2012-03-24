package br.com.softal.loteca.model.usuario;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.model.loteca.LotecaDataModel;
import br.com.softal.loteca.model.projeto.Projeto;
import br.com.softal.loteca.model.projeto.ProjetoBean;

@SuppressWarnings("serial")
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean extends AbstractManegedBean<Usuario> {
	
	private String deSenhaaux;
	private List<SelectItem> projetos;
	
	private LotecaDataModel lotecaDataModel;
	
	@ManagedProperty(value = "#{projetoBean}")
	private ProjetoBean projetoBean;
	
	
	public ProjetoBean getProjetoBean() {
		return projetoBean;
	}

	public void setProjetoBean(ProjetoBean projetoBean) {
		this.projetoBean = projetoBean;
	}

	public List<SelectItem> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<SelectItem> projetos) {
		this.projetos = projetos;
	}

	public boolean isBlAdm() {
		try {
			return getEntity().getFlAdm() == 1l;
		} catch (Exception e) {
			return false;
		}
	}

	public void setBlAdm(boolean blAdm) {
		getEntity().setFlAdm(blAdm ? 1l : 0l);
	}

	public boolean isBlAtivo() {
		try {
			return getEntity().getFlAtivo() == 1l;
		} catch (Exception e) {
			return false;
		}
	}

	public void setBlAtivo(boolean blAtivo) {
		getEntity().setFlAtivo(blAtivo ? 1l : 0l);
	}

	public boolean isBlForaempresa() {
		try {
			return getEntity().getFlForaempresa() == 1l;
		} catch (Exception e) {
			return false;
		}
	}

	public void setBlForaempresa(boolean blForaempresa) {
		getEntity().setFlForaempresa(blForaempresa ? 1l : 0l);
	}

	public boolean isBlEnviosenha() {
		try {
			return getEntity().getFlEnviosenha() == 1l;
		} catch (Exception e) {
			return false;
		}
	}

	public void setBlEnviosenha(boolean blEnviosenha) {
		getEntity().setFlEnviosenha(blEnviosenha ? 1l : 0l);
	}
	
	@Override
	protected void initializeEntity() {
		setEntity(new Usuario());
		getEntity().setProjeto(new Projeto());
	}

	@PostConstruct
	public void init() {
		super.findAll();
		this.carregaProjetos();
	}
	
	public void geraDeLoginDoEmail() {
		int pos = getEntity().getDeEmail().indexOf("@");
		String deLogin = getEntity().getDeEmail().substring(0, pos);
		getEntity().setDeLogin(deLogin);
	}

	@Override
	public void save() {
		try {
			if (getEntity().isStatusInsert()) {
				this.geraDeLoginDoEmail();
				super.save();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			} else if (getEntity().isStatusUpdate()) {
				this.geraDeLoginDoEmail();
				super.update();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			}
			getEntity().setStatusUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
	}
	
	private void carregaProjetos() {
		List<Projeto> listaProjetos = getProjetoBean().getRows();
		setProjetos(new ArrayList<SelectItem>());
		for (Projeto p : listaProjetos) {
			getProjetos().add(new SelectItem(p.getCdProjeto(), p.getNmProjeto()));
		}
	}

	public String adicionar() {
		initializeEntity();
		getEntity().setStatusInsert();
		return "eltcCadUsuario";
	}

	public String editar() {
		getEntity().setStatusUpdate();
		return "eltcCadUsuario";
	}

	public String excluir() {
		try {
			super.delete();
			super.getMessages().addSucessMessage(
					"mensagem_registro_excluido_com_sucesso");
			return adicionar();
		} catch (Exception e) {
			super.getMessages().addSucessMessage(
					"mensagem_ocorreu_um_erro_ao_excluir_o_registro");
		} finally {
			init();
		}
		return "eltcCadUsuario";
	}

	public String getDeSenhaaux() {
		return deSenhaaux;
	}

	public void setDeSenhaaux(String deSenhaaux) {
		this.deSenhaaux = deSenhaaux;
	}

	public LotecaDataModel getLotecaDataModel() {
		return lotecaDataModel;
	}

	public void setLotecaDataModel(LotecaDataModel lotecaDataModel) {
		this.lotecaDataModel = lotecaDataModel;
	}

}
