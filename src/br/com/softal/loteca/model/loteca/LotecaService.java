package br.com.softal.loteca.model.loteca;

import br.com.softal.base.service.DefaultService;

public interface LotecaService extends DefaultService {
	
	Loteca findLotecaAtiva();
	
}
