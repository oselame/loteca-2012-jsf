package br.com.softal.loteca.model.usuariodata;

import java.util.ArrayList;
import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HbnUsuariodataDAO extends GenericDAOImpl<Usuariodata> implements UsuariodataDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Usuariodata findUsuariodata(Jogousuario jogousuario) {
		List<Usuariodata> lista = new ArrayList<Usuariodata>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Usuariodata ud ");
		hql.append("LEFT JOIN FETCH ud.lotecausuario lo ");
		hql.append("LEFT JOIN FETCH ud.data dt ");
		hql.append("WHERE lo.nuSeqlotecausuario = :nuSeqlotecausuario ");
		hql.append("AND dt.cdData = :cdData ");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql.toString());
			query.setLong("nuSeqlotecausuario", jogousuario.getLotecausuario().getNuSeqlotecausuario() );
			query.setLong("cdData", jogousuario.getJogo().getData().getCdData() );
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Usuariodata findUsuariodata(Lotecausuario lotecausuario, Data data) {
		List<Usuariodata> lista = new ArrayList<Usuariodata>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Usuariodata ud ");
		hql.append("LEFT JOIN FETCH ud.lotecausuario lo ");
		hql.append("LEFT JOIN FETCH ud.data dt ");
		hql.append("WHERE lo.nuSeqlotecausuario = :nuSeqlotecausuario ");
		hql.append("AND dt.cdData = :cdData ");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql.toString());
			query.setLong("nuSeqlotecausuario", lotecausuario.getNuSeqlotecausuario() );
			query.setLong("cdData", data.getCdData() );
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuariodata> findAllUsuariodata(Loteca lotecaativa, Data data)
			throws DaoException {
		List<Usuariodata> lista = new ArrayList<Usuariodata>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Usuariodata ud ");
		hql.append("LEFT JOIN FETCH ud.lotecausuario lo ");
		hql.append("LEFT JOIN FETCH lo.loteca lot ");
		hql.append("LEFT JOIN FETCH ud.data dt ");
		hql.append("WHERE lot.cdLoteca = :cdLoteca ");
		hql.append("AND dt.cdData = :cdData ");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql.toString());
			query.setLong("cdLoteca", lotecaativa.getCdLoteca() );
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