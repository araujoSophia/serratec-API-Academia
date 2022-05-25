package com.residencia.Academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.Academia.dto.TurmaDTO;
import com.residencia.Academia.entity.Turma;
import com.residencia.Academia.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	TurmaRepository turmaRepository;

	public List<Turma> findAllTurma() {
		return turmaRepository.findAll();
	}

	public Turma findTurmaById(Integer id) {
		return turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
	}
	
	public TurmaDTO findTurmaDTOById(Integer id) {
		Turma turma = turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
		
		TurmaDTO turmaDTO = new TurmaDTO();
		if(null != turma) {
			turmaDTO = converterEntidadeParaDto(turma);	
		}
		return turmaDTO;
	}

	public Turma saveTurma(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public TurmaDTO saveTurmaDTO(TurmaDTO turmaDTO) {
		Turma turma = converterDtoParaEntidade(turmaDTO);
		Turma novaTurma = turmaRepository.save(turma);
		
		return converterEntidadeParaDto(novaTurma);
	}

	public Turma updateTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public void deleteTurma(Integer id) {
		turmaRepository.deleteById(id);
	}
	/*
	public Boolean deleteTurmaComConferencia(Integer id) {
		if (turmaRepository.findById(id).isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		} else {
			return false;
		}

	}
	*/
	
	private Turma converterDtoParaEntidade(TurmaDTO turmaDTO) {
		Turma turma = new Turma();
		
		turma.setIdTurma(turmaDTO.getIdTurma());
		turma.setHorarioTurma(turmaDTO.getHorarioTurma());
		turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
		turma.setDataInicio(turmaDTO.getDataInicio());
		turma.setDataFim(turmaDTO.getDataFim());
		
		Turma novaTurma = turmaRepository.save(turma);
		
		return novaTurma;
	}

	private TurmaDTO converterEntidadeParaDto(Turma turma) {
		TurmaDTO turmaDTO = new TurmaDTO();
		
		turmaDTO.setIdTurma(turma.getIdTurma());
		turmaDTO.setHorarioTurma(turma.getHorarioTurma());
		turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
		turmaDTO.setDataInicio(turma.getDataInicio());
		turmaDTO.setDataFim(turma.getDataFim());
		
		return turmaDTO;
	}
}