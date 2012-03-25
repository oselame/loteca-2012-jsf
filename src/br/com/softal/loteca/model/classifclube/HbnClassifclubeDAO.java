package br.com.softal.loteca.model.classifclube;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.data.Data;

public class HbnClassifclubeDAO extends GenericDAOImpl<Classifclube> implements
		ClassifclubeDAO {

	@SuppressWarnings("unused")
	@Override
	public void deleteClassifclube(Data data) throws DaoException {

		String hql = "DELETE FROM Classifclube x where x.data.cdData = :cdData";
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			int nuRegistrosDeletados = session.createQuery( hql )
					.setLong("cdData", data.getCdData() ) 
					.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
