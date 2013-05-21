package br.com.softal.loteca.model.clube;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.usuariodata.UsuariodataDTO;
import br.com.softal.loteca.util.Constantes;

public class HbnClubeDAO extends GenericDAOImpl<Clube> implements ClubeDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Clube> findAllClubeByLoteca(long cdLoteca) {
		String hql = "FROM Clube x where x.loteca.cdLoteca = " + cdLoteca;
		try {
			return getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Clube findClubePorNome(Clube clube) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Clube.class);
		criteria.add( Restrictions.eq("loteca.cdLoteca", clube.getLoteca().getCdLoteca()) );
		criteria.add( Restrictions.eq("nmClube", clube.getNmClube()) );
		List<Clube> lista = getHibernateTemplate().findByCriteria( criteria );
		if (lista.size() == 1) {
			return lista.get(0);
		} 
		return null;
	}
	
	@Override
	public List<ClubeDTO> findAllVotosCampeao(long cdLoteca) throws DaoException {
		List<ClubeDTO> lista = new ArrayList<ClubeDTO>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c.nmClube, count(*) as votos                  \n");
		hql.append("FROM eltcclubeusuario cu                             \n");
		hql.append("JOIN eltclotecausuario lu ON                         \n");
		hql.append("	lu.nuSeqlotecausuario = cu.nuSeqlotecausuario    \n");
		hql.append("JOIN eltcclube c ON                                  \n");
		hql.append("	c.nuSeqclube = cu.nuSeqclube                     \n");
		hql.append("WHERE lu.cdLoteca = ?                                \n");
		hql.append("AND cu.nuPosicao = 1                                 \n");
		hql.append("AND lu.flAtivo = 1                                   \n");		
		hql.append("GROUP BY c.nmClube                                   \n");
		hql.append("ORDER BY 2 DESC		                                 \n");
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
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ClubeDTO dto = new ClubeDTO();
					dto.setNmClube( rs.getString("nmClube") );
					dto.setNuVotos( rs.getLong("votos") );
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
	public List<ClubeDTO> findAllVotosRebaixados(long cdLoteca) throws DaoException {
		List<ClubeDTO> lista = new ArrayList<ClubeDTO>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c.nmClube, count(*) as votos                  \n");
		hql.append("FROM eltcclubeusuario cu                             \n");
		hql.append("JOIN eltclotecausuario lu ON                         \n");
		hql.append("	lu.nuSeqlotecausuario = cu.nuSeqlotecausuario    \n");
		hql.append("JOIN eltcclube c ON                                  \n");
		hql.append("	c.nuSeqclube = cu.nuSeqclube                     \n");
		hql.append("WHERE lu.cdLoteca = ?                                \n");
		hql.append("AND lu.flAtivo = 1                                   \n");
		hql.append("AND cu.nuPosicao in (17, 18, 19, 20)                 \n");
		hql.append("GROUP BY c.nmClube                                   \n");
		hql.append("ORDER BY 2 DESC		                                 \n");
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
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ClubeDTO dto = new ClubeDTO();
					dto.setNmClube( rs.getString("nmClube") );
					dto.setNuVotos( rs.getLong("votos") );
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
