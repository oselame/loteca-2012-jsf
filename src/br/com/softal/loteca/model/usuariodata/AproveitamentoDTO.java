package br.com.softal.loteca.model.usuariodata;

import java.sql.ResultSet;
import java.util.Date;

public class AproveitamentoDTO {

	private Long cdData;
	private Date dtData;
	private String nmUsuario;
	private Long nuPontosrodada;
	
	public AproveitamentoDTO() {
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
	
	public static AproveitamentoDTO popule(ResultSet rs) {
		AproveitamentoDTO dto = new AproveitamentoDTO();
		try { dto.setCdData(rs.getLong("cdData")); } catch (Exception e) {}
		try { dto.setDtData(rs.getDate("dtData")); } catch (Exception e) {}
		try { dto.setNmUsuario(rs.getString("nmUsuario")); } catch (Exception e) {}
		try { dto.setNuPontosrodada(rs.getLong("nuPontosrodada")); } catch (Exception e) {}
		return dto;
		
	}

}