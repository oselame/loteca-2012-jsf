package br.com.softal.loteca.model.jogo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.data.Data;

public class HbnJogoDAO extends GenericDAOImpl<Jogo> implements JogoDAO {

	@Override
	public List<Jogo> findAllJogos(Data data) {
		List<Jogo> lista = new ArrayList<Jogo>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Jogo jo ");
		hql.append("LEFT JOIN FETCH jo.data dt ");
		hql.append("WHERE dt.cdData = :cdData ");
		hql.append("ORDER BY jo.cdJogo asc ");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setLong("cdData", data.getCdData() );
			lista = query.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}

}
