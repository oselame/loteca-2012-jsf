package br.com.softal.loteca.model.lotecausuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.loteca.LotecaBean;
import br.com.softal.loteca.model.usuario.Usuario;
import br.com.softal.loteca.model.usuario.UsuarioBean;

@SuppressWarnings("serial")
@ManagedBean(name="lotecausuarioBean")
@SessionScoped
public class LotecausuarioBean extends AbstractManegedBean<Lotecausuario> implements Serializable {
	
	private List<SelectItem> lotecas;
	private List<SelectItem> usuarios;
	
	@ManagedProperty(value = "#{lotecaBean}")
	private LotecaBean lotecaBean;
	
	@ManagedProperty(value = "#{usuarioBean}")
	private UsuarioBean usuarioBean;
	
	public LotecausuarioBean() {
		
	}
	
	public List<SelectItem> getLotecas() {
		return lotecas;
	}

	public void setLotecas(List<SelectItem> lotecas) {
		this.lotecas = lotecas;
	}
	
	public List<SelectItem> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<SelectItem> usuarios) {
		this.usuarios = usuarios;
	}

	public LotecaBean getLotecaBean() {
		return lotecaBean;
	}

	public void setLotecaBean(LotecaBean lotecaBean) {
		this.lotecaBean = lotecaBean;
	}
	
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	private void carregaLotecas() {
		getLotecaBean().init();
		List<Loteca> listaLotecas = getLotecaBean().getRows();
		setLotecas(new ArrayList<SelectItem>());
		for (Loteca p : listaLotecas) {
			getLotecas().add(new SelectItem(p.getCdLoteca(), p.getNuAno().toString()));
		}
	}
	
	private void carregaUsuarios() {
		getUsuarioBean().init();
		List<Usuario> listaUsuarios = getUsuarioBean().getRows();
		setUsuarios(new ArrayList<SelectItem>());
		for (Usuario u : listaUsuarios) {
			getUsuarios().add(new SelectItem(u.getCdUsuario(), u.getNmUsuario()));
		}
	}
	
	@Override
	protected void initializeEntity() {
		setEntity(new Lotecausuario());
		getEntity().setLoteca(new Loteca());
		getEntity().setUsuario(new Usuario());
	}
	
	@PostConstruct
	public void init() {
		setUsuarios(new ArrayList<SelectItem>());
		setLotecas(new ArrayList<SelectItem>());
		this.carregaLotecas();
		this.carregaUsuarios();
		this.findAll();
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
		return "eltcCadLotecausuario";
	}

	public String editar() {
		getEntity().setStatusUpdate();
		return "eltcCadLotecausuario";
	}
	
	public String excluir() {
		try {
			super.delete();
			super.getMessages().addSucessMessage("mensagem_registro_excluido_com_sucesso");
		} catch (Exception e) {
			super.getMessages().addSucessMessage("mensagem_ocorreu_um_erro_ao_excluir_o_registro");
		} finally {
			init();
		}
		return "eltcCadLotecausuario";
	}
	
}
