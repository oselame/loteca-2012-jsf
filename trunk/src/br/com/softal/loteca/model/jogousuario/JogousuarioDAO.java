package br.com.softal.loteca.model.jogousuario;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.CanhotoDTO;

public interface JogousuarioDAO extends GenericDAO<Jogousuario> {
	
	List<Jogousuario> findAllJogoUsuarioDataAtiva(Lotecausuario lotecausuario) throws DaoException;
	List<Jogousuario> findAllJogoUsuario(Data data, Lotecausuario lotecausuario) throws DaoException;
	List<CanhotoDTO> findCanhotosConcurso(Data data) throws DaoException;
	
}
