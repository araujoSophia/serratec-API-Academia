package com.residencia.Academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.Academia.dto.InstrutorDTO;
import com.residencia.Academia.dto.TurmaDTO;
import com.residencia.Academia.entity.Instrutor;
import com.residencia.Academia.entity.Turma;
import com.residencia.Academia.repository.InstrutorRepository;

@Service
public class InstrutorService {
	@Autowired
	InstrutorRepository instrutorRepository;

	public List<Instrutor> findAllInstrutor() {
		return instrutorRepository.findAll();
	}

	public Instrutor findInstrutorById(Integer id) {
		// return instrutorRepository.findById(id).isPresent() ?
		// instrutorRepository.findById(id).get() : null;/
		return instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get() : null;
	}

	public InstrutorDTO findInstrutorDTOById(Integer id) {
		Instrutor instrutor = instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get()
				: null;

		InstrutorDTO instrutorDTO = new InstrutorDTO();
		if (null != instrutor) {
			instrutorDTO = converterEntidadeParaDto(instrutor);
		}
		return instrutorDTO;
	}

	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public InstrutorDTO saveInstrutorDTO(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = converterDtoParaEntidade(instrutorDTO);
		Instrutor novoInstrutor = instrutorRepository.save(instrutor);

		return converterEntidadeParaDto(novoInstrutor);
	}

	public Instrutor updateInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public void deleteInstrutor(Integer id) {
		Instrutor inst = instrutorRepository.findById(id).get();
		instrutorRepository.delete(inst);
	}

	public void deleteInstrutor(Instrutor instrutor) {
		instrutorRepository.delete(instrutor);
	}

	private Instrutor converterDtoParaEntidade(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = new Instrutor();

		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setRgInstrutor(instrutorDTO.getRgInstrutor());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());

		Instrutor novoInstrutor = instrutorRepository.save(instrutor);

		return novoInstrutor;
	}

	private InstrutorDTO converterEntidadeParaDto(Instrutor instrutor) {
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
		instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
		instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
		instrutorDTO.setRgInstrutor(instrutor.getRgInstrutor());
		instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());

		List<TurmaDTO> listTurmaDTO = new ArrayList<>();
		if (null != instrutor.getTurmaList()) {
			for (Turma turma : instrutor.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();
				turmaDTO.setDataFim(turma.getDataFim());
				turmaDTO.setDataInicio(turma.getDataInicio());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());
				turmaDTO.setIdTurma(turma.getIdTurma());

				listTurmaDTO.add(turmaDTO);
			}
			instrutorDTO.setTurmaDTOList(listTurmaDTO);
		}
		return instrutorDTO;
	}
}