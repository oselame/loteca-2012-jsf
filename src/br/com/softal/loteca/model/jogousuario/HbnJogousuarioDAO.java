package br.com.softal.loteca.model.jogousuario;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.CanhotoDTO;
import br.com.softal.loteca.util.Constantes;
import br.com.softal.loteca.util.Enuns.SituacaoData;

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
			query.setLong("tpSituacao", SituacaoData.ANDAMENTO.longValue()); //-- Constantes.DATA_SITUACAO_EM_ANDAMENTO );
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
	public List<Jogousuario> findAllJogoUsuario(Data data, Lotecausuario lotecausuario) {
		List<Jogousuario> lista = new ArrayList<Jogousuario>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Jogousuario ju ");
		hql.append("LEFT JOIN FETCH ju.lotecausuario lu ");
		hql.append("LEFT JOIN FETCH ju.jogo jo ");
		hql.append("LEFT JOIN FETCH jo.data dt ");
		hql.append("WHERE lu.flAtivo = 1 ");
		hql.append("AND lu.nuSeqlotecausuario = :nuSeqlotecausuario ");
		hql.append("AND dt.cdData = :cdData ");		
		hql.append("order by lu.nuSeqlotecausuario, jo.cdJogo asc");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
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
		return lista;
	}
	
	/*@Override
	public List<Jogousuario> findAllJogoUsuarioSemAposta(Data data) throws DaoException {
		List<Jogousuario> lista = new ArrayList<Jogousuario>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Jogousuario ju ");
		hql.append("LEFT JOIN FETCH ju.lotecausuario lu ");
		hql.append("LEFT JOIN FETCH ju.jogo jo ");
		hql.append("LEFT JOIN FETCH jo.data dt ");
		hql.append("WHERE lu.flAtivo = 1 ");
		hql.append("AND dt.cdData = :cdData ");		
		hql.append("AND EXISTS (FROM Usuariodata ud ");
		hql.append("		    join ud.lotecausuario ulu ");		
		hql.append("		    join ud.data udt ");		
		hql.append("		    where ud.flApostou = 1 ");		
		hql.append("		    and udt.cdData = dt.cdData ");		
		hql.append("		    and ulu.nuSeqlotecausuario = lu.nuSeqlotecausuario) ");		
		hql.append("order by lu.nuSeqlotecausuario, jo.cdJogo asc");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
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
	}*/
	
	@Override
	public List<CanhotoDTO> findCanhotosConcurso(Data data) throws DaoException {
		List<CanhotoDTO> lista = new ArrayList<CanhotoDTO>();
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Jogousuario ju ");
		hql.append("LEFT JOIN FETCH ju.lotecausuario lu ");
		hql.append("LEFT JOIN FETCH lu.usuario us ");
		hql.append("LEFT JOIN FETCH ju.jogo jo ");
		hql.append("LEFT JOIN FETCH jo.data dt ");
		hql.append("WHERE lu.flAtivo = 1 ");
		hql.append("AND dt.cdData = :cdData ");		
		hql.append("order by lu.nuSeqlotecausuario, jo.cdJogo asc");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery( hql.toString() );
			query.setLong("cdData", data.getCdData() );
			List<Jogousuario> jogosusuarios = query.list();
			Long nuSeqlotecausuario = 0l;
			CanhotoDTO dto = null;
			for (Jogousuario ju : jogosusuarios) {
				if (ju.getLotecausuario().getNuSeqlotecausuario() != nuSeqlotecausuario) {
					nuSeqlotecausuario = ju.getLotecausuario().getNuSeqlotecausuario();
					dto = new CanhotoDTO();
					dto.setDeLogin( ju.getLotecausuario().getUsuario().getDeLogin() );
					dto.setCdData( data.getCdData() );
					lista.add(dto);
				}
				int key = ju.getJogo().getCdJogo().intValue();
				switch (key) {
				case 1:
					dto.setFlLinha1Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha1Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha1Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha1Duplo(  	ju.getTpJogo() 		);
					break;
				case 2:
					dto.setFlLinha2Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha2Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha2Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha2Duplo(  	ju.getTpJogo() 		);
					break;
				case 3:
					dto.setFlLinha3Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha3Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha3Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha3Duplo(  	ju.getTpJogo() 		);
					break;
				case 4:
					dto.setFlLinha4Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha4Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha4Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha4Duplo(  	ju.getTpJogo() 		);
					break;
				case 5:
					dto.setFlLinha5Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha5Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha5Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha5Duplo(  	ju.getTpJogo() 		);
					break;
				case 6:
					dto.setFlLinha6Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha6Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha6Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha6Duplo(  	ju.getTpJogo() 		);
					break;
				case 7:
					dto.setFlLinha7Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha7Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha7Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha7Duplo(  	ju.getTpJogo() 		);
					break;
				case 8:
					dto.setFlLinha8Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha8Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha8Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha8Duplo(  	ju.getTpJogo() 		);
					break;
				case 9:
					dto.setFlLinha9Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha9Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha9Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha9Duplo(  	ju.getTpJogo() 		);
					break;
				case 10:
					dto.setFlLinha10Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha10Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha10Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha10Duplo(  	ju.getTpJogo() 		);
					break;
				case 11:
					dto.setFlLinha11Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha11Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha11Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha11Duplo(  	ju.getTpJogo() 		);
					break;
				case 12:
					dto.setFlLinha12Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha12Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha12Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha12Duplo(  	ju.getTpJogo() 		);
					break;
				case 13:
					dto.setFlLinha13Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha13Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha13Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha13Duplo(  	ju.getTpJogo() 		);
					break;
				case 14:
					dto.setFlLinha14Coluna1(	ju.getFlColuna1() 	);
					dto.setFlLinha14Empate( 	ju.getFlEmpate() 	);
					dto.setFlLinha14Coluna2(	ju.getFlColuna2() 	);
					dto.setFlLinha14Duplo(  	ju.getTpJogo() 		);
					break;
				default:
					break;
				}
			}
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
