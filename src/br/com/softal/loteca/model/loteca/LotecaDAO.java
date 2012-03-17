package br.com.softal.loteca.model.loteca;

import br.com.softal.base.dao.GenericDAO;

public interface LotecaDAO extends GenericDAO<Loteca> {
	
	Loteca findLotecaAtiva();

}
