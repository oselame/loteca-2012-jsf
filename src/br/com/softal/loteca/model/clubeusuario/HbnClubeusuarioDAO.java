package br.com.softal.loteca.model.clubeusuario;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.util.Constantes;

public class HbnClubeusuarioDAO extends GenericDAOImpl<Clubeusuario> implements ClubeusuarioDAO {
	
	@Override
	public List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws DaoException {
		String hql = " FROM Clubeusuario x " + 
			" left join fetch x.clube cl" +
			" left join fetch x.lotecausuario lu " +
			" where lu.nuSeqlotecausuario = " + clubeusuario.getLotecausuario().getNuSeqlotecausuario() + 
			" order by x.nuPosicao asc";
			return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<Clubeusuario> findAllClubeusuarioByLotecausuario(Lotecausuario lotecausuario) throws DaoException {
		String hql = " FROM Clubeusuario x " + 
				" left join fetch x.clube cl" +
				" left join fetch x.lotecausuario lu " +
				" where lu.nuSeqlotecausuario = " + lotecausuario.getNuSeqlotecausuario() + 
				" order by x.nuPosicao asc";
		return getHibernateTemplate().find(hql);
	}	
	
	public void excluirTodosClubesUsuario(Lotecausuario lotecausuario) throws DaoException {
		String hql = "DELETE FROM Clubeusuario x " + 
				" where x.lotecausuario.nuSeqlotecausuario = :nuSeqlotecausuario ";
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setLong("nuSeqlotecausuario", lotecausuario.getNuSeqlotecausuario() );
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
