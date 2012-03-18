package br.com.softal.loteca.service;

import java.util.List;

import br.com.softal.base.service.DefaultServiceImpl;
import br.com.softal.loteca.model.loteca.HbnLotecaDAO;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

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
	
	@Override
	public List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca) {
		return null;
	}
	

}
