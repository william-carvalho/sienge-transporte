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

import com.sienge.transporte.domain.Transporte;
import com.sienge.transporte.domain.TransporteRodovia;
import com.sienge.transporte.dto.TransporteRodoviaDto;
import com.sienge.transporte.repository.TransporteRepository;
import com.sienge.transporte.resource.TransporteResource;
import com.sienge.transporte.resource.TransporteResourceAssembler;
import com.sienge.transporte.service.TransporteRodoviaService;

@RestController
@RequestMapping("/transportes")
public class TransporteController {

	@Autowired
	TransporteRepository repository;
	
	@Autowired
	TransporteRodoviaService service;
	
	TransporteResourceAssembler assembler = new TransporteResourceAssembler();
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Page<TransporteRodoviaDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(service.list(pageable), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<TransporteResource> get(@PathVariable Long id) {
		Transporte transporte = repository.findOne(id);
		if (transporte != null) {			
			return new ResponseEntity<>(assembler.toResource(transporte), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<List<TransporteRodovia>> create(@RequestBody TransporteRodoviaDto transporteRodoviaDto) {
		List<TransporteRodovia> transporteRodovia = service.create(transporteRodoviaDto);
		if (transporteRodovia != null) {
			return new ResponseEntity<>(transporteRodovia, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<TransporteResource> update(@PathVariable Long id, @RequestBody Transporte transporte) {
		if (transporte != null) {
			transporte.setId(id);
			transporte = repository.save(transporte);
			return new ResponseEntity<>(assembler.toResource(transporte), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<TransporteResource> delete(@PathVariable Long id) {
		Transporte transporte = repository.findOne(id);
		if (transporte != null) {
			repository.delete(transporte);
			return new ResponseEntity<>(assembler.toResource(transporte), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<Page<TransporteRodoviaDto>> findByNome(@PathVariable String descricao, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return new ResponseEntity<>(service.findByDescricaoContaining(descricao, pageable), HttpStatus.OK);
	}
	

}
