package br.com.softal.loteca.model.classifclube;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.data.Data;

public interface ClassifclubeDAO extends GenericDAO<Classifclube> {
	
	public void deleteClassifclube(Data data) throws DaoException;
	
}
