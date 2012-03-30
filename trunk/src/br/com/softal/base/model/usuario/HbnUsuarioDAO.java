package br.com.softal.base.model.usuario;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAOImpl;

public class HbnUsuarioDAO extends GenericDAOImpl<Usuario> implements UsuarioDAO {
	
	@Override
	public Usuario findUsuarioByLogin(String deLogin) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add( Restrictions.eq("deLogin", deLogin ) );
		List<Usuario> lista = getHibernateTemplate().findByCriteria( criteria );
		if (lista.size() == 1) {
			return lista.get(0);
		} else {
			return null;
		}
	}
}
