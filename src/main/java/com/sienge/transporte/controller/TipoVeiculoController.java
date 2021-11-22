package com.sienge.transporte.controller;

import java.util.List;

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

import com.sienge.transporte.domain.TipoVeiculo;
import com.sienge.transporte.repository.TipoVeiculoRepository;
import com.sienge.transporte.resource.TipoVeiculoResource;
import com.sienge.transporte.resource.TipoVeiculoResourceAssembler;

@RestController
@RequestMapping("/tipoveiculos")
public class TipoVeiculoController {

	@Autowired
	TipoVeiculoRepository repository;
	
	TipoVeiculoResourceAssembler assembler = new TipoVeiculoResourceAssembler();
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Page<TipoVeiculo>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<TipoVeiculo> get(@PathVariable Long id) {
		TipoVeiculo tipoVeiculo = repository.findOne(id);
		if (tipoVeiculo != null) {			
			return new ResponseEntity<>(tipoVeiculo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<TipoVeiculoResource> create(@RequestBody TipoVeiculo tipoVeiculo) {
		tipoVeiculo = repository.save(tipoVeiculo);
		if (tipoVeiculo != null) {
			return new ResponseEntity<>(assembler.toResource(tipoVeiculo), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<TipoVeiculoResource> update(@PathVariable Long id, @RequestBody TipoVeiculo tipoVeiculo) {
		if (tipoVeiculo != null) {
			tipoVeiculo.setId(id);
			tipoVeiculo = repository.save(tipoVeiculo);
			return new ResponseEntity<>(assembler.toResource(tipoVeiculo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<TipoVeiculoResource> delete(@PathVariable Long id) {
		TipoVeiculo tipoVeiculo = repository.findOne(id);
		if (tipoVeiculo != null) {
			repository.delete(tipoVeiculo);
			return new ResponseEntity<>(assembler.toResource(tipoVeiculo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<TipoVeiculoResource>> findByNome(@PathVariable String descricao, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findByDescricaoContaining(descricao, pageable)), HttpStatus.OK);
	}
	

}
