package br.com.softal.loteca.model.classifclube;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.util.Constantes;
import br.com.softal.loteca.util.Enuns.SituacaoData;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	
	@Override
	public List<Classifclube> findAllClassifclubeAtual(Loteca loteca) throws DaoException {
		List<Classifclube> lista = new ArrayList<Classifclube>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Classifclube ud ");
		hql.append("LEFT JOIN FETCH ud.data dt ");
		hql.append("LEFT JOIN FETCH ud.clube cl ");
		hql.append("WHERE dt.cdData = (SELECT max(dx.cdData) from Data dx where dx.tpSituacao = :tpSituacao)  ");
                hql.append("AND cl.loteca.cdLoteca = :cdLoteca ");
		hql.append("order by ud.nuClassificacao asc");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
                    Query query = session.createQuery(hql.toString());
                    query.setLong("tpSituacao", SituacaoData.CONCLUIDA.longValue() ); //-- Constantes.DATA_SITUACAO_CONCLUIDO );
                    query.setLong("cdLoteca", loteca.getCdLoteca() );
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
