package br.com.softal.loteca.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.event.FlowEvent;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.classifclube.Classifclube;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.CanhotoDTO;

@SuppressWarnings("serial")
@ManagedBean(name="dataBean")
@SessionScoped
public class DataBean extends AbstractManegedBean<Data> {
	
	private boolean skip;
	private String deJogos;
	
	public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    }  
    
	public String getDeJogos() {
		return deJogos;
	}

	public void setDeJogos(String deJogos) {
		this.deJogos = deJogos;
	}
	
	/******************************************************************************/

	private void processaJogos() {
		if (getDeJogos() != null && !getDeJogos().equalsIgnoreCase("")) {
			getEntity().setJogos(new ArrayList<Jogo>());
			String[] jogos = getDeJogos().split("\n");
			long nuJogo = 0;
			for (String s : jogos) {
				Jogo jogo = new Jogo();
				jogo.setCdJogo( ++nuJogo );
				jogo.setDeJogo( s );
				jogo.setTpResultadofinal(null);
				getEntity().getJogos().add(jogo);
			}
		}
	}
	
	@Override
	protected void initializeEntity() {
		setEntity(new Data());
	}
	
	@PostConstruct
	public void init() {
		super.findAll();
		Collections.sort( this.getRows(), new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				return o2.getDtData().compareTo(o1.getDtData());
			}
		} );
	}
	
	@Override
	public void save() {
		try {
			if (getEntity().isStatusUpdate()) {
				super.update();
				super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			}
			getEntity().setStatusUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
	}
	
	public void gerarjogos() {
		try {
			LtcServiceLocator.getInstance().getLotecaService().gerarJogosusuarios(getEntity());
			getMessages().addSucessMessage("jogo_msg_sucesso_geracao_jogos");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		/*try {
			List<Jogousuario> jogousuarios = new ArrayList<Jogousuario>();
			List<Jogo> jogos = getEntity().getJogos();
			Loteca lotecaativa = LtcServiceLocator.getInstance().getLotecaService().findLotecaAtiva();
			List<Lotecausuario> usuarios = LtcServiceLocator.getInstance().getLotecaService().findAllLotecausuarioByLoteca(lotecaativa);
			for (Lotecausuario usuario : usuarios) {
				for (Jogo jogo : jogos) {
					Jogousuario jogousuario = new Jogousuario();
					jogousuario.setJogo(jogo);
					jogousuario.setLotecausuario(usuario);
					jogousuario.setTpJogo(null);
					jogousuario.setFlColuna1(null);
					jogousuario.setFlEmpate(null);
					jogousuario.setFlColuna2(null);
					jogousuarios.add(jogousuario);
				}
			}
			for (Jogousuario jogousuario : jogousuarios) {
				try {
					LtcServiceLocator.getInstance().getLotecaService().save(jogousuario);
				} catch (DataIntegrityViolationException e) {
					// tentou salvar mas ja existia
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			getMessages().addSucessMessage("jogo_msg_sucesso_geracao_jogos");
		} catch (ServiceException e) {
			e.printStackTrace();
		}*/
	}
	
	public void enviaremailjogoliberado() {
		try {
			Loteca lotecaativa = super.getLotecaativa();
			List<Lotecausuario> usuarios = LtcServiceLocator.getInstance().getLotecaService().findAllLotecausuarioAtivoByLoteca(lotecaativa);
			EmailData.enviaEmailJogoLiberado(getEntity(), usuarios);
			
			getEntity().setFlEnviouemailjogoliberado(1l);
			this.save();
			super.getMessages().addSucessMessage("msg_sucess_envio_email_jogo_liberado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void processarResultados() {
		try {
			LtcServiceLocator.getInstance().getLotecaService().processaResultadosData(getEntity());
			
			LtcServiceLocator.getInstance().getLotecaService().processarHistoricoData(getEntity());
			
			getEntity().setFlAtualizouresultados(1l);
			this.save();
			super.getMessages().addSucessMessage("msg_sucess_processamento_resultado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String salvaClassificacaojogos() {
		try {
			Loteca lotecaativa = super.getLotecaativa();
			LtcServiceLocator.getInstance().getLotecaService().geraClassificacao(lotecaativa, getEntity());
			getEntity().setFlAtualizouclassificacao(1l);
			this.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void atualizarResultadoJogos() {
		try {
			List<Jogo> jogos = getEntity().getJogos();
			for (Jogo j : jogos) {
				LtcServiceLocator.getInstance().getLotecaService().update(j);
			}
			getEntity().setFlAtualizoutimes(1l);
			this.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enviaremailresultado() {
		try {
			Loteca lotecaativa = super.getLotecaativa();
			List<Lotecausuario> usuarios = LtcServiceLocator.getInstance().getLotecaService().findAllLotecausuarioByLoteca(lotecaativa);
			EmailData.enviaEmailResultado(getEntity(), usuarios);
			getEntity().setFlEnviouemailresultado(1l);
			this.save();
			super.getMessages().addSucessMessage("msg_sucess_envio_email_resultado_cadastrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String saveWizard() {
		try {
			//getEntity().setTpSituacao( Data.TP_SITUACAO_CADASTRAMENTO );
			getEntity().setTpSituacao( 1L );
			getEntity().setFlAtualizouresultados(0l);
			getEntity().setFlAtualizoutimes(0l);
			getEntity().setFlAtualizouclassificacao(0l);
			getEntity().setFlEnviouemailjogoliberado(0l);
			getEntity().setFlEnviouemailresultado(0l);
			getEntity().setLoteca( super.getLotecaativa() );
			
			super.save();
			this.inserirJogos();
			this.init();
			super.getMessages().addSucessMessage("mensagem_registro_salvo_com_sucesso");
			return editar();
		} catch (Exception e) {
			e.printStackTrace();
			super.getMessages().addFatalMessage(e.getMessage());
			return null;
		}
		
	}
	
	private void inserirJogos() {
		List<Jogo> jogos = getEntity().getJogos();
		for (Jogo jog : jogos) {
			jog.setData(getEntity());
			LtcServiceLocator.getInstance().getLotecaService().save(jog);
		}
	}
	
	public String adicionar() {
		initializeEntity();
		getEntity().setStatusInsert();
		setDeJogos(null);
		return "eltcCadDataWizard";
	}
	
	@SuppressWarnings("unchecked")
	private void carregaJogosData() {
		Jogo j = new Jogo();
		j.setData(getEntity());
		List<Jogo> jogos = (List<Jogo>) LtcServiceLocator.getInstance().getLotecaService().findAll(j);
		getEntity().setJogos(jogos);
	}
	
	private void carregaClassifclubes() {
		Classifclube cc = new Classifclube();
		cc.setData(getEntity());
		List<Classifclube> classificacoes = (List<Classifclube>) LtcServiceLocator.getInstance().getLotecaService().findAll( cc );
		Collections.sort(classificacoes, new Comparator<Classifclube>() {
			@Override
			public int compare(Classifclube o1, Classifclube o2) {
				return o1.getNuClassificacao().compareTo(o2.getNuClassificacao());
			}
		});
		getEntity().setClassifclubes( classificacoes );
	}
	
	public String editar() {
		getEntity().setStatusUpdate();
		this.carregaJogosData();
		this.carregaClassifclubes();
		return "eltcCadData";
	}
	
	public String excluir() {
		try {
			super.delete();
			super.getMessages().addSucessMessage("mensagem_registro_excluido_com_sucesso");
			return adicionar();
		} catch (DataIntegrityViolationException e) {
			this.getMessages().addWarningMessage("warning_constrait_violation");
		} catch (Exception e) {
			super.getMessages().addSucessMessage("mensagem_ocorreu_um_erro_ao_excluir_o_registro");
			e.printStackTrace();
		} finally {
			init();
		}
		return "eltcCadData";
	}
	
	public String onFlowProcess(FlowEvent event) {  
        if(skip) {  
            return "confirm";  
        } else {  
        	if (!event.getOldStep().equalsIgnoreCase("confirm") && event.getNewStep().equalsIgnoreCase("confirm")) {
        		processaJogos();
        	}
            return event.getNewStep();  
        }  
    }  
	
	public void imprimirCanhotos() {
		try {
			this.gerarRelatoriCanhatos();
			super.getMessages().addSucessMessage("msg_sucess_canhotos_impressos_com_sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void gerarRelatoriCanhatos() {
		String jasperName = "cartoes.jasper";
		jasperName = "cartaounico.jasper";
		jasperName = "cartaounicoafastado.jasper";
		 FacesContext context = FacesContext.getCurrentInstance();
		 ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		 String sourceFileName = servletContext.getRealPath("/relatorios/" + jasperName);
		 Map<String, Object> params = new HashMap<String, Object>();
		  
		 try {
             HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
             response.setContentType("application/pdf");
             String nmArquivo = "cartoes_" + getEntity().getCdData() + ".pdf";
             response.addHeader("Content-disposition", "attachment; filename=" + nmArquivo);
             List<CanhotoDTO> usuarios = getDados();
             JRDataSource dataSource = new JRBeanCollectionDataSource( usuarios );
             JasperPrint impressao = JasperFillManager.fillReport(sourceFileName, params, dataSource);
             JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());
             context.getApplication().getStateManager().saveView(context);
             context.responseComplete();
         } catch (Exception e) {
        	 e.printStackTrace();
         }
	}
	
	private List<CanhotoDTO> getDados() {
		try {
			return LtcServiceLocator.getInstance().getLotecaService().findCanhotosConcurso(getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void gerarjogosaleatorios() {
		try {
			super.getMessages().addSucessMessage("msg_sucess_jogos_aleatorios_gerados_com_sucesso");
			LtcServiceLocator.getInstance().getLotecaService().gerarJogosAleatoriosUsuarioSemApostas(getEntity());
		} catch (Exception e) {
			super.getMessages().addWarningMessage("msg_warning_ocorreu_erro_na_geracao_dos_jogos_aleatorios");
			e.printStackTrace();
		}
	}
	
	public boolean getExisteUsuarioSemAposta() {
		try {
			return LtcServiceLocator.getInstance().getLotecaService().existeUsuarioSemAposta(getEntity());
		} catch (ServiceException e) {
			return true;
		}
	}

}
