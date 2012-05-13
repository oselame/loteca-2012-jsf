package br.com.softal.loteca.model.lotecausuario;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.loteca.util.Constantes;

public class HbnLotecausuarioDAO extends GenericDAOImpl<Lotecausuario> implements LotecausuarioDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca) {
		String hql = "FROM Lotecausuario x " +
			    "where x.loteca.cdLoteca = " + cdLoteca;
		return getHibernateTemplate().find(hql);
	}
	
	public Lotecausuario findLotecausuarioAtivo(Usuario usuario) {
		List<Lotecausuario> lista = new ArrayList<Lotecausuario>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT lu ");
		hql.append("FROM Lotecausuario lu ");
		hql.append("LEFT JOIN FETCH lu.loteca lo ");
		hql.append("LEFT JOIN FETCH lu.usuario us ");
		hql.append("WHERE us.cdUsuario = :cdUsuario ");
		hql.append("AND lu.flAtivo = 1");
		hql.append("AND lo.tpSituacao = :tpSituacao");		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setLong("cdUsuario", usuario.getCdUsuario() );
			query.setLong("tpSituacao", Constantes.DATA_SITUACAO_EM_ANDAMENTO );
			lista = query.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (lista.size() > 0) ? lista.get(0) : null;
	}
	
	public Lotecausuario findLotecausuarioInscricao(Usuario usuario) {
		List<Lotecausuario> lista = new ArrayList<Lotecausuario>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT lu ");
		hql.append("FROM Lotecausuario lu ");
		hql.append("LEFT JOIN FETCH lu.loteca lo ");
		hql.append("LEFT JOIN FETCH lu.usuario us ");
		hql.append("WHERE us.cdUsuario = :cdUsuario ");
		hql.append("AND lo.tpSituacao = :tpSituacao");		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setLong("cdUsuario", usuario.getCdUsuario() );
			query.setLong("tpSituacao", Constantes.DATA_SITUACAO_CADASTRAMENTO );
			lista = query.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (lista.size() > 0) ? lista.get(0) : null;
	}
}
