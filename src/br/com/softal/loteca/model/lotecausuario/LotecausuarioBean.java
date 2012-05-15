package br.com.softal.loteca.model.lotecausuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataIntegrityViolationException;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.loteca.Loteca;

@SuppressWarnings("serial")
@ManagedBean(name="lotecausuarioBean")
@SessionScoped
public class LotecausuarioBean extends AbstractManegedBean<Lotecausuario> implements Serializable {
	
	private Long cdLoteca;
	private List<SelectItem> lotecas;
	private List<SelectItem> usuarios;
	
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
	
	@SuppressWarnings("unchecked")
	private void carregaLotecas() {
		List<Loteca> listaLotecas = (List<Loteca>) LtcServiceLocator.getInstance().getLotecaService().findAll(new Loteca());
		setLotecas(new ArrayList<SelectItem>());
		for (Loteca p : listaLotecas) {
			getLotecas().add(new SelectItem(p.getCdLoteca(), p.getNuAno().toString()));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregaUsuarios() {
		List<Usuario> listaUsuarios =  (List<Usuario>) LtcServiceLocator.getInstance().getLotecaService().findAll(new Usuario());
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
		getEntity().setClubeusuarios(new ArrayList<Clubeusuario>());
	}
	
	@PostConstruct
	public void init() {
		setUsuarios(new ArrayList<SelectItem>());
		setLotecas(new ArrayList<SelectItem>());
		this.carregaLotecas();
		this.carregaUsuarios();
	}
	
	@SuppressWarnings("unchecked")
	private void insereClubesUsuario() {
		Clube clube = new Clube();
		clube.setLoteca( getEntity().getLoteca() );
		List<Clube> clubes = (List<Clube>) LtcServiceLocator.getInstance().getLotecaService().findAll(clube);
		for (Clube c : clubes) {
			Clubeusuario cu = new Clubeusuario();
			cu.setClube( c );
			cu.setLotecausuario( getEntity() );
			cu.getLotecausuario().setLoteca( c.getLoteca() );
			cu.setNuPontos(0l);
			cu.setNuPosicao( c.getCdClube() );
			LtcServiceLocator.getInstance().getLotecaService().save(cu);
		}
	}
	
	private void carregaClubesUsuario() {
		try {
			Clubeusuario cu = new Clubeusuario();
			cu.setLotecausuario(getEntity());
			List<Clubeusuario> lista = (List<Clubeusuario>) LtcServiceLocator.getInstance().getLotecaService().findAllClubeusuario( cu );
			if (lista == null || lista.size() == 0) {
				this.insereClubesUsuario();
				this.carregaClubesUsuario();
			} else {
				getEntity().setClubeusuarios( lista );
			}
			Collections.sort(getEntity().getClubeusuarios(), new Comparator<Clubeusuario>() {
				@Override
				public int compare(Clubeusuario o1, Clubeusuario o2) {
					return o1.getNuPosicao().compareTo(o2.getNuPosicao());
				}
			});
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void atualizaClubesUsuario() {
		List<Clubeusuario> lista = getEntity().getClubeusuarios();
		long nuPosicao = 1l;
		for (Clubeusuario cu : lista) {
			cu.setLotecausuario(getEntity());
			cu.setNuPosicao(nuPosicao);
			if (nuPosicao <= 16) {
				cu.setFlRebaixado(0L);
			} else {
				cu.setFlRebaixado(1l);
			}
			LtcServiceLocator.getInstance().getLotecaService().update(cu);
			nuPosicao++;
		}
	}
	
	@Override
	public void save() {
		try {
			if (getEntity().isStatusInsert()) {
				super.save();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			} else if (getEntity().isStatusUpdate()) {
				super.update();
				this.atualizaClubesUsuario();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			}
			getEntity().setStatusUpdate();
			this.carregaClubesUsuario();
		} catch (DataIntegrityViolationException e) {
			super.getMessages().addWarningMessage("warning_registro_duplicado");
			e.printStackTrace();
		} catch (Exception e) {
			super.getMessages().addWarningMessage("warning_registro_duplicado");
			e.printStackTrace();
		} finally {
			init();
		}
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
			if ((Long)event.getNewValue() != null) {
				long cdLoteca = (Long) event.getNewValue();
				List<Lotecausuario> lista = LtcServiceLocator.getInstance().getLotecaService().findAllLotecausuarioByLoteca(cdLoteca);
				
				Collections.sort(lista, new Comparator<Lotecausuario>() {
					@Override
					public int compare(Lotecausuario o1, Lotecausuario o2) {
						return o1.getUsuario().getNmUsuario().compareTo(o2.getUsuario().getNmUsuario());
					}
				});
				
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
