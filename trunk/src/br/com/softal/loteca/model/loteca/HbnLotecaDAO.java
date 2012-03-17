package br.com.softal.loteca.model.loteca;

import br.com.softal.base.dao.GenericDAOImpl;

public class HbnLotecaDAO extends GenericDAOImpl<Loteca> implements LotecaDAO {
	
	@Override
	public Loteca findLotecaAtiva() {
		String hql = "FROM Loteca x where x.flAtiva = 1";
		try {
			return (Loteca) getHibernateTemplate().find(hql).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
