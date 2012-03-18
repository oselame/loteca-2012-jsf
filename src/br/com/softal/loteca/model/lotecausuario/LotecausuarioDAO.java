package br.com.softal.loteca.model.lotecausuario;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;

public interface LotecausuarioDAO extends GenericDAO<Lotecausuario> {
	
	List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca) throws DaoException;

}
