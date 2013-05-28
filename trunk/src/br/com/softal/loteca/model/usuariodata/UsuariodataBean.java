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
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@ManagedBean(name = "usuariodataBean")
@SessionScoped
public class UsuariodataBean extends AbstractManegedBean<Usuariodata> implements
		Serializable {

	private Long cdData;
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
	
	public String abrirConPontosCartoes() {
		 try {
			 setCdData( null );
			 setRows( null );
			 this.populaComboDatas();
			 
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
			 this.populaComboDatas();
			 
       } catch (Exception e) {
      	 	e.printStackTrace();
       }	
		return "/pages/user/usuariodata/eltcConHistoricoApostas.xhtml";
	}
	
	public void carregaHistoricoApostasChange(ValueChangeEvent event) {
		try {
			historicos = new ArrayList<Histusuariodata>();
			Usuario usuario = super.getUsuariologado();
			Lotecausuario lotecausuario = super.getLotecaService().findLotecausuarioAtivo(usuario);
			Usuariodata usuariodata = new Usuariodata();
			usuariodata.setLotecausuario(lotecausuario);
			if ((Long)event.getNewValue() != null) {
				long cdData = (Long) event.getNewValue();
				Data data = super.getLotecaService().findData( cdData );
				usuariodata.setData(data);
				
				historicos = super.getLotecaService().findHistoricoUsuarioData(usuariodata);
			} 
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
}
