package br.com.softal.loteca.model.clube;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;

public interface ClubeDAO extends GenericDAO<Clube> {
	
	List<Clube> findAllClubeByLoteca(long cdLoteca) throws DaoException;
	Clube findClubePorNome(Clube clube) throws DaoException;
	
}
