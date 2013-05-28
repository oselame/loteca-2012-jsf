package br.com.softal.loteca.model.histusuariodata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.usuariodata.Usuariodata;

@javax.persistence.Entity
@Table(name = "eltcHistusuariodata")
public class Histusuariodata extends Entity {
	
	private static final long serialVersionUID = -2668703077287648801L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuSequencial")
	private long nuSequencial;
	
	@Column(name = "deBytesjogo")
	private String deBytesjogo;
	
	@Column(name = "dtCadastro")
	private Date dtCadastro;
	
	@Column(name = "flGeradoaleat")
	private long flGeradoaleat;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nuSequsuariodata", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Usuariodata usuariodata;
	
	@Transient
	private List<Jogousuario> jogousuarios;
	
	public long getNuSequencial() {
		return nuSequencial;
	}

	public void setNuSequencial(long nuSequencial) {
		this.nuSequencial = nuSequencial;
	}

	public String getDeBytesjogo() {
		return deBytesjogo;
	}

	public void setDeBytesjogo(String deBytesjogo) {
		this.deBytesjogo = deBytesjogo;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public long getFlGeradoaleat() {
		return flGeradoaleat;
	}

	public void setFlGeradoaleat(long flGeradoaleat) {
		this.flGeradoaleat = flGeradoaleat;
	}

	public Usuariodata getUsuariodata() {
		return usuariodata;
	}

	public void setUsuariodata(Usuariodata usuariodata) {
		this.usuariodata = usuariodata;
	}
	

	public List<Jogousuario> getJogousuarios() {
		return jogousuarios;
	}

	public void setJogousuarios(List<Jogousuario> jogousuarios) {
		this.jogousuarios = jogousuarios;
	}

	@Override
	public void inicializaRelacionamentos() {
		setUsuariodata(new Usuariodata());		
		setJogousuarios(new ArrayList<Jogousuario>());
	}
	

}
