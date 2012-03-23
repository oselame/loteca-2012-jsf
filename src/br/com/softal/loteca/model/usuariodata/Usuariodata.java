package br.com.softal.loteca.model.usuariodata;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.softal.base.model.Entity;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "eltcusuariodata")
public class Usuariodata extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nusequsuariodata")
	@OneToMany(mappedBy = "usuariodata")
	private long nuSequsuariodata;

	@Column(name = "flapostou")
	private Long flApostou;

	@Column(name = "nutotalpontos")
	private Long nuTotalpontos;

	@Column(name = "flcanhoto")
	private Long flCanhoto;

	@Column(name = "flgeradoaleat")
	private Long flGeradoaleat;

	@Column(name = "nuposicao")
	private Long nuPosicao;

	@Column(name = "nutotallistas")
	private Long nuTotallistas;

	@Column(name = "debytesjogo")
	private String deBytesjogo;

	@Column(name = "flpagou")
	private Long flPagou;

	@Column(name = "nuposicaofinal")
	private Long nuPosicaofinal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nuseqlotecausuario", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Lotecausuario lotecausuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cddata", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Data data;

	public Usuariodata() {
		super();
	}
	
	public Usuariodata(long nuSequsuariodata, Long flApostou,
			Long nuTotalpontos, Long flCanhoto, Long flGeradoaleat,
			Long nuPosicao, Long nuTotallistas, String deBytesjogo,
			Long flPagou, Long nuPosicaofinal, Long nuSeqlotecausuario,
			Long cdData) {
		super();
		this.nuSequsuariodata = nuSequsuariodata;
		this.flApostou = flApostou;
		this.nuTotalpontos = nuTotalpontos;
		this.flCanhoto = flCanhoto;
		this.flGeradoaleat = flGeradoaleat;
		this.nuPosicao = nuPosicao;
		this.nuTotallistas = nuTotallistas;
		this.deBytesjogo = deBytesjogo;
		this.flPagou = flPagou;
		this.nuPosicaofinal = nuPosicaofinal;
		this.inicializaRelacionamentos();
		getLotecausuario().setNuSeqlotecausuario(nuSeqlotecausuario);
		getData().setCdData(cdData);
	}

	@Override
	public void inicializaRelacionamentos() {
		setData(new Data());
		setLotecausuario(new Lotecausuario());
	}

	public long getNuSequsuariodata() {
		return nuSequsuariodata;
	}

	public void setNuSequsuariodata(long nuSequsuariodata) {
		this.nuSequsuariodata = nuSequsuariodata;
	}

	public Long getFlApostou() {
		return flApostou;
	}

	public void setFlApostou(Long flApostou) {
		this.flApostou = flApostou;
	}

	public Long getNuTotalpontos() {
		return nuTotalpontos;
	}

	public void setNuTotalpontos(Long nuTotalpontos) {
		this.nuTotalpontos = nuTotalpontos;
	}

	public Long getFlCanhoto() {
		return flCanhoto;
	}

	public void setFlCanhoto(Long flCanhoto) {
		this.flCanhoto = flCanhoto;
	}

	public Long getFlGeradoaleat() {
		return flGeradoaleat;
	}

	public void setFlGeradoaleat(Long flGeradoaleat) {
		this.flGeradoaleat = flGeradoaleat;
	}

	public Long getNuPosicao() {
		return nuPosicao;
	}

	public void setNuPosicao(Long nuPosicao) {
		this.nuPosicao = nuPosicao;
	}

	public Long getNuTotallistas() {
		return nuTotallistas;
	}

	public void setNuTotallistas(Long nuTotallistas) {
		this.nuTotallistas = nuTotallistas;
	}

	public String getDeBytesjogo() {
		return deBytesjogo;
	}

	public void setDeBytesjogo(String deBytesjogo) {
		this.deBytesjogo = deBytesjogo;
	}

	public Long getFlPagou() {
		return flPagou;
	}

	public void setFlPagou(Long flPagou) {
		this.flPagou = flPagou;
	}

	public Long getNuPosicaofinal() {
		return nuPosicaofinal;
	}

	public void setNuPosicaofinal(Long nuPosicaofinal) {
		this.nuPosicaofinal = nuPosicaofinal;
	}

	public Lotecausuario getLotecausuario() {
		return lotecausuario;
	}

	public void setLotecausuario(Lotecausuario lotecausuario) {
		this.lotecausuario = lotecausuario;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
