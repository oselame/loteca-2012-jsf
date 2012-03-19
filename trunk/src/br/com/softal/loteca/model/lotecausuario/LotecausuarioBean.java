package br.com.softal.loteca.model.lotecausuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataIntegrityViolationException;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.loteca.LotecaBean;
import br.com.softal.loteca.model.usuario.Usuario;
import br.com.softal.loteca.model.usuario.UsuarioBean;

@SuppressWarnings("serial")
@ManagedBean(name="lotecausuarioBean")
@SessionScoped
public class LotecausuarioBean extends AbstractManegedBean<Lotecausuario> implements Serializable {
	
	private Long cdLoteca;
	private List<SelectItem> lotecas;
	private List<SelectItem> usuarios;
	
	@ManagedProperty(value = "#{lotecaBean}")
	private LotecaBean lotecaBean;
	
	@ManagedProperty(value = "#{usuarioBean}")
	private UsuarioBean usuarioBean;
	
	
	public LotecausuarioBean() {
	}
	
	public Long getCdLoteca() {
		return cdLoteca;
	}

	public void setCdLoteca(Long cdLoteca) {
		this.cdLoteca = cdLoteca;
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
	}
	
	private void insereClubesUsuario(Long cdUsuario, Long cdLoteca) {
		if (getEntity().getClubeusuarios() != null  && getEntity().getClubeusuarios().size() == 0) {
			Clube clube = new Clube();
			clube.getLoteca().setCdLoteca(cdLoteca);
			List<Clube> clubes = (List<Clube>) LtcServiceLocator.getInstance().getLotecaService().findAll(clube);
			for (Clube c : clubes) {
				Clubeusuario cu = new Clubeusuario();
				cu.setNuPontos(0l);
				cu.setNuPosicao(c.getCdClube());
				cu.setClube(c);
				cu.setLotecausuario(getEntity());
				cu.setStatusInsert();
				LtcServiceLocator.getInstance().getLotecaService().save(cu);
				getEntity().getClubeusuarios().add(cu);
			}
		}
	}
	
	private void carregaClubesUsuario() {
		Clubeusuario cu = new Clubeusuario();
	}
	
	@Override
	public void save() {
		try {
			if (getEntity().isStatusInsert()) {
				super.save();
				this.insereClubesUsuario(getEntity().getUsuario().getCdUsuario(), getEntity().getLoteca().getCdLoteca());
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			} else if (getEntity().isStatusUpdate()) {
				super.update();
				this.insereClubesUsuario(getEntity().getUsuario().getCdUsuario(), getEntity().getLoteca().getCdLoteca());
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			}
			getEntity().setStatusUpdate();
		} catch (DataIntegrityViolationException e) {
			super.getMessages().addWarningMessage("warning_registro_duplicado");
		} catch (Exception e) {
			super.getMessages().addWarningMessage("warning_registro_duplicado");
			e.printStackTrace();
		}
		init();
	}	
	
	public String abrirConLotecausuario() {
		return "eltcConLotecausuario";
	}
	
	public String adicionar() {
		initializeEntity();
		getEntity().setStatusInsert();
		return "eltcCadLotecausuario";
	}

	public String editar() {
		getEntity().setStatusUpdate();
		this.carregaClubesUsuario();
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
	
	public void carregaUsuariosloteca(ValueChangeEvent event) {
		try {
			System.out.println( event.getNewValue() );
			if ((Long)event.getNewValue() != null) {
				long cdLoteca = (Long) event.getNewValue();
				List<Lotecausuario> lista = LtcServiceLocator.getInstance().getLotecaService().findAllLotecausuarioByLoteca(cdLoteca);
				setRows(lista);
				setCdLoteca(cdLoteca);
			} else {
				setCdLoteca(null);
				setRows(new ArrayList<Lotecausuario>());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
