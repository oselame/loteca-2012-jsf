package br.com.softal.base.model.usuariogrupo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataIntegrityViolationException;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.model.grupo.Grupo;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@ManagedBean(name = "usuariogrupoBean")
@SessionScoped
public class UsuariogrupoBean extends AbstractManegedBean<Usuariogrupo> {
	
	private Long cdGrupo;
	private Long cdUsuario;
	private List<SelectItem> grupos;
	private List<SelectItem> usuarios;
	
	/*@ManagedProperty(value = "#{lotecaBean}")
	private LotecaBean lotecaBean;*/
	
	@Override
	protected void initializeEntity() {
		setEntity(new Usuariogrupo());
		getEntity().setGrupo(new Grupo());
		getEntity().setUsuario(new Usuario());
	}
	
	public UsuariogrupoBean() {
		super();
	}
	
	public Long getCdGrupo() {
		return cdGrupo;
	}

	public void setCdGrupo(Long cdGrupo) {
		this.cdGrupo = cdGrupo;
	}

	public List<SelectItem> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<SelectItem> grupos) {
		this.grupos = grupos;
	}
	
	public Long getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(Long cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public List<SelectItem> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<SelectItem> usuarios) {
		this.usuarios = usuarios;
	}

	/******************************************************************************************************************************/
	@PostConstruct
	public void init() {
		setGrupos(new ArrayList<SelectItem>());
		this.carregaGrupos();
		this.carregaUsuarios();
	}
	
	@SuppressWarnings("unchecked")
	private void carregaGrupos() {
		try {
			List<Grupo> listaGrupos = (List<Grupo>) LtcServiceLocator.getInstance().getLotecaService().findAll(new Grupo());
			setGrupos(new ArrayList<SelectItem>());
			for (Grupo g : listaGrupos) {
				getGrupos().add(new SelectItem(g.getCdGrupo(), g.getNmGrupo()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregaUsuarios() {
		try {
			List<Usuario> listaUsuarios = (List<Usuario>) LtcServiceLocator.getInstance().getLotecaService().findAll(new Usuario());
			setUsuarios(new ArrayList<SelectItem>());
			for (Usuario u : listaUsuarios) {
				getUsuarios().add(new SelectItem(u.getCdUsuario(), u.getNmUsuario()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void save() {
		try {
			initializeEntity();
			getEntity().getGrupo().setCdGrupo(getCdGrupo());
			getEntity().getUsuario().setCdUsuario(getCdUsuario());
			super.save();
			super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
		} catch (DataIntegrityViolationException e) {
			super.getMessages().addWarningMessage("warning_registro_duplicado");
			e.printStackTrace();
		} catch (Exception e) {
			super.getMessages().addFatalMessage(e.getMessage());
			e.printStackTrace();
		}
		this.carregaUsuariosGrupo(getCdGrupo());
		setCdUsuario(null);
	}
	
	private void carregaUsuariosGrupo(Long cdGrupo) {
		if (cdGrupo != null) {
			Usuariogrupo ug = new Usuariogrupo();
			ug.setGrupo(new Grupo());
			ug.getGrupo().setCdGrupo(cdGrupo);
			List<Usuariogrupo> lista = (List<Usuariogrupo>) LtcServiceLocator.getInstance().getLotecaService().findAll(ug);
			setRows(lista);
 		} else {
 			setRows(new ArrayList<Usuariogrupo>());
 		}
	}
	
	public void carregaUsuariosGrupo(ValueChangeEvent event) {
		try {
			if ((Long)event.getNewValue() != null) {
				long cdGrupo = (Long) event.getNewValue();
				setCdGrupo(cdGrupo);
			} else {
				setCdGrupo(null);
			}
			this.carregaUsuariosGrupo(this.getCdGrupo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String excluir() {
		try {
			super.delete();
			super.getMessages().addSucessMessage("mensagem_registro_excluido_com_sucesso");
		} catch (Exception e) {
			super.getMessages().addSucessMessage("mensagem_ocorreu_um_erro_ao_excluir_o_registro");
		} finally {
			carregaUsuariosGrupo(getCdGrupo());
		}
		return null;
	}
	
	
	
}
