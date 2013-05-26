package br.com.softal.loteca.dto;

import java.util.List;

public class ComparacaoDto {

	private String nmUsuario;
	private Long cdJogo;
	private String deJogo;
	private Long tpResultadofinal;
	private Long tpJogo;
	private Long flColuna1;
	private Long flEmpate;
	private Long flColuna2;

	private List<ComparacaoDto> jogos;
	
	
	public ComparacaoDto() {
	}
	
	public ComparacaoDto(Long cdJogo, String deJogo, Long tpResultadofinal) {
		this();
		this.cdJogo = cdJogo;
		this.deJogo = deJogo;
		this.tpResultadofinal = tpResultadofinal;
	}
	
	public ComparacaoDto(String nmUsuario, Long tpJogo, Long flColuna1, Long flEmpate, Long flColuna2) {
		this();
		this.nmUsuario = nmUsuario;
		this.tpJogo = tpJogo;
		this.flColuna1 = flColuna1;
		this.flEmpate = flEmpate;
		this.flColuna2 = flColuna2;
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public Long getCdJogo() {
		return cdJogo;
	}

	public void setCdJogo(Long cdJogo) {
		this.cdJogo = cdJogo;
	}

	public String getDeJogo() {
		return deJogo;
	}

	public void setDeJogo(String deJogo) {
		this.deJogo = deJogo;
	}

	public Long getTpResultadofinal() {
		return tpResultadofinal;
	}

	public void setTpResultadofinal(Long tpResultadofinal) {
		this.tpResultadofinal = tpResultadofinal;
	}

	public Long getFlColuna1() {
		return flColuna1;
	}

	public void setFlColuna1(Long flColuna1) {
		this.flColuna1 = flColuna1;
	}

	public Long getFlEmpate() {
		return flEmpate;
	}

	public void setFlEmpate(Long flEmpate) {
		this.flEmpate = flEmpate;
	}

	public Long getFlColuna2() {
		return flColuna2;
	}

	public void setFlColuna2(Long flColuna2) {
		this.flColuna2 = flColuna2;
	}

	public List<ComparacaoDto> getJogos() {
		return jogos;
	}

	public void setJogos(List<ComparacaoDto> jogos) {
		this.jogos = jogos;
	}

	public Long getTpJogo() {
		return tpJogo;
	}

	public void setTpJogo(Long tpJogo) {
		this.tpJogo = tpJogo;
	}
	
	public String getTpResultadofinaldesc() {
		if (getTpResultadofinal() == 0) {
			return "Empate";
		} else if (getTpResultadofinal() == 1) {
			return "Coluna 1";
		} 
		return "Coluna 2";
	}
	

}
