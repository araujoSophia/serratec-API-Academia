package com.residencia.Academia.dto;

import java.util.List;

public class AtividadeDTO {
	private Integer idAtividade;
	private String nomeAtividade;
	private List<TurmaDTO> turmaDTOList;

	public Integer getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public List<TurmaDTO> getTurmaDTOList() {
		return turmaDTOList;
	}

	public void setTurmaDTOList(List<TurmaDTO> turmaDTOList) {
		this.turmaDTOList = turmaDTOList;
	}

	@Override
	public String toString() {
		return "AtividadeDTO [idAtividade=" + idAtividade + ", nomeAtividade=" + nomeAtividade + ", turmaDTOList="
				+ turmaDTOList + "]";
	}

}
