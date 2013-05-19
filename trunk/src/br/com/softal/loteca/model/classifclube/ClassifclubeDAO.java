 
package br.com.softal.loteca.model.classifclube;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.loteca.Loteca;
import java.util.List;

public interface ClassifclubeDAO extends GenericDAO<Classifclube> {
	
	void deleteClassifclube(Data data) throws DaoException;
	List<Classifclube> findAllClassifclubeAtual(Loteca loteca) throws DaoException;
	
}
