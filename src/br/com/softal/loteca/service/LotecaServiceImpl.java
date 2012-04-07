package br.com.softal.loteca.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.ha.backend.CollectedInfo;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.model.usuario.HbnUsuarioDAO;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.DefaultServiceImpl;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.classifclube.Classifclube;
import br.com.softal.loteca.model.classifclube.HbnClassifclubeDAO;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clube.HbnClubeDAO;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.clubeusuario.HbnClubeusuarioDAO;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.jogousuario.HbnJogousuarioDAO;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.jogousuario.JogousuarioValidator;
import br.com.softal.loteca.model.loteca.HbnLotecaDAO;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.HbnLotecausuarioDAO;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.HbnUsuariodataDAO;
import br.com.softal.loteca.model.usuariodata.Usuariodata;
import br.com.softal.loteca.util.Constantes;

public class LotecaServiceImpl extends DefaultServiceImpl implements LotecaService {
	
	private HbnLotecaDAO lotecaDAO;
	private HbnLotecausuarioDAO lotecausuarioDAO;
	private HbnClubeDAO clubeDAO;
	private HbnClubeusuarioDAO clubeusuarioDAO;
	private HbnClassifclubeDAO classifclubeDAO;
	private HbnUsuarioDAO usuarioDAO;
	private HbnJogousuarioDAO jogousuarioDAO;
	private HbnUsuariodataDAO usuariodataDAO;
	
	private HbnLotecaDAO getLotecaDAO() {
		return lotecaDAO;
	}
	
	public HbnJogousuarioDAO getJogousuarioDAO() {
		return jogousuarioDAO;
	}

	public void setJogousuarioDAO(HbnJogousuarioDAO jogousuarioDAO) {
		this.jogousuarioDAO = jogousuarioDAO;
	}

	public void setLotecaDAO(HbnLotecaDAO lotecaDAO) {
		this.lotecaDAO = lotecaDAO;
	}

	private HbnLotecausuarioDAO getLotecausuarioDAO() {
		return lotecausuarioDAO;
	}

	public void setLotecausuarioDAO(HbnLotecausuarioDAO lotecausuarioDAO) {
		this.lotecausuarioDAO = lotecausuarioDAO;
	}

	private HbnClubeDAO getClubeDAO() {
		return clubeDAO;
	}

	public void setClubeDAO(HbnClubeDAO clubeDAO) {
		this.clubeDAO = clubeDAO;
	}
	
	private HbnClubeusuarioDAO getClubeusuarioDAO() {
		return clubeusuarioDAO;
	}

	public void setClubeusuarioDAO(HbnClubeusuarioDAO clubeusuarioDAO) {
		this.clubeusuarioDAO = clubeusuarioDAO;
	}
	
	private HbnClassifclubeDAO getClassifclubeDAO() {
		return classifclubeDAO;
	}

	public void setClassifclubeDAO(HbnClassifclubeDAO classifclubeDAO) {
		this.classifclubeDAO = classifclubeDAO;
	}
	
