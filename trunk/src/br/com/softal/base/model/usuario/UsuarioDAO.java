package br.com.softal.base.model.usuario;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	Usuario findUsuarioByLogin(String deLogin) throws DaoException;

}
