package br.com.softal.loteca.model.usuariodata;

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
import br.com.softal.loteca.model.data.Data;

@SuppressWarnings("serial")
@ManagedBean(name = "usuariodataBean")
@SessionScoped
public class UsuariodataBean extends AbstractManegedBean<Usuariodata> implements
		Serializable {

	private Long cdData;
	private List<SelectItem> datasencerradas;
	private List<AproveitamentoDTO> dtos;

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
			 setDtos( new ArrayList<AproveitamentoDTO>() );
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
				//dtos = super.getLotecaService().findAllRanking(this.getLotecaativa().getCdLoteca(), null);
			}
			setDtos(dtos);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
