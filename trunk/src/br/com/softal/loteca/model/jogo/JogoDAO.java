package br.com.softal.loteca.model.jogo;

import java.util.List;

import br.com.softal.base.dao.GenericDAO;
import br.com.softal.loteca.model.data.Data;

public interface JogoDAO extends GenericDAO<Jogo> {
	
	List<Jogo> findAllJogos(Data data);

}
