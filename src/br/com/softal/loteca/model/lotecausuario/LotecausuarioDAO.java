package br.com.softal.loteca.model.lotecausuario;

import java.util.List;

import br.com.softal.base.dao.DaoException;
import br.com.softal.base.dao.GenericDAO;
import br.com.softal.base.model.usuario.Usuario;

public interface LotecausuarioDAO extends GenericDAO<Lotecausuario> {
	
	List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca) throws DaoException;
	Lotecausuario findLotecausuarioAtivo(Usuario usuario) throws DaoException;
	Lotecausuario findLotecausuarioInscricao(Usuario usuario) throws DaoException;
	Lotecausuario findLotecausuario(Lotecausuario lotecausuario) throws DaoException;

}
