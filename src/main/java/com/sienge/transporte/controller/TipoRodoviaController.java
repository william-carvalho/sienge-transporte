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

import com.sienge.transporte.domain.TipoRodovia;
import com.sienge.transporte.repository.TipoRodoviaRepository;
import com.sienge.transporte.resource.TipoRodoviaResource;
import com.sienge.transporte.resource.TipoRodoviaResourceAssembler;

@RestController
@RequestMapping("/tiporodovias")
public class TipoRodoviaController {

	@Autowired
	TipoRodoviaRepository repository;
	
	TipoRodoviaResourceAssembler assembler = new TipoRodoviaResourceAssembler();
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Page<TipoRodovia>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
	}

	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<TipoRodovia> get(@PathVariable Long id) {
		TipoRodovia tipoRodovia = repository.findOne(id);
		if (tipoRodovia != null) {			
			return new ResponseEntity<>(tipoRodovia, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<TipoRodoviaResource> create(@RequestBody TipoRodovia tipoRodovia) {
		tipoRodovia = repository.save(tipoRodovia);
		if (tipoRodovia != null) {
			return new ResponseEntity<>(assembler.toResource(tipoRodovia), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<TipoRodoviaResource> update(@PathVariable Long id, @RequestBody TipoRodovia tipoRodovia) {
		if (tipoRodovia != null) {
			tipoRodovia.setId(id);
			tipoRodovia = repository.save(tipoRodovia);
			return new ResponseEntity<>(assembler.toResource(tipoRodovia), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<TipoRodoviaResource> delete(@PathVariable Long id) {
		TipoRodovia tipoRodovia = repository.findOne(id);
		if (tipoRodovia != null) {
			repository.delete(tipoRodovia);
			return new ResponseEntity<>(assembler.toResource(tipoRodovia), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<Page<TipoRodovia>> findByNome(@PathVariable String descricao, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(repository.findByDescricaoContaining(descricao, pageable), HttpStatus.OK);
	}
	

}
