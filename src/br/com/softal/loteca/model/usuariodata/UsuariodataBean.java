package br.com.softal.loteca.model.usuariodata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.ServiceException;
import br.com.softal.base.util.DateUtil;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.histusuariodata.Histusuariodata;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@ManagedBean(name = "usuariodataBean")
@SessionScoped
public class UsuariodataBean extends AbstractManegedBean<Usuariodata> implements
		Serializable {

	private Long cdData;
	private Usuariodata usuariodata;
	private List<SelectItem> datasencerradas;
	private List<AproveitamentoDTO> dtos;
	private List<Histusuariodata> historicos;

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
	
	public UsuariodataBean() {
		setDatasencerradas(new ArrayList<SelectItem>());
	}
	
	public List<AproveitamentoDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<AproveitamentoDTO> dtos) {
		this.dtos = dtos;
	}
	
	public List<Histusuariodata> getHistoricos() {
		return historicos;
	}

	@Override
	protected void initializeEntity() {
		setEntity(new Usuariodata());
	}
	
	private void populaComboDatasEncerradas() {
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
	
	private void populaComboDatasLotecaAtiva() {
		try {
			getDatasencerradas().clear();
			List<Data> datas = super.getLotecaService().findAllDatas( this.getLotecaativa() );
			for (Data data : datas) {
				SelectItem sItem = new SelectItem(data.getCdData(), DateUtil.dateToStr( data.getDtData() ));
				getDatasencerradas().add( sItem );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public String abrirConPontosCartoes() {
		 try {
			 setCdData( null );
			 setRows( null );
			 this.populaComboDatasEncerradas();
			 
			 List<AproveitamentoDTO> dtos = super.getLotecaService().findAllRanking(this.getLotecaativa().getCdLoteca(), null);
			 setDtos( dtos );
        } catch (Exception e) {
       	 	e.printStackTrace();
        }	
		return "/pages/user/usuariodata/eltcConPontosCartoes.xhtml";
	}
	
	public void carregaResultadoPontosCartoesChange(ValueChangeEvent event) {
		try {
			List<AproveitamentoDTO> dtos = new ArrayList<AproveitamentoDTO>();
			if ((Long)event.getNewValue() != null) {
				this.cdData = (Long) event.getNewValue();
				dtos = super.getLotecaService().findAllRanking(this.getLotecaativa().getCdLoteca(), cdData);
			} else {
				setCdData( null );
				dtos = super.getLotecaService().findAllRanking(this.getLotecaativa().getCdLoteca(), null);
			}
			setDtos(dtos);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
	public String abrirConHistoricoApostas() {
		 try {
			 setCdData( null );
			 setRows( null );
			 this.populaComboDatasLotecaAtiva();
			 historicos = new ArrayList<Histusuariodata>();
       } catch (Exception e) {
      	 	e.printStackTrace();
       }	
		return "/pages/user/usuariodata/eltcConHistoricoApostas.xhtml";
	}
	
	private void processaListaHistorico() {
		for (Histusuariodata hdata : this.historicos) {
			hdata.inicializaRelacionamentos();
			
			String deBytes = hdata.getDeBytesjogo();
			for (Jogo jogo : usuariodata.getData().getJogos()) {
				Jogousuario jogousuario = new Jogousuario();
				jogousuario.setJogo(jogo);
				
				String aposta = deBytes.substring(0, 3);
				jogousuario.setFlColuna1( Long.valueOf( aposta.substring(0,1) ) );
				jogousuario.setFlEmpate( Long.valueOf(  aposta.substring(1,2) ) );
				jogousuario.setFlColuna2( Long.valueOf( aposta.substring(2,3)) );
				
				hdata.getJogousuarios().add(jogousuario);
				
				deBytes = deBytes.substring(3);
			}
			
		}
		
	}
	
	public void carregaHistoricoApostasChange(ValueChangeEvent event) {
		try {
			usuariodata = new Usuariodata();
			historicos = new ArrayList<Histusuariodata>();
			if ((Long)event.getNewValue() != null) {
				Usuario usuario = super.getUsuariologado();
				Lotecausuario lotecausuario = super.getLotecaService().findLotecausuarioAtivo(usuario);
				
				usuariodata.setLotecausuario(lotecausuario);
				long cdData = (Long) event.getNewValue();
				Data data = super.getLotecaService().findData( cdData );
				usuariodata.setData(data);
				usuariodata = super.getLotecaService().findUsuariodataFecth(lotecausuario, data);
				
				historicos = super.getLotecaService().findHistoricoUsuarioData(usuariodata);
				this.processaListaHistorico();
			} 
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
}
