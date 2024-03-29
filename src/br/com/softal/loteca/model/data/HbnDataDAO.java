package br.com.softal.loteca.model.data;

import br.com.softal.base.dao.DaoException;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.util.Constantes;
import br.com.softal.loteca.util.Enuns.SituacaoData;

public class HbnDataDAO extends GenericDAOImpl<Data> implements DataDAO {

    @Override
    public List<Data> findAllDatasEncerradas(Loteca loteca) throws DaoException {
    	List<Data> datas = new ArrayList<Data>();
    	StringBuilder hql = new StringBuilder();
		hql.append("SELECT dt ");
		hql.append("FROM Data dt ");
		hql.append("LEFT JOIN dt.loteca ltc ");
		hql.append("WHERE ltc.cdLoteca = :cdLoteca ");
		hql.append("AND dt.tpSituacao = :tpSituacao ");
		hql.append("ORDER BY dt.cdData desc ");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setLong("cdLoteca", loteca.getCdLoteca() );
			query.setLong("tpSituacao", SituacaoData.CONCLUIDA.longValue()); //-- Constantes.DATA_SITUACAO_CONCLUIDO );
			datas = query.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return datas;
    }
    
    @Override
    public List<Data> findAllDatas(Loteca loteca) throws DaoException {
    	List<Data> datas = new ArrayList<Data>();
    	StringBuilder hql = new StringBuilder();
    	hql.append("SELECT dt ");
    	hql.append("FROM Data dt ");
    	hql.append("LEFT JOIN dt.loteca ltc ");
    	hql.append("WHERE ltc.cdLoteca = :cdLoteca ");
    	hql.append("ORDER BY dt.cdData desc ");
    	Session session = getHibernateTemplate().getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();
    	try {
    		Query query = session.createQuery( hql.toString() );
    		query.setLong("cdLoteca", loteca.getCdLoteca() );
    		datas = query.list();
    		tx.commit();
    	} catch (Exception e) {
    		tx.rollback();
    		e.printStackTrace();
    	} finally {
    		session.close();
    	}
    	return datas;
    }
    
    @Override
    public Data findUltimaDataEncerrada(Loteca loteca) throws DaoException {
    	List<Data> datas = new ArrayList<Data>();
    	StringBuilder hql = new StringBuilder();
    	hql.append("SELECT dt ");
    	hql.append("FROM Data dt ");
    	hql.append("LEFT JOIN fetch dt.jogos jgs ");
    	hql.append("LEFT JOIN dt.loteca ltc ");
    	hql.append("WHERE ltc.cdLoteca = :cdLoteca ");
    	//hql.append("AND dt.tpSituacao = :tpSituacao ");
    	hql.append("AND dt.cdData = (SELECT MAX(dx.cdData) FROM Data dx ");
    	hql.append("				LEFT JOIN dx.loteca ltcx ");
    	hql.append("				 WHERE ltcx.cdLoteca = ltc.cdLoteca ");
    	hql.append("				 AND dx.tpSituacao = :tpSituacao ) ");
    	Session session = getHibernateTemplate().getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();
    	try {
    		Query query = session.createQuery( hql.toString() );
    		query.setLong("cdLoteca", loteca.getCdLoteca() );
    		query.setLong("tpSituacao",  SituacaoData.CONCLUIDA.longValue() ); //-- Constantes.DATA_SITUACAO_CONCLUIDO );
    		datas = query.list();
    		if (datas.size() > 0) {
    			Hibernate.initialize(datas.get(0).getJogos());
        	}
    		tx.commit();
    	} catch (Exception e) {
    		tx.rollback();
    		e.printStackTrace();
    	} finally {
    		session.close();
    	}
    	if (datas.size() > 0) {
    		return datas.get(0);
    	}
    	return null;
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
