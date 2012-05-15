package br.com.softal.loteca.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.classifclube.Classifclube;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.usuariodata.Usuariodata;
import br.com.softal.loteca.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean(name="dashboardBean")
@RequestScoped
public class DashboardBean implements Serializable {

	private DashboardModel model;
	private List<Usuariodata> ranking;
	private List<Classifclube> classifclubes;
	private Usuariodata usuariodata;
	private Loteca lotecaativa;
	private Data ultimadataencerrada;

	public DashboardBean() {
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		//DashboardColumn column3 = new DefaultDashboardColumn();
		this.carregaLotecaativa();		
		
		if (lotecaativa.getTpSituacao() == Constantes.lOTECA_SITUACAO_CADASTRAMENTO) {
			column1.addWidget("dhbregulamento");
			column2.addWidget("dhbinscricao");
			
			model.addColumn(column1);
			model.addColumn(column2);
		} else if (lotecaativa.getTpSituacao() == Constantes.lOTECA_SITUACAO_ANDAMENTO) {
			column1.addWidget("dhbclassificacao");
			column1.addWidget("dhbultimarodada");
			column2.addWidget("dhbranking");
			
			
			model.addColumn(column1);
			model.addColumn(column2);
			
			this.carregaRanking();
			this.carregaClassificacao();
			this.carregaUltimadataencerrada();
		} else {
			column1.addWidget("dhbregulamento");
			
			model.addColumn(column1);
		}

	}
	
	private void carregaLotecaativa() {
		if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(Constantes.LOTECA_ATIVA)) {
			try {
				lotecaativa = LtcServiceLocator.getInstance().getLotecaService().findLotecaAtiva();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constantes.LOTECA_ATIVA, lotecaativa);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		lotecaativa = (Loteca) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constantes.LOTECA_ATIVA);
	}

	public void handleReorder(DashboardReorderEvent event) {
		/*FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex()
				+ ", Column index: " + event.getColumnIndex()
				+ ", Sender index: " + event.getSenderColumnIndex());

		addMessage(message);*/
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public DashboardModel getModel() {
		return model;
	}

	public List<Usuariodata> getRanking() {
		return ranking;
	}

	public void setRanking(List<Usuariodata> ranking) {
		this.ranking = ranking;
	}
	
	public Usuariodata getUsuariodata() {
		return usuariodata;
	}

	public void setUsuariodata(Usuariodata usuariodata) {
		this.usuariodata = usuariodata;
	}
	
	public List<Classifclube> getClassifclubes() {
		return classifclubes;
	}

	public void setClassifclubes(List<Classifclube> classifclubes) {
		this.classifclubes = classifclubes;
	}
	
	public Data getUltimadataencerrada() {
		return ultimadataencerrada;
	}

	public void setUltimadataencerrada(Data ultimadataencerrada) {
		this.ultimadataencerrada = ultimadataencerrada;
	}
	
	private void carregaRanking() {
		try {
			setRanking( LtcServiceLocator.getInstance().getLotecaService().findAllDadosRankingLotecaAtiva() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void carregaClassificacao() {
		try {
			setClassifclubes( LtcServiceLocator.getInstance().getLotecaService().findAllClassifclubeAtual() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void carregaUltimadataencerrada() {
		try {
			Data findUltimaDataEncerrada = LtcServiceLocator.getInstance().getLotecaService().findUltimaDataEncerrada( this.lotecaativa );
			if (findUltimaDataEncerrada != null) {
				setUltimadataencerrada( findUltimaDataEncerrada );
			} else {
				setUltimadataencerrada(new Data());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
