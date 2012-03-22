package br.com.softal.loteca.model.clubeusuario;

import java.util.List;

import br.com.softal.base.dao.GenericDAO;
import br.com.softal.base.service.ServiceException;

public interface ClubeusuarioDAO extends GenericDAO<Clubeusuario> {
	
	List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws ServiceException;
	
}
