package br.com.softal.loteca.service;

import java.util.List;

import br.com.softal.base.service.DefaultService;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public interface LtcService extends DefaultService {
	
	List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca);
	
}
