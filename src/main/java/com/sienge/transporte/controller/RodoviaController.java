package com.sienge.transporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sienge.transporte.domain.Rodovia;
import com.sienge.transporte.repository.RodoviaRepository;
import com.sienge.transporte.resource.RodoviaResource;
import com.sienge.transporte.resource.RodoviaResourceAssembler;

@RestController
@RequestMapping("/rodovias")
public class RodoviaController {

	@Autowired
	RodoviaRepository repository;
	
	RodoviaResourceAssembler assembler = new RodoviaResourceAssembler();

	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Page<Rodovia>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<Rodovia> get(@PathVariable Long id) {
		Rodovia rodovia = repository.findOne(id);
		if (rodovia != null) {			
			return new ResponseEntity<>(rodovia, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<RodoviaResource> create(@RequestBody Rodovia rodovia) {
		rodovia = repository.save(rodovia);
		if (rodovia != null) {
			return new ResponseEntity<>(assembler.toResource(rodovia), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<RodoviaResource> update(@PathVariable Long id, @RequestBody Rodovia rodovia) {
		if (rodovia != null) {
			rodovia.setId(id);
			rodovia = repository.save(rodovia);
			return new ResponseEntity<>(assembler.toResource(rodovia), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<RodoviaResource> delete(@PathVariable Long id) {
		Rodovia rodovia = repository.findOne(id);
		if (rodovia != null) {
			repository.delete(rodovia);
			return new ResponseEntity<>(assembler.toResource(rodovia), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Page<Rodovia>> findByNome(@PathVariable String nome, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(repository.findByNomeContaining(nome, pageable), HttpStatus.OK);
	}

	
}
