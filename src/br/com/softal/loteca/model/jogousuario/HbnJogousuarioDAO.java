package br.com.softal.loteca.model.jogousuario;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.util.Constantes;

public class HbnJogousuarioDAO extends GenericDAOImpl<Jogousuario> implements JogousuarioDAO {
	
	@Override
	public List<Jogousuario> findAllJogoUsuarioDataAtiva(Lotecausuario lotecausuario) {
		List<Jogousuario> lista = new ArrayList<Jogousuario>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Jogousuario ju ");
		hql.append("LEFT JOIN FETCH ju.lotecausuario lu ");
		hql.append("LEFT JOIN FETCH ju.jogo jo ");
		hql.append("LEFT JOIN FETCH jo.data dt ");
		hql.append("WHERE lu.nuSeqlotecausuario = :nuSeqlotecausuario ");
		hql.append("AND lu.flAtivo = 1");
		hql.append("AND dt.tpSituacao = :tpSituacao");		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setLong("nuSeqlotecausuario", lotecausuario.getNuSeqlotecausuario() );
			query.setLong("tpSituacao", Constantes.DATA_SITUACAO_EM_ANDAMENTO );
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