	private HbnUsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(HbnUsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	private HbnUsuariodataDAO getUsuariodataDAO() {
		return usuariodataDAO;
	}

	public void setUsuariodataDAO(HbnUsuariodataDAO usuariodataDAO) {
		this.usuariodataDAO = usuariodataDAO;
	}

	@Override
	public Loteca findLotecaAtiva() {
		return getLotecaDAO().findLotecaAtiva();
	}
	
	@Override
	public Usuario findUsuarioByLogin(String deLogin) throws ServiceException {
		try {
			return getUsuarioDAO().findUsuarioByLogin(deLogin);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Lotecausuario> findAllLotecausuarioByLoteca(Long cdLoteca) {
		return getLotecausuarioDAO().findAllLotecausuarioByLoteca(cdLoteca);
	}
	
	@Override
	public List<Lotecausuario> findAllLotecausuarioByLoteca(Loteca loteca)
			throws ServiceException {
		if (loteca.getCdLoteca() != 0) {
			return getLotecausuarioDAO().findAllLotecausuarioByLoteca(loteca.getCdLoteca());
		}
		return null;
	}
	
	@Override
	public List<Clube> findAllClubeByLoteca(long cdLoteca) throws ServiceException {
		return getClubeDAO().findAllClubeByLoteca(cdLoteca);
	}
	
	@Override
	public List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws ServiceException {
		return getClubeusuarioDAO().findAllClubeusuario(clubeusuario);
	}
	
	@Override
	public List<Clubeusuario> findAllClubeusuario(Lotecausuario lotecausuario) throws ServiceException {
		Clubeusuario clubeusuario = new Clubeusuario();
		clubeusuario.setLotecausuario(lotecausuario);
		return getClubeusuarioDAO().findAllClubeusuario(clubeusuario);
	}
	
	@Override
	public void deleteClassifclube(Data data) throws ServiceException {
		try {
			getClassifclubeDAO().deleteClassifclube(data);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	public void geraClassificacao(Loteca loteca, Data data) throws ServiceException {
		try {
			data.getClassifclubes().clear();
			this.deleteClassifclube(data);
			String deClassificacao = data.getDeClassificacao();
			if (deClassificacao != null) {
				String[] classificacoes = deClassificacao.split("\n");
				if (classificacoes.length != 20) {
					throw new ServiceException("classifclube_msg_warning_classificacao_invalida");
				}
				for (String classif : classificacoes) {
					String[] vclassif = classif.split(";");
					if (vclassif.length != 11) {
						throw new ServiceException("Classificação inválida");
					}
					
					//-- Busca o clube pelo nome
					String nmClube = vclassif[1];
					Clube clube = new Clube();
					clube.setLoteca(loteca);
					clube.setNmClube(nmClube);
					clube = getClubeDAO().findClubePorNome(clube);
					
					
					Classifclube cc = new Classifclube();
					//nuSeqclassifclube
					cc.setData(data); //cdData
					cc.setNuClassificacao( 	Long.valueOf(vclassif[0]) ); //nuClassificacao
					cc.setClube(clube); 								 //nuSeqclube
					cc.setNuPontos( 	 	Long.valueOf(vclassif[2].trim()) ); //nuPontos
					cc.setNuJogos( 			Long.valueOf(vclassif[3].trim()) ); //nuJogos
					cc.setNuVitorias( 		Long.valueOf(vclassif[4].trim()) ); //nuVitorias
					cc.setNuEmpates( 		Long.valueOf(vclassif[5].trim()) ); //nuEmpates
					cc.setNuDerrotas( 		Long.valueOf(vclassif[6].trim()) ); //nuDerrotas
					cc.setNuGolspro( 		Long.valueOf(vclassif[7].trim()) ); //nuGolspro
					cc.setNuGolscontra( 	Long.valueOf(vclassif[8].trim()) ); //nuGolscontra
					cc.setNuSaldogols( 		Long.valueOf(vclassif[9].trim()) ); //nuSaldogols
					cc.setNuPercaprov( 		Long.valueOf(vclassif[10].trim()) ); //nuPercaprov
					save(cc);
					data.getClassifclubes().add(cc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	@Override
	public List<Jogousuario> findAllJogoUsuarioDataEmAndamento(Jogousuario jogousuario) throws ServiceException {
		Data data = this.findDataEmAndamentoLotecaAtiva();
		jogousuario.setJogo(new Jogo());
		jogousuario.getJogo().setData(data);
		List<Jogousuario> lista = (List<Jogousuario>) this.findAll(jogousuario);
		return lista;
	}*/

	@Override
	public Data findDataEmAndamentoLotecaAtiva() {
		Data data = new Data();
		data.setTpSituacao( Constantes.DATA_SITUACAO_EM_ANDAMENTO );
		List<Data> lista = (List<Data>) super.findAll(data);
		return lista.size() > 0 ? lista.get(0) : null;
	}
	
	@Override
	public Lotecausuario findLotecausuarioAtivo(Usuario usuario) {
		return getLotecausuarioDAO().findLotecausuarioAtivo(usuario);
	}
	
	@Override
	public List<Jogousuario> findAllJogoUsuarioDataAtiva(Lotecausuario lotecausuario) {
		return getJogousuarioDAO().findAllJogoUsuarioDataAtiva(lotecausuario);
	}
	
	@Override
	public void saveAllJogousuario(List<Jogousuario> jogos) throws ServiceException {
		this.saveAllJogousuario(jogos, false);
	}
	
	@Override
	public void saveAllJogousuario(List<Jogousuario> jogos, boolean aleatorio) throws ServiceException {
		if (aleatorio) {
			JogousuarioValidator.gerarJogousuarioAleatorio(jogos);
		}
		JogousuarioValidator.validarJogousuario(jogos);
		for (Jogousuario ju : jogos) {
			super.update(ju);
		}
		
		Jogousuario jogousuario = jogos.get(0);
		
		String deBytesjogo = JogousuarioValidator.geraBytesJogos(jogos);
		Usuariodata ud = getUsuariodataDAO().findUsuariodata(jogousuario);
		ud.setDeBytesjogo(deBytesjogo);
		ud.setFlApostou(1l);
		ud.setFlGeradoaleat((aleatorio ? 1l : 0l));
		this.update(ud);
	}
	
	@Override
	public void gerarJogosusuarios(Data data) throws ServiceException {
		try {
			List<Jogousuario> jogousuarios = new ArrayList<Jogousuario>();
			List<Jogo> jogos = data.getJogos();
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
			
			for (Lotecausuario usuario : usuarios) {
				Usuariodata ud = new Usuariodata();
				ud.setData(data);
				ud.setLotecausuario(usuario);
				ud.setFlApostou(0l);
				ud.setFlCanhoto(0l);
				ud.setFlGeradoaleat(0l);
				ud.setFlPagou(0l);
				try {
					LtcServiceLocator.getInstance().getLotecaService().save( ud );
				} catch (DataIntegrityViolationException e) {
					// tentou salvar mas ja existia
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	private void processaResultadoCanhotos(Loteca lotecaativa, Data data, List<Lotecausuario> usuarios) {
		//-- Calculando o resultado dos canhotos
		for (Lotecausuario lu : usuarios) {
			List<Jogousuario> jogosusuario = getJogousuarioDAO().findAllJogoUsuario(data, lu);
			long nuPontoscartao = 0;
			for (Jogousuario jogousuario : jogosusuario) {
				/**************************** JOGO SIMPLES ****************************/
				if (jogousuario.getTpJogo().longValue() == Constantes.JOGO_TPJOGO_SIMPLES) {
					//-- Coluna 1
					if (jogousuario.getJogo().getTpResultadofinal().longValue() == Constantes.JOGO_TIPO_RESULTADO_COLUNA1 &&  jogousuario.getFlColuna1().longValue() == 1) {
						nuPontoscartao++;
					} else
					//-- Empate
					if (jogousuario.getJogo().getTpResultadofinal().longValue() == Constantes.JOGO_TIPO_RESULTADO_EMPATE &&  jogousuario.getFlEmpate().longValue() == 1) {
						nuPontoscartao++;
					} else
					//-- Coluna 2
					if (jogousuario.getJogo().getTpResultadofinal().longValue() == Constantes.JOGO_TIPO_RESULTADO_COLUNA2 &&  jogousuario.getFlColuna2().longValue() == 1) {
						nuPontoscartao++;
					}
				}
				
				/**************************** JOGO DUPLO ****************************/
				if (jogousuario.getTpJogo().longValue() == Constantes.JOGO_TPJOGO_DUPLO) {
					//-- Coluna 1
					if (jogousuario.getJogo().getTpResultadofinal().longValue() == Constantes.JOGO_TIPO_RESULTADO_COLUNA1 &&  jogousuario.getFlColuna1().longValue() == 1) {
						nuPontoscartao++;
					} else
					//-- Empate
					if (jogousuario.getJogo().getTpResultadofinal().longValue() == Constantes.JOGO_TIPO_RESULTADO_EMPATE &&  jogousuario.getFlEmpate().longValue() == 1) {
						nuPontoscartao++;
					} else
					//-- Coluna 2
					if (jogousuario.getJogo().getTpResultadofinal().longValue() == Constantes.JOGO_TIPO_RESULTADO_COLUNA2 &&  jogousuario.getFlColuna2().longValue() == 1) {
						nuPontoscartao++;
					}
				}
				
				/**************************** JOGO TRIPLO ****************************/
				if (jogousuario.getTpJogo().longValue() == Constantes.JOGO_TPJOGO_TRIPLO) {
					//-- Coluna 1
					if (jogousuario.getFlColuna1().longValue() == 1) {
						nuPontoscartao++;
					} else
					//-- Empate
					if (jogousuario.getFlEmpate().longValue() == 1) {
						nuPontoscartao++;
					} else
					//-- Coluna 2
					if (jogousuario.getFlColuna2().longValue() == 1) {
						nuPontoscartao++;
					}
				}
			}
			
			//-- Atualiza Pontos Cartao Usuario
			Usuariodata usuariodata = getUsuariodataDAO().findUsuariodata( jogosusuario.get(0) );
			usuariodata.setNuPontoscartao(nuPontoscartao);
			this.update(usuariodata);
		}
	}
	
	private void processaResultadoListas(Loteca lotecaativa, Data data, List<Lotecausuario> usuarios) {
		try {
			Map<Long, Classifclube> mapClassifclube = this.findAllClassifclubeMap(data);
			for (Lotecausuario lotecausuario : usuarios) {
				List<Clubeusuario> clubeusuarios = this.findAllClubeusuario(lotecausuario);
				long nuPontosLista = 0;
				for (Clubeusuario clubeusuario : clubeusuarios) {
					long nuPosicao = clubeusuario.getNuPosicao();
					Classifclube classifclube = mapClassifclube.get(clubeusuario.getClube().getCdClube());
					long nuClassificacao = classifclube.getNuClassificacao();
					//-- Valida o campeao
					if (nuClassificacao == 1 && nuPosicao == 1) {
						nuPontosLista += 2;
					}
					
					//-- Valida acerto na posicao
					if (nuPosicao < 11 || nuPosicao > 16) {
						if (nuClassificacao == nuPosicao) {
							nuPontosLista += 2;
						}
					}
					
					if (nuPosicao < 11 && nuClassificacao < 11) {
						nuPontosLista++;
					} else if (nuPosicao > 16 && nuClassificacao > 16) {
						nuPontosLista++;
					}
				}
				//-- Atualiza Pontos Listas
				Usuariodata usuariodata = getUsuariodataDAO().findUsuariodata(lotecausuario, data);
				usuariodata.setNuPontoslista(nuPontosLista);
				usuariodata.setNuTotalpontos( usuariodata.getNuPontoscartao() +  nuPontosLista );
				this.update(usuariodata);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atualizaPosicaoJogador(Loteca lotecaativa, Data data) {
		try {
			List<Usuariodata> usuariodatas = getUsuariodataDAO().findAllUsuariodata(lotecaativa, data);
			Collections.sort(usuariodatas, new Comparator<Usuariodata>() {
				@Override
				public int compare(Usuariodata o1, Usuariodata o2) {
					// compara primeiro os pontos totais
					int ok =  o2.getNuTotalpontos().compareTo(o1.getNuTotalpontos());
					// se der empate busca a posicao anterior
					if (ok == 0) {
						ok =  o1.getNuPosicao().compareTo(o2.getNuPosicao());
					}
					return ok;
				}
			});
			long nuPosicao = 0;
			for (Usuariodata ud : usuariodatas) {
				ud.setNuPosicao(++nuPosicao);
				super.update(ud);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void processaResultadosData(Data data) throws ServiceException {
		Loteca lotecaativa = LtcServiceLocator.getInstance().getLotecaService().findLotecaAtiva();
		List<Lotecausuario> usuarios = LtcServiceLocator.getInstance().getLotecaService().findAllLotecausuarioByLoteca(lotecaativa);
		this.processaResultadoCanhotos(lotecaativa, data, usuarios);
		this.processaResultadoListas(lotecaativa, data, usuarios);
		this.atualizaPosicaoJogador(lotecaativa, data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, Classifclube> findAllClassifclubeMap(Data data) throws ServiceException {
		Classifclube cc = new Classifclube();
		cc.setData(data);
		List<Classifclube> classifclubes = (List<Classifclube>) super.findAll(cc);
		Map<Long, Classifclube> retorno = new HashMap<Long, Classifclube>();
		for (Classifclube cfc : classifclubes) {
			retorno.put(cfc.getClube().getCdClube() , cfc);
		}
		return retorno;
	}
	
}
