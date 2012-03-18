package br.com.softal.loteca.model.lotecausuario;

import java.util.List;

import br.com.softal.base.dao.GenericDAOImpl;

public class HbnLotecausuarioDAO extends GenericDAOImpl<Lotecausuario> implements LotecausuarioDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca) {
		String hql = "FROM Lotecausuario x where x.loteca.cdLoteca = " + cdLoteca;
		return getHibernateTemplate().find(hql);
	}
}
