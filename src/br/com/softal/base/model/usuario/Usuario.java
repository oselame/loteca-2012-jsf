package br.com.softal.base.model.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.softal.base.model.Entity;
import br.com.softal.base.model.projeto.Projeto;
import br.com.softal.base.model.usuariogrupo.Usuariogrupo;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "esegusuario")
public class Usuario extends Entity {

	@Id
	@Column(name = "cdusuario")
	//@OneToMany(mappedBy = "usuario")	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cdUsuario;
	
	@Column(name = "nmUsuario")
	private String nmUsuario;
	
	@Column(name = "delogin")
	private String deLogin;
	
	@Column(name = "deSenha")
	private String deSenha;

	@Column(name = "deEmail")
	private String deEmail;

	@Column(name = "deEmailpessoal")
	private String deEmailpessoal;

	@Column(name = "flAdm")
	private Long flAdm;

	@Column(name = "flAtivo")
	private Long flAtivo;

	@Column(name = "flForaempresa")
	private Long flForaempresa;

	@Column(name = "flEnviosenha")
	private Long flEnviosenha;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cdprojeto", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	//-- @Cascade(CascadeType.SAVE_UPDATE)
	private Projeto projeto;
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
	//-- @Cascade(CascadeType.ALL)
	private List<Lotecausuario> lotecasusuario;

	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
	//-- @Cascade(CascadeType.ALL)
	private List<Usuariogrupo> usuariogrupos;
	
	public Usuario() {
		super();
	}
	
	public Usuario(Long cdUsuario, String nmUsuario, String deLogin,
			String deSenha, String deEmail, String deEmailpessoal, Long flAdm, Long flAtivo,
			Long flForaempresa, Long flEnviosenha, Long cdProjeto) {
		this();
		this.inicializaRelacionamentos();
		this.cdUsuario = cdUsuario;
		this.nmUsuario = nmUsuario;
		this.deLogin = deLogin;
		this.deSenha = deSenha;
		this.deEmail = deEmail;
		this.deEmailpessoal = deEmailpessoal;
		this.flAdm = flAdm;
		this.flAtivo = flAtivo;
		this.flForaempresa = flForaempresa;
		this.flEnviosenha = flEnviosenha;
		getProjeto().setCdProjeto(cdProjeto);
	}



	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getDeSenha() {
		return deSenha;
	}

	public void setDeSenha(String deSenha) {
		this.deSenha = deSenha;
	}

	public String getDeEmail() {
		return deEmail;
	}

	public void setDeEmail(String deEmail) {
		this.deEmail = deEmail;
	}

	public String getDeEmailpessoal() {
		return deEmailpessoal;
	}

	public void setDeEmailpessoal(String deEmailpessoal) {
		this.deEmailpessoal = deEmailpessoal;
	}

	public Long getFlAdm() {
		return flAdm;
	}

	public void setFlAdm(Long flAdm) {
		this.flAdm = flAdm;
	}
	
	public Long getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(Long flAtivo) {
		this.flAtivo = flAtivo;
	}

	public Long getFlForaempresa() {
		return flForaempresa;
	}

	public void setFlForaempresa(Long flForaempresa) {
		this.flForaempresa = flForaempresa;
	}

	public Long getFlEnviosenha() {
		return flEnviosenha;
	}

	public void setFlEnviosenha(Long flEnviosenha) {
		this.flEnviosenha = flEnviosenha;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String getDeLogin() {
		if (deLogin != null && !deLogin.equalsIgnoreCase("")) {
			return deLogin.toUpperCase();
		}
		return deLogin;
	}

	public void setDeLogin(String deLogin) {
		if (deLogin != null && !deLogin.equalsIgnoreCase("")) {
			this.deLogin = deLogin.toUpperCase();
		} else {
			this.deLogin = deLogin;
		}
	}

	public Long getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(Long cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public List<Lotecausuario> getLotecasusuario() {
		return lotecasusuario;
	}

	public void setLotecasusuario(List<Lotecausuario> lotecasusuario) {
		this.lotecasusuario = lotecasusuario;
	}

	public List<Usuariogrupo> getUsuariogrupos() {
		return usuariogrupos;
	}

	public void setUsuariogrupos(List<Usuariogrupo> usuariogrupos) {
		this.usuariogrupos = usuariogrupos;
	}
	
	@Override
	public void inicializaRelacionamentos() {
		setProjeto(new Projeto());		
		setLotecasusuario(new ArrayList<Lotecausuario>());
		setUsuariogrupos(new ArrayList<Usuariogrupo>());
	}
	
}
