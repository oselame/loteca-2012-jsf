package br.com.softal.loteca.model.usuariodata;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.histusuariodata.Histusuariodata;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public interface UsuariodataDAO extends GenericDAO<Usuariodata> {
	
	Usuariodata findUsuariodata(Jogousuario jogousuario) throws DaoException;
	Usuariodata findUsuariodata(Lotecausuario lotecausuario, Data data) throws DaoException;
	List<Usuariodata> findAllUsuariodata(Loteca lotecaativa, Data data) throws DaoException;
	List<Usuariodata> findAllUsuarioAtivoPordata(Loteca lotecaativa, Data data) throws DaoException;
	List<Usuariodata> findDadosRankingLotecaAtiva(Loteca lotecaativa) throws DaoException;
	List<Usuariodata> findAllUsuariodataSemAposta(Data data) throws DaoException;
	Boolean existeUsuarioSemAposta(Data data) throws DaoException;
	List<AproveitamentoDTO> findAllAproveitamento(Loteca lotecaativa) throws DaoException;
	List<AproveitamentoDTO> findAllRanking(Long cdLoteca, Long cdData) throws DaoException;
	List<AproveitamentoDTO> findAllCampeoes() throws DaoException;
	
	List<Histusuariodata> findHistoricoUsuarioData(Usuariodata usuariodata) throws DaoException;

}
