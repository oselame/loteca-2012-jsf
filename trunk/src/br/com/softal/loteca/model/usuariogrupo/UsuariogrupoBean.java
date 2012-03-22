package br.com.softal.loteca.model.usuariogrupo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softal.base.bean.AbstractManegedBean;

@SuppressWarnings("serial")
@ManagedBean(name = "usuariogrupoBean")
@SessionScoped
public class UsuariogrupoBean extends AbstractManegedBean<Usuariogrupo> {
	
	@Override
	protected void initializeEntity() {
		setEntity(new Usuariogrupo());
	}
	
	public UsuariogrupoBean() {
		super();
	}

}
