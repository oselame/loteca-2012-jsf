package br.com.softal.loteca.model.loteca;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.loteca.util.Constantes;
import br.com.softal.loteca.util.Enuns.SituacaoLoteca;

public class HbnLotecaDAO extends GenericDAOImpl<Loteca> implements LotecaDAO {
	
	@Override
	public Loteca findLotecaAtiva() {
		String hql = "FROM Loteca x ";
		hql +=  "where x.cdLoteca = (Select max(y.cdLoteca) from Loteca y " + 
				"where y.tpSituacao = " + SituacaoLoteca.ANDAMENTO.longValue() + ")"; //-- Constantes.lOTECA_SITUACAO_CONCLUIDA
		try {
			return (Loteca) getHibernateTemplate().find(hql).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
