package br.com.softal.loteca.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
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
import br.com.softal.loteca.model.clube.ClubeDTO;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.usuariodata.AproveitamentoDTO;
import br.com.softal.loteca.model.usuariodata.Usuariodata;
import br.com.softal.loteca.util.Constantes;
import br.com.softal.loteca.util.Enuns.SituacaoLoteca;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

@SuppressWarnings("serial")
@ManagedBean(name = "dashboardBean")
@RequestScoped
public class DashboardBean implements Serializable {

	private DashboardModel model;
	private List<Usuariodata> ranking;
	private List<Classifclube> classifclubes;
	private List<ClubeDTO> campeoes;
	private List<ClubeDTO> rebaixados;
	private Usuariodata usuariodata;
	private Loteca lotecaativa;
	private Data ultimadataencerrada;
	private List<AproveitamentoDTO> campeoeslotecas;

	public DashboardBean() {
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		this.carregaLotecaativa();

		if (lotecaativa.getTpSituacao() == SituacaoLoteca.CADASTRAMENTO
				.longValue()) { // -- Constantes.lOTECA_SITUACAO_CADASTRAMENTO
			// column1.addWidget("dhbregulamento");
			// column2.addWidget("dhbinscricao");

			// model.addColumn(column1);
			// model.addColumn(column2);
		} else if (lotecaativa.getTpSituacao() == SituacaoLoteca.ANDAMENTO
				.longValue()) { // -- Constantes.lOTECA_SITUACAO_ANDAMENTO
			// --column1.addWidget("dhbpremiacao");
			if (this.carregaCampeoesLotecas()) {
				column1.addWidget("dhbcampeoeslotecas");
			}

			if (this.carregaClassificacao()) {
				column1.addWidget("dhbclassificacao");
			}

			if (this.carregaVotosClubes()) {
				column1.addWidget("dhbcampeoes");
				column1.addWidget("dhbrebaixados");
			}

			if (this.carregaRanking()) {
				column2.addWidget("dhbranking");
			}

			if (this.carregaUltimadataencerrada()) {
				column1.addWidget("dhbultimarodada");
			}

			model.addColumn(column1);
			model.addColumn(column2);
		} else {
			column1.addWidget("dhbregulamento");

			model.addColumn(column1);
		}

	}

	private void carregaLotecaativa() {
		if (!FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey(Constantes.LOTECA_ATIVA)) {
			try {
				lotecaativa = LtcServiceLocator.getInstance()
						.getLotecaService().findLotecaAtiva();
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap()
						.put(Constantes.LOTECA_ATIVA, lotecaativa);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lotecaativa = (Loteca) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap()
				.get(Constantes.LOTECA_ATIVA);
	}

	public void handleReorder(DashboardReorderEvent event) {
		/*
		 * FacesMessage message = new FacesMessage();
		 * message.setSeverity(FacesMessage.SEVERITY_INFO);
		 * message.setSummary("Reordered: " + event.getWidgetId());
		 * message.setDetail("Item index: " + event.getItemIndex() +
		 * ", Column index: " + event.getColumnIndex() + ", Sender index: " +
		 * event.getSenderColumnIndex());
		 * 
		 * addMessage(message);
		 */
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

	public List<ClubeDTO> getCampeoes() {
		return campeoes;
	}

	public void setCampeoes(List<ClubeDTO> campeoes) {
		this.campeoes = campeoes;
	}

	public List<ClubeDTO> getRebaixados() {
		return rebaixados;
	}

	public void setRebaixados(List<ClubeDTO> rebaixados) {
		this.rebaixados = rebaixados;
	}

	public List<AproveitamentoDTO> getCampeoeslotecas() {
		return campeoeslotecas;
	}

	public void setCampeoeslotecas(List<AproveitamentoDTO> campeoeslotecas) {
		this.campeoeslotecas = campeoeslotecas;
	}

	private boolean carregaRanking() {
		try {
			setRanking(LtcServiceLocator.getInstance().getLotecaService()
					.findAllDadosRankingLotecaAtiva());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean carregaClassificacao() {
		try {
			if (!existeLotecaAtiva()) {
				return false;
			}
			setClassifclubes(LtcServiceLocator.getInstance().getLotecaService()
					.findAllClassifclubeAtual(this.lotecaativa));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean carregaUltimadataencerrada() {
		try {
			Data findUltimaDataEncerrada = LtcServiceLocator.getInstance()
					.getLotecaService()
					.findUltimaDataEncerrada(this.lotecaativa);
			if (findUltimaDataEncerrada != null) {
				setUltimadataencerrada(findUltimaDataEncerrada);
			} else {
				Data data = new Data();
				setUltimadataencerrada(data);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean carregaVotosClubes() {
		try {
			if (!existeLotecaAtiva()) {
				return false;
			}
			setCampeoes(LtcServiceLocator.getInstance().getLotecaService()
					.findAllVotosCampeao(lotecaativa.getCdLoteca()));
			setRebaixados(LtcServiceLocator.getInstance().getLotecaService()
					.findAllVotosRebaixados(lotecaativa.getCdLoteca()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean carregaCampeoesLotecas() {
		try {
			if (!existeLotecaAtiva()) {
				// return false;
			}
			List<AproveitamentoDTO> lista = LtcServiceLocator.getInstance().getLotecaService().findAllCampeoes();
			Long nuAno = 0L;
			List<AproveitamentoDTO> listaAno = new ArrayList<AproveitamentoDTO>();
			AproveitamentoDTO anoLoteca = new AproveitamentoDTO();
			for (AproveitamentoDTO dto : lista) {
				if (!dto.getNuAnoloteca().equals(nuAno)) {
					nuAno = dto.getNuAnoloteca();
					
					anoLoteca = new AproveitamentoDTO();
					anoLoteca.setNuAnoloteca(nuAno);
					anoLoteca.setCampeoes(new ArrayList<AproveitamentoDTO>());
					
					listaAno.add(anoLoteca);
				}
				anoLoteca.getCampeoes().add(dto);
			}

			setCampeoeslotecas(listaAno);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean existeLotecaAtiva() {
		return lotecaativa != null;
	}

}
