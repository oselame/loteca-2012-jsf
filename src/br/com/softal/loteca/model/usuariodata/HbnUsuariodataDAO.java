package br.com.softal.loteca.model.usuariodata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.util.Constantes;


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
		hql.append("   ud.nuPontoscartao, ");
		hql.append("   ud.nuPontoslista, ");
		hql.append("   (SELECT sum(x.nuPontoscartao) "); 
		hql.append("           FROM eltcusuariodata x "); 
		hql.append("           WHERE x.cdData <= ud.cdData "); 
		hql.append("           AND x.nuSeqlotecausuario = ud.nuSeqlotecausuario "); 
		hql.append("           GROUP BY x.nuSeqlotecausuario) AS nuPontosrodada "); 
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
					dto.setNuPontoscartao( rs.getLong("nuPontoscartao") );
					dto.setNuPontosranking( rs.getLong("nuPontosrodada") );
					
					long nuPontosfinal = rs.getLong("nuPontosrodada") + rs.getLong("nuPontoslista");
					dto.setNuPontosfinal( nuPontosfinal );
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
	
	@Override
	public List<Usuariodata> findDadosRankingLotecaAtiva(Loteca lotecaativa) {
		List<Usuariodata> lista = new ArrayList<Usuariodata>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Usuariodata ud ");
		hql.append("LEFT JOIN FETCH ud.lotecausuario lo ");
		hql.append("LEFT JOIN FETCH lo.loteca lot ");
		hql.append("LEFT JOIN FETCH ud.data dt ");
		hql.append("WHERE lot.cdLoteca = :cdLoteca ");
		hql.append("AND lo.flAtivo = 1 ");
		hql.append("AND dt.cdData = (SELECT max(dx.cdData) from Data dx where dx.tpSituacao = :tpSituacao)  ");
		hql.append("order by ud.nuPosicaofinal asc");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql.toString());
			query.setLong("cdLoteca", lotecaativa.getCdLoteca() );
			query.setLong("tpSituacao", Constantes.DATA_SITUACAO_CONCLUIDO );
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
	
	@Override
	public List<Usuariodata> findAllUsuariodataSemAposta(Data data) throws DaoException {
		List<Usuariodata> lista = new ArrayList<Usuariodata>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Usuariodata ud ");
		hql.append("LEFT JOIN FETCH ud.lotecausuario lo ");
		hql.append("LEFT JOIN FETCH lo.loteca lot ");
		hql.append("LEFT JOIN FETCH ud.data dt ");
		hql.append("WHERE lo.flAtivo = 1 ");
		hql.append("AND (ud.flApostou is null or ud.flApostou = 0) ");
		hql.append("AND dt.cdData = :cdData ");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql.toString());
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
	
	@Override
	public Boolean existeUsuarioSemAposta(Data data) throws DaoException {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(ud.flApostou) ");
		hql.append("FROM Usuariodata ud ");
		hql.append("LEFT JOIN ud.lotecausuario lo ");
		hql.append("LEFT JOIN lo.loteca lot ");
		hql.append("LEFT JOIN ud.data dt ");
		hql.append("WHERE lo.flAtivo = 1 ");
		hql.append("AND dt.cdData = :cdData ");
		hql.append("AND (ud.flApostou is null or ud.flApostou = 0) ");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql.toString());
			query.setLong("cdData", data.getCdData() );
			return ((Long) query.uniqueResult()) > 0;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public List<AproveitamentoDTO> findAllAproveitamento(Loteca lotecaativa) throws DaoException {
		List<AproveitamentoDTO> lista = new ArrayList<AproveitamentoDTO>();
		StringBuilder hql = new StringBuilder();
		hql.append("select u.nmUsuario, ud.cdData, d.dtData, ud.nuPontoscartao, ud.nuPosicao          \n"); 
		hql.append("from eltcusuariodata ud                                                           \n"); 
		hql.append("left join eltclotecausuario lu on lu.nuSeqlotecausuario = ud.nuSeqlotecausuario   \n"); 
		hql.append("left join esegusuario u on u.cdUsuario = lu.cdUsuario                             \n"); 
		hql.append("left join eltcdata d on d.cdData = ud.cdData                                      \n"); 
		hql.append("where lu.cdLoteca = ?		                                          			  \n"); 
		hql.append("and d.tpSituacao = ?		                                          			  \n"); 
		hql.append("order by  u.nmUsuario, ud.cdData		                                          \n"); 
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = session.connection();
				pstmt = conn.prepareStatement(hql.toString());
				pstmt.setLong(1, lotecaativa.getCdLoteca());
				pstmt.setLong(2, Constantes.DATA_SITUACAO_CONCLUIDO);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					lista.add( AproveitamentoDTO.popule(rs) );
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
	
	public List<AproveitamentoDTO> findAllRanking(Long cdLoteca, Long cdData) throws DaoException {
		List<AproveitamentoDTO> lista = new ArrayList<AproveitamentoDTO>();
		StringBuilder hql = new StringBuilder();
		hql.append("select   \n"); 
		hql.append("  u.nmUsuario  \n"); 
		hql.append("  ,ud.cdData  \n"); 
		hql.append("  ,d.dtData  \n"); 
		
		hql.append("  ,ud.nuPontoscartao  \n"); 
		hql.append("  ,ud.nuPosicaocartoes  \n"); 
		
		hql.append("  ,ud.nuPontosrodada    \n"); 
		hql.append("  ,ud.nuPosicao         \n"); 
		
		hql.append("  ,ud.nuPontosfinal  \n"); 
		hql.append("  ,ud.nuPosicaofinal   \n"); 
		
		hql.append("from eltcusuariodata ud                                                           \n"); 
		hql.append("left join eltclotecausuario lu on lu.nuSeqlotecausuario = ud.nuSeqlotecausuario   \n"); 
		hql.append("left join esegusuario u on u.cdUsuario = lu.cdUsuario                             \n"); 
		hql.append("left join eltcdata d on d.cdData = ud.cdData                                      \n"); 
		hql.append("where lu.cdLoteca = ?		                                          			  \n"); 
		hql.append("and d.tpSituacao = ?		                                          			  \n"); 
		if (cdData != null) {
			hql.append("and ud.cdData = ?		                                          			  \n"); 
		} else {
			hql.append("and ud.cdData = (select max(cddata) from eltcdata dt where dt.cdLoteca = lu.cdLoteca and dt.tpSituacao = d.tpSituacao) \n");
		}
		
		hql.append("order by  		                                          \n");
		if (cdData != null) {
			hql.append(" 	ud.nuPontoscartao desc, ud.nuPosicaocartoes asc	  \n"); 
		} else {
			hql.append(" 	ud.nuPontosrodada desc, ud.nuPosicao asc	      \n"); 
		}
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = session.connection();
				pstmt = conn.prepareStatement(hql.toString());
				pstmt.setLong(1, cdLoteca);
				pstmt.setLong(2, Constantes.DATA_SITUACAO_CONCLUIDO);
				if (cdData != null) {
					pstmt.setLong(3, cdData);
				}
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					lista.add( AproveitamentoDTO.popule(rs) );
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