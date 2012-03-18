package br.com.softal.loteca.service;

import java.util.List;

import br.com.softal.base.service.DefaultService;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public interface LotecaService extends DefaultService {
	
	Loteca findLotecaAtiva();
	List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca);
	
}
