package com.sienge.transporte.controller;

import com.sienge.transporte.service.VeiculoService;
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

import com.sienge.transporte.domain.Veiculo;
import com.sienge.transporte.repository.VeiculoRepository;
import com.sienge.transporte.resource.VeiculoResource;
import com.sienge.transporte.resource.VeiculoResourceAssembler;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	VeiculoService veiculoService;


	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Page<Veiculo>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(veiculoService.findAll(pageable), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> get(@PathVariable Long id) {
		Veiculo veiculo = veiculoService.findOne(id);
		if (veiculo != null) {			
			return new ResponseEntity<>(veiculo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<VeiculoResource> create(@RequestBody Veiculo veiculo) {
		if (veiculo != null) {
			return new ResponseEntity<>(veiculoService.save(veiculo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<VeiculoResource> update(@PathVariable Long id, @RequestBody Veiculo veiculo) {
		if (veiculo != null) {
			return new ResponseEntity<>(veiculoService.update(id, veiculo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (id != null) {
			veiculoService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Page<Veiculo>> findByNome(@PathVariable String nome, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(veiculoService.findByNomeContaining(nome, pageable), HttpStatus.OK);
	}
	
}
