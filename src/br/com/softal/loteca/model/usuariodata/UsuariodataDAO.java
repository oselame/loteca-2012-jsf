package br.com.softal.loteca.model.usuariodata;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.jogousuario.Jogousuario;

public interface UsuariodataDAO extends GenericDAO<Usuariodata> {
	
	Usuariodata findUsuariodata(Jogousuario jogousuario) throws DaoException;

}
