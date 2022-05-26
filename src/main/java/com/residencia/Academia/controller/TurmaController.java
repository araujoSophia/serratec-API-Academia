package com.residencia.Academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.Academia.dto.TurmaDTO;
import com.residencia.Academia.entity.Turma;
import com.residencia.Academia.exception.NoSuchElementFoundException;
import com.residencia.Academia.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<Turma>> findAllTurma() {
		return new ResponseEntity<>(turmaService.findAllTurma(), HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<TurmaDTO> findTurmaDTOById(@PathVariable Integer id) {
		if (null == turmaService.findTurmaDTOById(id).getIdTurma())
			throw new NoSuchElementFoundException("Não foi encontrada Turma com o id " + id);
		else
			return new ResponseEntity<>(turmaService.findTurmaDTOById(id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> findTurmaById(@PathVariable Integer id) {
		Turma turma = turmaService.findTurmaById(id);
		if (null == turma)
			throw new NoSuchElementFoundException("Não foi encontrada Turma com o id " + id);
		else
			return new ResponseEntity<>(turmaService.findTurmaById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma) {
		if (turmaService.findTurmaById(turma.getIdTurma()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar, não foi encontrada a Turma com o id " + turma.getIdTurma());
		}
		return new ResponseEntity<>(turmaService.updateTurma(turma), HttpStatus.OK);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<TurmaDTO> saveTurmaDTO(@RequestBody TurmaDTO turmaDTO) {
		return new ResponseEntity<>(turmaService.saveTurmaDTO(turmaDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}") 
	public ResponseEntity<String> deleteTurma(@PathVariable Integer id) { 
		Turma turma = turmaService.findTurmaById(id);
		if(null == turma)
			throw new NoSuchElementFoundException("Não foi possível encontrar a Turma, pois não foi encontrada uma Turma com o id " + id);
		
		turmaService.deleteTurma(id);
		return new ResponseEntity<>("", HttpStatus.OK); 
	}
	
	/* 
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTurmaComConferencia(@PathVariable Integer id) {
		Boolean verificacao = turmaService.deleteTurmaComConferencia(id);
		if (verificacao)
			return new ResponseEntity<>("", HttpStatus.OK);
		else
			throw new NoSuchElementFoundException(
					"Não foi possível encontrar a Turma, pois não foi encontrada uma Turma com o id " + id);

	}
	*/
}
