package br.com.softal.loteca.service;

import java.util.List;
import java.util.Map;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.DefaultService;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.model.classifclube.Classifclube;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clube.ClubeDTO;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.AproveitamentoDTO;
import br.com.softal.loteca.model.usuariodata.CanhotoDTO;
import br.com.softal.loteca.model.usuariodata.Usuariodata;

public interface LotecaService extends DefaultService {
	
	Loteca findLotecaAtiva() throws ServiceException;
	
	Usuario findUsuarioByLogin(String deLogin) throws ServiceException;
	Usuario findUsuarioByLoginEmail(String deLoginEmail) throws ServiceException;
	Usuario findUsuarioByLonginSenha(Usuario usuario) throws ServiceException;
	
	List<Lotecausuario> findAllLotecausuarioByLoteca(Long cdLoteca) throws ServiceException;
	List<Lotecausuario> findAllLotecausuarioByLoteca(Loteca loteca) throws ServiceException;
	Lotecausuario findLotecausuarioAtivo(Usuario usuario) throws ServiceException;
	Lotecausuario findLotecausuarioInscricao(Usuario usuario) throws ServiceException;
	Lotecausuario findLotecausuario(Lotecausuario lotecausuario) throws ServiceException;
	
	List<Clube> findAllClubeByLoteca(long cdLoteca) throws ServiceException;
	List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws ServiceException;
	List<Clubeusuario> findAllClubeusuario(Lotecausuario lotecausuario) throws ServiceException;
	void excluirTodosClubesUsuario(Lotecausuario lotecausuario) throws ServiceException;
	List<Clubeusuario> findAllClubeusuarioByLotecausuario(Lotecausuario lotecausuario) throws ServiceException;
	
	List<AproveitamentoDTO> findAllRanking(Long cdLoteca, Long cdData) throws ServiceException;
	
	List<Data> findAllDatasEncerradas(Loteca loteca) throws ServiceException;
	Data findUltimaDataEncerrada(Loteca loteca) throws ServiceException;
	void deleteClassifclube(Data data) throws ServiceException;
	void processaResultadosData(Data data) throws ServiceException;
	Data findData(Long cdData) throws ServiceException;
	
	void geraClassificacao(Loteca loteca, Data data) throws ServiceException;
	//List<Jogousuario> findAllJogoUsuarioDataEmAndamento(Jogousuario jogousuario) throws ServiceException;
	Data findDataEmAndamentoLotecaAtiva() throws ServiceException;
	List<Jogousuario> findAllJogoUsuarioDataAtiva(Lotecausuario lotecausuario) throws ServiceException;
	void saveAllJogousuario(List<Jogousuario> jogos) throws ServiceException;
	void saveAllJogousuario(List<Jogousuario> jogos, boolean aleatorio) throws ServiceException;
	void gerarJogosusuarios(Data data) throws ServiceException;
	Map<Long, Classifclube> findAllClassifclubeMap(Data data) throws ServiceException;
	
	/*******************************************************************/
	List<Usuariodata> findAllDadosRankingLotecaAtiva() throws ServiceException;
	List<Classifclube> findAllClassifclubeAtual() throws ServiceException;
	
	void saveUsuarioWizard(Usuario usuario, List<Clubeusuario> clubeusuarios) throws ServiceException;
	void saveDadosInscricaoUsuario(Usuario usuario, List<Clubeusuario> clubeusuarios) throws ServiceException;
	List<CanhotoDTO> findCanhotosConcurso(Data data) throws ServiceException;
	
	void gerarJogosAleatoriosUsuarioSemApostas(Data data) throws ServiceException;
	List<Jogousuario> findAllJogoUsuario(Data data, Lotecausuario lotecausuario) throws ServiceException;
	Boolean existeUsuarioSemAposta(Data data) throws ServiceException;
	List<AproveitamentoDTO> findAllAproveitamento(Loteca lotecaativa) throws ServiceException;
	
	List<ClubeDTO> findAllVotosCampeao(long cdLoteca) throws ServiceException;
	List<ClubeDTO> findAllVotosRebaixados(long cdLoteca) throws ServiceException;

}
