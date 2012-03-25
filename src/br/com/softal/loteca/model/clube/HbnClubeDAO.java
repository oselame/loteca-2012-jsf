package br.com.softal.loteca.model.clube;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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
	
	public Clube findClubePorNome(Clube clube) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Clube.class);
		criteria.add( Restrictions.eq("loteca.cdLoteca", clube.getLoteca().getCdLoteca()) );
		criteria.add( Restrictions.eq("nmClube", clube.getNmClube()) );
		List<Clube> lista = getHibernateTemplate().findByCriteria( criteria );
		if (lista.size() == 1) {
			return lista.get(0);
		} 
		return null;
	}
	
}
