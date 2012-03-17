package br.com.softal.loteca.model.loteca;

import br.com.softal.base.service.DefaultServiceImpl;

public class LotecaServiceImpl extends DefaultServiceImpl implements LotecaService {
	
	private HbnLotecaDAO lotecaDAO;

	private HbnLotecaDAO getLotecaDAO() {
		return lotecaDAO;
	}

	public void setLotecaDAO(HbnLotecaDAO lotecaDAO) {
		this.lotecaDAO = lotecaDAO;
	}

	@Override
	public Loteca findLotecaAtiva() {
		return getLotecaDAO().findLotecaAtiva();
	}
	

}
