package br.com.softal.loteca.model.usuariodata;

import java.sql.ResultSet;
import java.util.Date;

public class AproveitamentoDTO {

	private Long cdData;
	private Date dtData;
	private String nmUsuario;
	
	private Long nuPontoscartao;
	private Long nuPosicaocartoes;
	
	private Long nuPontosrodada;
	private Long nuPosicao;
	
	private Long nuPontosfinal;
	private Long nuPosicaofinal;
	
	public AproveitamentoDTO() {
	}

	public Long getNuPontoscartao() {
		return nuPontoscartao;
	}

	public void setNuPontoscartao(Long nuPontoscartao) {
		this.nuPontoscartao = nuPontoscartao;
	}

	public Long getCdData() {
		return cdData;
	}

	public void setCdData(Long cdData) {
		this.cdData = cdData;
	}

	public Date getDtData() {
		return dtData;
	}

	public void setDtData(Date dtData) {
		this.dtData = dtData;
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public Long getNuPontosrodada() {
		return nuPontosrodada;
	}

	public void setNuPontosrodada(Long nuPontosrodada) {
		this.nuPontosrodada = nuPontosrodada;
	}
	
	
	public Long getNuPontosfinal() {
		return nuPontosfinal;
	}

	public void setNuPontosfinal(Long nuPontosfinal) {
		this.nuPontosfinal = nuPontosfinal;
	}

	public Long getNuPosicaofinal() {
		return nuPosicaofinal;
	}

	public void setNuPosicaofinal(Long nuPosicaofinal) {
		this.nuPosicaofinal = nuPosicaofinal;
	}

	public Long getNuPosicao() {
		return nuPosicao;
	}

	public void setNuPosicao(Long nuPosicao) {
		this.nuPosicao = nuPosicao;
	}
	
	public Long getNuPosicaocartoes() {
		return nuPosicaocartoes;
	}

	public void setNuPosicaocartoes(Long nuPosicaocartoes) {
		this.nuPosicaocartoes = nuPosicaocartoes;
	}

	public static AproveitamentoDTO popule(ResultSet rs) {
		AproveitamentoDTO dto = new AproveitamentoDTO();
		try { dto.setCdData(			rs.getLong("cdData")); 				} catch (Exception e) {}
		try { dto.setDtData(			rs.getDate("dtData")); 				} catch (Exception e) {}
		try { dto.setNmUsuario(			rs.getString("nmUsuario")); 		} catch (Exception e) {}
		try { dto.setNuPontosrodada(	rs.getLong("nuPontosrodada")); 		} catch (Exception e) {}
		try { dto.setNuPosicaocartoes(  rs.getLong("nuPosicaocartoes")); 	} catch (Exception e) {}
		
		try { dto.setNuPontoscartao(	rs.getLong("nuPontoscartao")); 		} catch (Exception e) {}
		try { dto.setNuPosicao(			rs.getLong("nuPosicao")); 			} catch (Exception e) {}
		
		try { dto.setNuPontosfinal(		rs.getLong("nuPontosfinal")); 		} catch (Exception e) {}
		try { dto.setNuPosicaofinal(	rs.getLong("nuPosicaofinal")); 		} catch (Exception e) {}
		return dto;
		
	}

}
