package br.com.softal.loteca.model.data;

import br.com.softal.base.dao.DaoException;
import java.util.List;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.loteca.Loteca;

public class HbnDataDAO extends GenericDAOImpl<Data> implements DataDAO {

    @Override
    public List<Data> findAllDatasEncerradas(Loteca loteca) throws DaoException {
        String hql = "FROM Data x " + 
			"left join fetch x.clube " +
			"left join fetch x.lotecausuario lu ";
		try {
			return getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
//	@Override
//	public List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario)
//			throws ServiceException {
//		String hql = "FROM Clubeusuario x " + 
//			"left join fetch x.clube " +
//			"left join fetch x.lotecausuario lu " +
//			"where lu.nuSeqlotecausuario = " + clubeusuario.getLotecausuario().getNuSeqlotecausuario();
//		try {
//			return getHibernateTemplate().find(hql);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}	
    
    
}
