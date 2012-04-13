package br.com.softal.loteca.model.jogousuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@ManagedBean(name="jogousuarioBean")
@SessionScoped
public class JogousuarioBean extends AbstractManegedBean<Jogousuario> implements Serializable {
	
	private Long cdLoteca;
	private List<Jogousuario> jogousuarios; 
	private Boolean jogohabilitado;
	
	public Long getCdLoteca() {
		return cdLoteca;
	}

	public void setCdLoteca(Long cdLoteca) {
		this.cdLoteca = cdLoteca;
	}
	
	public List<Jogousuario> getJogousuarios() {
		return jogousuarios;
	}

	public void setJogousuarios(List<Jogousuario> jogousuarios) {
		this.jogousuarios = jogousuarios;
	}
	
	public Boolean getJogohabilitado() {
		return jogohabilitado == null ? false : jogohabilitado;
	}

	public void setJogohabilitado(Boolean jogohabilitado) {
		this.jogohabilitado = jogohabilitado;
	}

	public JogousuarioBean() {
		
	}
	
	/*****************************************************************************************************/
	
	@Override
	protected void initializeEntity() {
		setEntity(new Jogousuario());
		setJogousuarios(new ArrayList<Jogousuario>());
	}
	
	private void carregaJogoUsuario() {
		try {
			Lotecausuario lotecausuario = LtcServiceLocator.getInstance().getLotecaService().findLotecausuarioAtivo(super.getUsuariologado()); 
			if (lotecausuario != null) {
				getEntity().setLotecausuario(lotecausuario);
				List<Jogousuario> jogousuarios = LtcServiceLocator.getInstance().getLotecaService().findAllJogoUsuarioDataAtiva( lotecausuario );
				setJogousuarios(jogousuarios);
				this.setJogohabilitado(getJogousuarios().size() > 0);
			} else {
				setJogousuarios(new ArrayList<Jogousuario>());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String salvarCadJogousuario() {
		try {
			LtcServiceLocator.getInstance().getLotecaService().saveAllJogousuario( getJogousuarios() );
			super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
		} catch (ServiceException e) {
			super.getMessages().addWarningMessage(e.getMessage());
			return null;
		}
		return abrirCadJogousuario();		
	}
	
	public String gerarCadJogousuarioAleatorio() {
		try {
			LtcServiceLocator.getInstance().getLotecaService().saveAllJogousuario( getJogousuarios(), true);
			super.getMessages().addSucessMessage("msg_sucess_jogo_aleatorio_gerado_com_sucesso");
		} catch (ServiceException e) {
			super.getMessages().addWarningMessage(e.getMessage());
			return null;
		}
		return abrirCadJogousuario();		
	}
	
	public void enviarJogoEmailPessoal() {
		try {
			EmailJogousuario.enviaEmailJogousuarioParaEmailPessoal(getJogousuarios(), getUsuariologado());
			super.getMessages().addSucessMessage("msg_sucess_email_enviado_com_sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String abrirCadJogousuario() {
		getJogousuarios().add(new Jogousuario());
		this.carregaJogoUsuario();
		return "/pages/user/jogousuario/eltcCadJogousuario.xhtml";
	}
        
        private void carregaResultadoJogoUsuario() {
            try {
               Loteca lotecaativa = super.getLotecaativa();
            } catch (Exception e) {
                    e.printStackTrace();
            }	
	}
	
	public String abrirConResultadoRodada() {
		getJogousuarios().add(new Jogousuario());
		this.carregaResultadoJogoUsuario();
		return "/pages/user/jogousuario/eltcConResultadoRodada.xhtml";
	}
	
}
