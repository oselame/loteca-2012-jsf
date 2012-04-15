package br.com.softal.loteca.model.clubeusuario;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;

public class HbnClubeusuarioDAO extends GenericDAOImpl<Clubeusuario> implements ClubeusuarioDAO {
	
	@Override
	public List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws DaoException {
		String hql = " FROM Clubeusuario x " + 
			" left join fetch x.clube cl" +
			" left join fetch x.lotecausuario lu " +
			" where lu.nuSeqlotecausuario = " + clubeusuario.getLotecausuario().getNuSeqlotecausuario() + 
			" order by x.nuPosicao asc";
			//--" order by cl.cdClube asc";
			return getHibernateTemplate().find(hql);
	}	
}
