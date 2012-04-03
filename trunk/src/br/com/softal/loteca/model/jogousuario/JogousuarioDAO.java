package br.com.softal.loteca.model.jogousuario;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;

public interface JogousuarioDAO extends GenericDAO<Jogousuario> {
	
	List<Jogousuario> findAllJogoUsuarioDataAtiva(Long nuSeqlotecausuario) throws DaoException;
	
}
