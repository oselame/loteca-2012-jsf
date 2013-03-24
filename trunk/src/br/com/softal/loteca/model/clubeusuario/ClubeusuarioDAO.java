package br.com.softal.loteca.model.clubeusuario;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public interface ClubeusuarioDAO extends GenericDAO<Clubeusuario> {
	
	List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws DaoException;

	void excluirTodosClubesUsuario(Lotecausuario lotecausuario)  throws DaoException;

	List<Clubeusuario> findAllClubeusuarioByLotecausuario(Lotecausuario lotecausuario) throws DaoException;
	
}
