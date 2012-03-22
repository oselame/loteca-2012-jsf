package br.com.softal.loteca.service;

import java.util.List;

import br.com.softal.base.service.DefaultServiceImpl;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clube.HbnClubeDAO;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.clubeusuario.HbnClubeusuarioDAO;
import br.com.softal.loteca.model.loteca.HbnLotecaDAO;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.HbnLotecausuarioDAO;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public class LotecaServiceImpl extends DefaultServiceImpl implements LotecaService {
	
	private HbnLotecaDAO lotecaDAO;
	private HbnLotecausuarioDAO lotecausuarioDAO;
	private HbnClubeDAO clubeDAO;
	private HbnClubeusuarioDAO clubeusuarioDAO;

	private HbnLotecaDAO getLotecaDAO() {
		return lotecaDAO;
	}

	public void setLotecaDAO(HbnLotecaDAO lotecaDAO) {
		this.lotecaDAO = lotecaDAO;
	}

	private HbnLotecausuarioDAO getLotecausuarioDAO() {
		return lotecausuarioDAO;
	}

	public void setLotecausuarioDAO(HbnLotecausuarioDAO lotecausuarioDAO) {
		this.lotecausuarioDAO = lotecausuarioDAO;
	}

	private HbnClubeDAO getClubeDAO() {
		return clubeDAO;
	}

	public void setClubeDAO(HbnClubeDAO clubeDAO) {
		this.clubeDAO = clubeDAO;
	}
	
	private HbnClubeusuarioDAO getClubeusuarioDAO() {
		return clubeusuarioDAO;
	}

	public void setClubeusuarioDAO(HbnClubeusuarioDAO clubeusuarioDAO) {
		this.clubeusuarioDAO = clubeusuarioDAO;
	}

	@Override
	public Loteca findLotecaAtiva() {
		return getLotecaDAO().findLotecaAtiva();
	}
	
	@Override
	public List<Lotecausuario> findAllLotecausuarioByLoteca(long cdLoteca) {
		return getLotecausuarioDAO().findAllLotecausuarioByLoteca(cdLoteca);
	}
	
	@Override
	public List<Clube> findAllClubeByLoteca(long cdLoteca) throws ServiceException {
		return getClubeDAO().findAllClubeByLoteca(cdLoteca);
	}
	
	@Override
	public List<Clubeusuario> findAllClubeusuario(Clubeusuario clubeusuario) throws ServiceException {
		return getClubeusuarioDAO().findAllClubeusuario(clubeusuario);
	}

}
