package br.com.softal.loteca.model.clubeusuario;

import java.util.List;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.base.service.ServiceException;

public class HbnClubeusuarioDAO extends GenericDAOImpl<Clubeusuario> implements ClubeusuarioDAO {
	
	@Override
	public List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario)
			throws ServiceException {
		String hql = "FROM Clubeusuario x " + 
			"left join fetch x.clube " +
			"left join fetch x.lotecausuario lu " +
			"where lu.nuSeqlotecausuario = " + clubeusuario.getLotecausuario().getNuSeqlotecausuario();
		try {
			return getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
