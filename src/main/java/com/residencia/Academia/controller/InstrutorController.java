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

import com.residencia.Academia.dto.InstrutorDTO;
import com.residencia.Academia.entity.Instrutor;
import com.residencia.Academia.exception.NoSuchElementFoundException;
import com.residencia.Academia.service.InstrutorService;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	private InstrutorService instrutorService;

	@GetMapping
	public ResponseEntity<List<Instrutor>> findAllInstrutor() {
		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
		return new ResponseEntity<>(instrutorList, HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<InstrutorDTO> findInstrutorDTOById(@PathVariable Integer id) {
		if (null == instrutorService.findInstrutorDTOById(id).getIdInstrutor())
			throw new NoSuchElementFoundException("Não foi encontrado Instrutor com o id " + id);
		else
			return new ResponseEntity<>(instrutorService.findInstrutorDTOById(id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if (null == instrutor)
			throw new NoSuchElementFoundException("Não foi encontrado Instrutor com o id " + id);
		else
			return new ResponseEntity<>(instrutor, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody Instrutor instrutor) {
		Instrutor novoInstrutor = instrutorService.saveInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.CREATED);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<InstrutorDTO> saveInstrutorDTO(@RequestBody InstrutorDTO instrutorDTO) {
		InstrutorDTO novoInstrutorDTO = instrutorService.saveInstrutorDTO(instrutorDTO);
		return new ResponseEntity<>(novoInstrutorDTO, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody Instrutor instrutor) {
		if (instrutorService.findInstrutorById(instrutor.getIdInstrutor()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar, não foi encontrado o Instrutor com o id " + instrutor.getIdInstrutor());
		}
		return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstrutor(@PathVariable Integer id) {
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if(null == instrutor)
			throw new NoSuchElementFoundException("Não foi possível encontrar o Instrutor, pois não foi encontrado um Instrutor com o id " + id);
		
		instrutorService.deleteInstrutor(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}