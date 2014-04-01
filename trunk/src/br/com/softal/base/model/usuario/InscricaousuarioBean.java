package br.com.softal.base.model.usuario;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.model.projeto.Projeto;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@ManagedBean(name = "inscricaousuarioBean")
@SessionScoped
public class InscricaousuarioBean extends AbstractManegedBean<Usuario> {
	
	private String deSenhaaux;
	private List<SelectItem> projetos;
	private boolean skip;
	private List<Clubeusuario> clubeusuarios;
	private Map<Long, Projeto> mapProjetos = new HashMap<Long, Projeto>();
	private Long cdProjeto;
	private boolean existeusuario;
	
	private DualListModel<String> clubes;
	
	public boolean isExisteusuario() {
		return existeusuario;
	}

	
	public DualListModel<String> getClubes() {
		return clubes;
	}

	public void setClubes(DualListModel<String> clubes) {
		this.clubes = clubes;
	}

	public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    } 
	
	public List<SelectItem> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<SelectItem> projetos) {
		this.projetos = projetos;
	}
	
	public List<Clubeusuario> getClubeusuarios() {
		return clubeusuarios;
	}

	public void setClubeusuarios(List<Clubeusuario> clubeusuarios) {
		this.clubeusuarios = clubeusuarios;
	}
	
	public Long getCdProjeto() {
		return cdProjeto;
	}

	public void setCdProjeto(Long cdProjeto) {
		this.cdProjeto = cdProjeto;
	}

	@Override
	protected void initializeEntity() {
		setDeSenhaaux(null);
		setCdProjeto(null);
		setEntity(new Usuario());
		getEntity().setProjeto(new Projeto());
	}

	@PostConstruct
	public void init() {
		this.carregaProjetos();
	}
	
	public String saveUsuarioWizard() {
		try {
			//FIXME: bug do primefaces no wizard
			getEntity().getProjeto().setCdProjeto( this.getCdProjeto() );
			
			super.getLotecaService().saveUsuarioWizard(getEntity(), getClubeusuarios());
			super.getMessages().addSucessMessage("msg_sucess_registro_usuario_realizado_com_sucesso_aguarde_inicio_campeonato");
			this.existeusuario = false;
			return "eltcDashboard.xhtml";
		} catch (Exception e) {
			super.getMessages().addWarningMessage("msg_warning_email_ja_cadastrado_na_base");
			e.printStackTrace();
		}
		return null;
	}
	
	public void projetoChange(ValueChangeEvent e) {
	}
	
	public void clubeChange(ValueChangeEvent e) {
		System.out.println(e.getSource().toString());
	}
	
	public void projetoChange2() {
		this.setCdProjeto( getEntity().getProjeto().getCdProjeto() );
		getEntity().setProjeto( mapProjetos.get( this.getCdProjeto() ) );
	}
	
	private void carregaProjetos() {
		try {
			List<Projeto> listaProjetos = (List<Projeto>) getLotecaService().findAll( new Projeto() );
			setProjetos(new ArrayList<SelectItem>());
			mapProjetos = new HashMap<Long, Projeto>();
			for (Projeto p : listaProjetos) {
				mapProjetos.put(p.getCdProjeto(), p);
				getProjetos().add(new SelectItem(p.getCdProjeto(), p.getNmProjeto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDeSenhaaux() {
		return deSenhaaux;
	}

	public void setDeSenhaaux(String deSenhaaux) {
		this.deSenhaaux = deSenhaaux;
	}
	
	public void montaLogin() {
		int pos = getEntity().getDeEmail().indexOf("@");
		String deLogin = getEntity().getDeEmail().substring(0, pos);
		getEntity().setDeLogin(deLogin);
	}

	public String onFlowProcess(FlowEvent event) {  
        if(skip) {  
            return "confirm";  
        } else {  
        	System.out.println( getEntity().getProjeto().getCdProjeto() );
        	if (!event.getOldStep().equalsIgnoreCase("confirm") && event.getNewStep().equalsIgnoreCase("confirm")) {
        		//montaLogin();
        	}
            return event.getNewStep();  
        }  
    }  
	
	public String onFlowProcessInscricaoUsuarioExistente(FlowEvent event) {  
		if(skip) {  
			return "confirm";  
		} else {  
			this.existeusuario = false;
			System.out.println("event.getOldStep() --> " + event.getOldStep());
			System.out.println("event.getNewStep() --> " + event.getNewStep());
			if (event.getOldStep().equalsIgnoreCase("dadosusuario") && event.getNewStep().equalsIgnoreCase("timeusuario")) {
				this.existeusuario = this.validaLogin();
				if (this.existeusuario) {
					return event.getNewStep();  
				} else {
					getMessages().addWarningMessage("msg_warning_usuario_senha_incorretos");
					return event.getOldStep();  
				}
			}
			return event.getNewStep();  
		}  
	}  
	
	public String existeUsuarioNaBase() {
		try {
			this.existeusuario = this.validaLogin();
			if (!this.existeusuario) {
				getMessages().addWarningMessage("msg_warning_usuario_senha_incorretos");
			} else {
				this.carregaClubesUsuarioExistente();
			}
			return "eltcCadUsuarioExistenteWizard.xhtml";
		} catch (Exception e) {
			super.getMessages().addWarningMessage("msg_warning_usuario_senha_incorretos");
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean validaLogin() {
		Usuario usuario = null;
		try {
			usuario = super.getLotecaService().findUsuarioByLonginSenha(this.getEntity());
			usuario.setStatusUpdate();
			this.setEntity(usuario);
			this.setCdProjeto( usuario.getProjeto().getCdProjeto() );
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return (usuario != null);
	}

	public String abrirCadInscricaoUsuario() {
		initializeEntity();
		getEntity().setStatusInsert();
		this.carregaClubesUsuario();
		getEntity().setDeLogin("Gerado a partir do e-mail");
		return "eltcCadUsuarioWizard.xhtml";
	}
	
	public String abrirCadInscricaoUsuarioJaExistente() {
		initializeEntity();
		getEntity().setStatusInsert();
		this.carregaClubesUsuario();
		return "eltcCadUsuarioExistenteWizard.xhtml";
	}
	
	private void carregaClubesUsuario() {
		List<String> clubesSource = new ArrayList<String>();  
        List<String> clubesTarget = new ArrayList<String>();
		try {
			setClubeusuarios(new ArrayList<Clubeusuario>());
			Clube clube = new Clube();
			clube.setLoteca( super.getLotecaativa() );
			List<Clube> clubes = (List<Clube>) this.getLotecaService().findAll(clube);
			for (Clube c : clubes) {
				Clubeusuario cu = new Clubeusuario();
				cu.setClube( c );
				cu.setLotecausuario(new Lotecausuario());
				cu.getLotecausuario().setLoteca( c.getLoteca() );
				cu.getLotecausuario().setNuSeqlotecausuario(99);
				cu.setNuPontos(0l);
				cu.setNuPosicao( c.getCdClube() );
				getClubeusuarios().add(cu);
				
				clubesSource.add(c.getNmClube());
			}
		} catch (Exception e) {
			getMessages().addWarningMessage("msg_warning_nao_exite_loteca_ativa");
		}
		
        clubes = new DualListModel<String>(clubesSource, clubesTarget);
	}
	
	private void carregaClubesUsuarioExistente() {
		List<String> clubesSource = new ArrayList<String>();  
		List<String> clubesTarget = new ArrayList<String>();
		try {
			Lotecausuario lotecausuario = new Lotecausuario();
			lotecausuario.setUsuario(this.getEntity());
			lotecausuario.setLoteca(this.getLotecaativa());
			lotecausuario = this.getLotecaService().findLotecausuario(lotecausuario);
			if (lotecausuario == null) {
				this.carregaClubesUsuario();
			} else {
				List<Clubeusuario> lista = this.getLotecaService().findAllClubeusuarioByLotecausuario(lotecausuario);
				getClubeusuarios().clear();
				getClubeusuarios().addAll( lista );
				clubesSource.clear();
				for (Clubeusuario c : lista) {
					c.setNuPontos( 0l );
					clubesSource.add(c.getClube().getNmClube());
				}
			}
			
			/*
			setClubeusuarios(new ArrayList<Clubeusuario>());
			Clube clube = new Clube();
			clube.setLoteca( super.getLotecaativa() );
			List<Clube> clubes = (List<Clube>) this.getLotecaService().findAll(clube);
			for (Clube c : clubes) {
				Clubeusuario cu = new Clubeusuario();
				cu.setClube( c );
				cu.setLotecausuario(new Lotecausuario());
				cu.getLotecausuario().setLoteca( c.getLoteca() );
				cu.getLotecausuario().setNuSeqlotecausuario(99);
				cu.setNuPontos(0l);
				cu.setNuPosicao( c.getCdClube() );
				getClubeusuarios().add(cu);
				
				clubesSource.add(c.getNmClube());
			}
			*/
		} catch (Exception e) {
			getMessages().addWarningMessage("msg_warning_nao_exite_loteca_ativa");
		}
		
		clubes = new DualListModel<String>(clubesSource, clubesTarget);
	}
	
	
	public String editarDadosInscricaoUsuario() {
		try {
			setEntity( super.getUsuariologado() );
			setDeSenhaaux( getEntity().getDeSenha() );
			Lotecausuario lotecausuario = super.getLotecaService().findLotecausuarioInscricao( super.getUsuariologado() );
			setClubeusuarios( super.getLotecaService().findAllClubeusuario( lotecausuario ) );
			return "/pages/user/usuario/eltcCadDadosUsuario.xhtml";
		} catch (Exception e) {
			return null;
		}
	}
	
	public String saveDadosInscricaoUsuario() {
		try {
			montaLogin();
			super.getLotecaService().saveDadosInscricaoUsuario(getEntity(), getClubeusuarios());
			super.getMessages().addSucessMessage("msg_sucess_registro_usuario_realizado_com_sucesso_aguarde_inicio_campeonato");
			return "/pages/user/usuario/eltcCadDadosUsuario.xhtml";
		} catch (Exception e) {
			super.getMessages().addWarningMessage("msg_warning_email_ja_cadastrado_na_base");
			e.printStackTrace();
		}
		return null;
	}

	
}
