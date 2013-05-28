package br.com.softal.loteca.model.data;

import br.com.softal.base.dao.DaoException;
import java.util.List;

import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.loteca.Loteca;

public interface DataDAO extends GenericDAO<Data> {
	
    List<Data> findAllDatasEncerradas(Loteca loteca) throws DaoException;
    List<Data> findAllDatas(Loteca loteca) throws DaoException;
    Data findUltimaDataEncerrada(Loteca loteca) throws DaoException;
	
}
