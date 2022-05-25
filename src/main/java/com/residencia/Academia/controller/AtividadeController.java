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

import com.residencia.Academia.dto.AtividadeDTO;
import com.residencia.Academia.entity.Atividade;
import com.residencia.Academia.exception.NoSuchElementFoundException;
import com.residencia.Academia.service.AtividadeService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
	@Autowired
	private AtividadeService atividadeService;

	@GetMapping
	public ResponseEntity<List<Atividade>> findAllAtividade() {
		List<Atividade> atividadeList = atividadeService.findAllAtividade();
		return new ResponseEntity<>(atividadeList, HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<AtividadeDTO> findAtividadeDTOById(@PathVariable Integer id) {
		if (null == atividadeService.findAtividadeDTOById(id).getIdAtividade())
			throw new NoSuchElementFoundException("Não foi encontrada Atividade com o id " + id);
		else
			return new ResponseEntity<>(atividadeService.findAtividadeDTOById(id), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> findAtividadeById(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException("Não foi encontrada Atividade com o id " + id);
		else
			return new ResponseEntity<>(atividade, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Atividade> saveAtividade(@RequestBody Atividade atividade) {
		Atividade novaAtividade = atividadeService.saveAtividade(atividade);
		return new ResponseEntity<>(novaAtividade, HttpStatus.CREATED);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<AtividadeDTO> saveAtividadeDTO(@RequestBody AtividadeDTO atividadeDTO) {
		AtividadeDTO novaAtividadeDTO = atividadeService.saveAtividadeDTO(atividadeDTO);
		return new ResponseEntity<>(novaAtividadeDTO, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Atividade> updateAtividade(@RequestBody Atividade atividade) {
		if (atividadeService.findAtividadeById(atividade.getIdAtividade()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar, não foi encontrada a Atividade com o id " + atividade.getIdAtividade());
		}
		return new ResponseEntity<>(atividadeService.updateAtividade(atividade), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAtividade(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if(null == atividade)
			throw new NoSuchElementFoundException("Não foi possível encontrar a Atividade, pois não foi encontrada uma Atividade com o id " + id);
		
		atividadeService.deleteAtividade(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}