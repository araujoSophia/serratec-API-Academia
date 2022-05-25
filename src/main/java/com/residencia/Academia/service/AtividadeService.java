package com.residencia.Academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.Academia.dto.AtividadeDTO;
import com.residencia.Academia.dto.TurmaDTO;
import com.residencia.Academia.entity.Atividade;
import com.residencia.Academia.entity.Turma;
import com.residencia.Academia.repository.AtividadeRepository;

@Service
public class AtividadeService {
	@Autowired
	AtividadeRepository atividadeRepository;

	public List<Atividade> findAllAtividade() {
		return atividadeRepository.findAll();
	}
	
	public AtividadeDTO findAtividadeDTOById(Integer id) {
		Atividade atividade = atividadeRepository.findById(id).isPresent() ? atividadeRepository.findById(id).get() : null;
		
		AtividadeDTO atividadeDTO = new AtividadeDTO();
		if(null != atividade) {
			atividadeDTO = converterEntidadeParaDto(atividade);	
		}
		return atividadeDTO;
	}

	public Atividade findAtividadeById(Integer id) {
		return atividadeRepository.findById(id).isPresent() ? atividadeRepository.findById(id).get() : null;
	}

	public Atividade saveAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}
	
	public AtividadeDTO saveAtividadeDTO(AtividadeDTO atividadeDTO) {
		Atividade atividade = converterDtoParaEntidade(atividadeDTO);
		Atividade novaAtividade = atividadeRepository.save(atividade);
		
		return converterEntidadeParaDto(novaAtividade);
	}

	public Atividade updateAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public void deleteAtividade(Integer id) {
		Atividade ativ = atividadeRepository.findById(id).get();
		atividadeRepository.delete(ativ);
	}
	
	public void deleteAtividade(Atividade atividade) {
		atividadeRepository.delete(atividade);
	}
	
	private Atividade converterDtoParaEntidade(AtividadeDTO atividadeDTO) {
		Atividade atividade = new Atividade();
		
		atividade.setIdAtividade(atividadeDTO.getIdAtividade());
		atividade.setNomeAtividade(atividadeDTO.getNomeAtividade());
		
		Atividade novaAtividade = atividadeRepository.save(atividade);
		
		return novaAtividade;
	}

	private AtividadeDTO converterEntidadeParaDto(Atividade atividade) {
		AtividadeDTO atividadeDTO = new AtividadeDTO();
		atividadeDTO.setIdAtividade(atividade.getIdAtividade());
		atividadeDTO.setNomeAtividade(atividade.getNomeAtividade());
		
		List<TurmaDTO> listTurmaDTO = new ArrayList<>();
		if (null != atividade.getTurmaList()) {
			for (Turma turma : atividade.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();
				turmaDTO.setDataFim(turma.getDataFim());
				turmaDTO.setDataInicio(turma.getDataInicio());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());
				turmaDTO.setIdTurma(turma.getIdTurma());
				
				listTurmaDTO.add(turmaDTO);
			}
			atividadeDTO.setTurmaDTOList(listTurmaDTO);
		}
		return atividadeDTO;
	}
	
}