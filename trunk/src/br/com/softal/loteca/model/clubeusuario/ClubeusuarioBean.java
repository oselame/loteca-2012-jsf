package br.com.softal.loteca.model.clubeusuario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.model.usuariodata.Usuariodata;

@SuppressWarnings("serial")
@ManagedBean(name = "clubeusuarioBean")
@RequestScoped
public class ClubeusuarioBean extends AbstractManegedBean<Clubeusuario> {
	
	private Usuariodata usuariodata;
	private Long nuSeqlotecausuario;
	private List<Clubeusuario> clubesclassificados;
	private List<Clubeusuario> clubesrebaixados;
	
	public List<Clubeusuario> getClubesclassificados() {
		return clubesclassificados;
	}

	public void setClubesclassificados(List<Clubeusuario> clubesclassificados) {
		this.clubesclassificados = clubesclassificados;
	}

	public List<Clubeusuario> getClubesrebaixados() {
		return clubesrebaixados;
	}

	public void setClubesrebaixados(List<Clubeusuario> clubesrebaixados) {
		this.clubesrebaixados = clubesrebaixados;
	}

	public Usuariodata getUsuariodata() {
		return usuariodata;
	}

	public void setUsuariodata(Usuariodata usuariodata) {
		this.usuariodata = usuariodata;
		carregaClubeUsuario();
	}
	
	public Long getNuSeqlotecausuario() {
		return nuSeqlotecausuario;
	}

	public void setNuSeqlotecausuario(Long nuSeqlotecausuario) {
		this.nuSeqlotecausuario = nuSeqlotecausuario;
	}

	@Override
	protected void initializeEntity() {
		setEntity(new Clubeusuario());
	}
	
	public void carregaClubeUsuario() {
		try {
			setClubesclassificados(new ArrayList<Clubeusuario>());
			setClubesrebaixados(new ArrayList<Clubeusuario>());
			
			List<Clubeusuario> clubes = LtcServiceLocator.getInstance().getLotecaService().findAllClubeusuario( this.usuariodata.getLotecausuario() );
			for (Clubeusuario cu : clubes) {
				if (cu.getNuPosicao() <= 10) {
					getClubesclassificados().add(cu);
				} else if (cu.getNuPosicao() >= 17) {
					getClubesrebaixados().add(cu);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
