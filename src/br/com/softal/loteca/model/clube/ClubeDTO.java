package br.com.softal.loteca.model.clube;

import java.sql.ResultSet;


public class ClubeDTO {

	private String nmClube;
	private Long nuVotos;

	public String getNmClube() {
		return nmClube;
	}

	public void setNmClube(String nmClube) {
		this.nmClube = nmClube;
	}

	public Long getNuVotos() {
		return nuVotos;
	}

	public void setNuVotos(Long nuVotos) {
		this.nuVotos = nuVotos;
	}
	
}
