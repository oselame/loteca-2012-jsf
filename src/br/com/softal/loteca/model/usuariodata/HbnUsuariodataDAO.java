package br.com.softal.loteca.model.usuariodata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.util.Constantes;

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
	
	public List<UsuariodataDTO> findAllUsuariodataFinal(Loteca lotecaativa, Data data) throws DaoException {
		List<UsuariodataDTO> lista = new ArrayList<UsuariodataDTO>();
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT "); 
		hql.append("   lu.nuSeqlotecausuario, ");
		hql.append("   ud.cdData, ");
		hql.append("   (SELECT sum(x.nuPontosrodada) "); 
		hql.append("           FROM eltcusuariodata x "); 
		hql.append("           WHERE x.cdData <= ud.cdData "); 
		hql.append("           AND x.nuSeqlotecausuario = ud.nuSeqlotecausuario "); 
		hql.append("           GROUP BY x.nuSeqlotecausuario) AS nuPontosfinal "); 
		hql.append("  FROM eltcusuariodata ud "); 
		hql.append("  join eltclotecausuario lu on "); 
		hql.append("     ud.nuSeqlotecausuario = lu.nuSeqlotecausuario ");
		hql.append("     and lu.flAtivo = 1 "); 
		hql.append("  join eltcloteca l on ");  
		hql.append("   	lu.cdLoteca = l.cdLoteca ");  
		hql.append("  	and l.tpSituacao = " + Constantes.lOTECA_SITUACAO_ANDAMENTO); 	
		hql.append(" WHERE ud.cdData = ? ");
		hql.append(" ORDER BY ud.cdData asc, 3 DESC, ud.nuPosicaofinal asc "); 
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = session.connection();
				pstmt = conn.prepareStatement(hql.toString());
				pstmt.setLong(1, data.getCdData());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					UsuariodataDTO dto = new UsuariodataDTO();
					dto.setCdData( data.getCdData() );
					dto.setNuSeqlotecausuario( rs.getLong("nuSeqlotecausuario") );
					dto.setNuPontosfinal( rs.getLong("nuPontosfinal") );
					lista.add(dto);
				}
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			} finally {
				rs.close();
				pstmt.close();
				conn.close();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
