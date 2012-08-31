package br.com.softal.loteca.model.jogousuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.base.util.DateUtil;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.AproveitamentoDTO;

@SuppressWarnings("serial")
@ManagedBean(name="jogousuarioBean")
@SessionScoped
public class JogousuarioBean extends AbstractManegedBean<Jogousuario> implements Serializable {
	
	private Long cdLoteca;
	private List<Jogousuario> jogousuarios; 
	private Boolean jogohabilitado;
	private Long cdData;
	private List<SelectItem> datasencerradas;
	private String deAproveitamento;
	
	public String getDeAproveitamento() {
		return deAproveitamento;
	}

	public void setDeAproveitamento(String deAproveitamento) {
		this.deAproveitamento = deAproveitamento;
	}

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
	
	public Long getCdData() {
		return cdData;
	}

	public void setCdData(Long cdData) {
		this.cdData = cdData;
	}
	
	public List<SelectItem> getDatasencerradas() {
		return datasencerradas;
	}

	public void setDatasencerradas(List<SelectItem> datasencerradas) {
		this.datasencerradas = datasencerradas;
	}

	public JogousuarioBean() {
		setDatasencerradas(new ArrayList<SelectItem>());
	}
	
	/*****************************************************************************************************/
	
	@Override
	protected void initializeEntity() {
		setEntity(new Jogousuario());
		setJogousuarios(new ArrayList<Jogousuario>());
	}
	
	private void carregaJogoUsuario() {
		try {
			Lotecausuario lotecausuario = super.getLotecaService().findLotecausuarioAtivo(super.getUsuariologado()); 
			if (lotecausuario != null) {
				getEntity().setLotecausuario(lotecausuario);
				List<Jogousuario> jogousuarios = super.getLotecaService().findAllJogoUsuarioDataAtiva( lotecausuario );
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
			super.getLotecaService().saveAllJogousuario( getJogousuarios() );
			super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
		} catch (ServiceException e) {
			super.getMessages().addWarningMessage(e.getMessage());
			return null;
		}
		return abrirCadJogousuario();		
	}
	
	public String gerarCadJogousuarioAleatorio() {
		try {
			super.getLotecaService().saveAllJogousuario( getJogousuarios(), true);
			super.getMessages().addSucessMessage("msg_sucess_jogo_aleatorio_gerado_com_sucesso");
		} catch (ServiceException e) {
			super.getMessages().addWarningMessage(e.getMessage());
			return null;
		}
		return abrirCadJogousuario();		
	}
	
	public void enviarJogoEmailPessoal() {
		try {
			super.getLotecaService().saveAllJogousuario( getJogousuarios() );
			EmailJogousuario.enviaEmailJogousuarioParaEmailPessoal(getJogousuarios(), getUsuariologado());
			super.getMessages().addSucessMessage("msg_sucess_email_enviado_com_sucesso");
		} catch (Exception e) {
			super.getMessages().addWarningMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String abrirCadJogousuario() {
		getJogousuarios().add(new Jogousuario());
		this.carregaJogoUsuario();
		return "/pages/user/jogousuario/eltcCadJogousuario.xhtml";
	}
        
    private void populaComboDatas() {
    	try {
    		getDatasencerradas().clear();
         	List<Data> datas = super.getLotecaService().findAllDatasEncerradas( this.getLotecaativa() );
         	for (Data data : datas) {
         		SelectItem sItem = new SelectItem(data.getCdData(), DateUtil.dateToStr( data.getDtData() ));
         		getDatasencerradas().add( sItem );
         	}
        } catch (Exception e) {
                e.printStackTrace();
        }	
    }
	
	public String abrirConResultadoRodada() {
		 try {
			 setCdData( null );
			 setRows( null );
			 this.populaComboDatas();
         } catch (Exception e) {
        	 e.printStackTrace();
         }	
		return "/pages/user/jogousuario/eltcConResultadoRodada.xhtml";
	}
	
	public void carregaResultadoJogoUsuarioChange(ValueChangeEvent event) {
		try {
			if ((Long)event.getNewValue() != null) {
				this.cdData = (Long) event.getNewValue();
				Data data = super.getLotecaService().findData(this.cdData);
				Lotecausuario lotecausuario = super.getLotecaService().findLotecausuarioAtivo(super.getUsuariologado()); 
				List<Jogousuario> lista = super.getLotecaService().findAllJogoUsuario(data, lotecausuario);
				setRows(lista);
			} else {
				setCdData( null );
				setRows( new ArrayList<Jogousuario>() );
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	/*public String aproveitamentoAjax() {
		return JogousuarioJson.getDados();
	}*/
	
	public String abrirConGraficoAproveitamento() {
		try {
			Loteca lotecaativa = super.getLotecaativa();
			List<AproveitamentoDTO> aproveitamentos = LtcServiceLocator.getInstance().getLotecaService().findAllAproveitamento(lotecaativa);
			setDeAproveitamento( JogousuarioJson.getDados( aproveitamentos ) );
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "/pages/user/jogousuario/eltcConGraficoAproveitamento.xhtml";
	}
	
}
