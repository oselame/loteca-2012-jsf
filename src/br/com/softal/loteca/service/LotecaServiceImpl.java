package br.com.softal.loteca.service;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.model.usuario.HbnUsuarioDAO;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.DefaultServiceImpl;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.model.classifclube.Classifclube;
import br.com.softal.loteca.model.classifclube.HbnClassifclubeDAO;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clube.HbnClubeDAO;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.clubeusuario.HbnClubeusuarioDAO;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.HbnJogousuarioDAO;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.HbnLotecaDAO;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.HbnLotecausuarioDAO;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.util.Constantes;

public class LotecaServiceImpl extends DefaultServiceImpl implements LotecaService {
	
	private HbnLotecaDAO lotecaDAO;
	private HbnLotecausuarioDAO lotecausuarioDAO;
	private HbnClubeDAO clubeDAO;
	private HbnClubeusuarioDAO clubeusuarioDAO;
	private HbnClassifclubeDAO classifclubeDAO;
	private HbnUsuarioDAO usuarioDAO;
	private HbnJogousuarioDAO jogousuarioDAO;
	
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
	public void deleteClassifclube(Data data) throws ServiceException {
		try {
			getClassifclubeDAO().deleteClassifclube(data);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	public void geraClassificacao(Loteca loteca, Data data) throws ServiceException {
		try {
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void processaResultado(Data data) throws ServiceException {
		
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
	

}
