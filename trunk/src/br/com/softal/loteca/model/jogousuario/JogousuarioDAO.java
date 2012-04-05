package br.com.softal.loteca.model.jogousuario;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public interface JogousuarioDAO extends GenericDAO<Jogousuario> {
	
	List<Jogousuario> findAllJogoUsuarioDataAtiva(Lotecausuario lotecausuario) throws DaoException;
	
}
