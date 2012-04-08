package br.com.softal.base.model.usuario;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;

public class HbnUsuarioDAO extends GenericDAOImpl<Usuario> implements UsuarioDAO {
	
	@Override
	public Usuario findUsuarioByLogin(String deLogin) throws DaoException {
	/*	DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add( Restrictions.eq("deLogin", deLogin ) );
		criteria.setFetchMode("projeto", FetchMode.EAGER);
		criteria.setFetchMode("usuariogrupos", FetchMode.EAGER);
		criteria.setFetchMode("lotecasusuario", FetchMode.EAGER);
		List<Usuario> lista = getHibernateTemplate().findByCriteria( criteria );
		if (lista.size() == 1) {
			return lista.get(0);
		} else {
			return null;
		}*/
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Usuario u ");
		hql.append("LEFT JOIN FETCH u.projeto ");
		hql.append("WHERE u.deLogin = :deLogin ");
		hql.append("AND u.flAtivo = 1");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setString("deLogin", deLogin );
			List<Usuario> lista = query.list();
			if (lista.size() == 1) {
				Usuario usuario = lista.get(0);
				Hibernate.initialize(usuario.getLotecasusuario());
				Hibernate.initialize(usuario.getUsuariogrupos());
				return usuario;
			} 
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
