package br.com.softal.loteca.model.usuario;

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
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.projeto.Projeto;
import br.com.softal.loteca.model.usuariogrupo.Usuariogrupo;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcusuario")
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

	@Column(name = "vlDeposito")
	private Long vlDeposito;

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

	/*@Column(name = "cdProjeto")
	private String cdProjeto;*/

/*	@ManyToOne(optional = false, targetEntity = Projeto.class)
	@JoinColumn(name = "cdProjeto", referencedColumnName = "cdProjeto")	*/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cdprojeto", insertable=true, updatable=true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Projeto projeto;
	
/*	@OneToMany(targetEntity = Lotecausuario.class)
	@JoinColumn(name = "cdUsuario", referencedColumnName = "cdUsuario")	*/
	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Lotecausuario> lotecasusuario;

	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Usuariogrupo> usuariogrupos;
	
	public Usuario() {
		super();
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

	public Long getVlDeposito() {
		return vlDeposito;
	}

	public void setVlDeposito(Long vlDeposito) {
		this.vlDeposito = vlDeposito;
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
	
}
