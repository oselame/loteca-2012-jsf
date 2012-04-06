package br.com.softal.loteca.service;

import java.util.List;

import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.DefaultService;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public interface LotecaService extends DefaultService {
	
	Loteca findLotecaAtiva() throws ServiceException;
	Usuario findUsuarioByLogin(String deLogin) throws ServiceException;
	List<Lotecausuario> findAllLotecausuarioByLoteca(Long cdLoteca) throws ServiceException;
	List<Lotecausuario> findAllLotecausuarioByLoteca(Loteca loteca) throws ServiceException;
	List<Clube> findAllClubeByLoteca(long cdLoteca) throws ServiceException;
	List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws ServiceException;
	void deleteClassifclube(Data data) throws ServiceException;
	void geraClassificacao(Loteca loteca, Data data) throws ServiceException;
	void processaResultadosData(Data data) throws ServiceException;
	//List<Jogousuario> findAllJogoUsuarioDataEmAndamento(Jogousuario jogousuario) throws ServiceException;
	Data findDataEmAndamentoLotecaAtiva() throws ServiceException;
	Lotecausuario findLotecausuarioAtivo(Usuario usuario) throws ServiceException;
	List<Jogousuario> findAllJogoUsuarioDataAtiva(Lotecausuario lotecausuario) throws ServiceException;
	void saveAllJogousuario(List<Jogousuario> jogos) throws ServiceException;
	void saveAllJogousuario(List<Jogousuario> jogos, boolean aleatorio) throws ServiceException;
	void gerarJogosusuarios(Data data) throws ServiceException;
	
}
