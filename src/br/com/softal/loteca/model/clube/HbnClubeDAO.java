package br.com.softal.loteca.model.clube;

import java.util.List;

import br.com.softal.base.dao.GenericDAOImpl;

public class HbnClubeDAO extends GenericDAOImpl<Clube> implements ClubeDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Clube> findAllClubeByLoteca(long cdLoteca) {
		String hql = "FROM Clube x where x.loteca.cdLoteca = " + cdLoteca;
		try {
			return getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
