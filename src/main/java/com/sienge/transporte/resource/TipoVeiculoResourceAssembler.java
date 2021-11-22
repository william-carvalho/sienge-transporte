package com.sienge.transporte.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.sienge.transporte.controller.TipoVeiculoController;
import com.sienge.transporte.domain.TipoVeiculo;

public class TipoVeiculoResourceAssembler extends ResourceAssemblerSupport<TipoVeiculo, TipoVeiculoResource> {
	
	public TipoVeiculoResourceAssembler() {
		super(TipoVeiculo.class, TipoVeiculoResource.class);
	}

	@Override
	public TipoVeiculoResource toResource(TipoVeiculo tipoVeiculo) {
		return new TipoVeiculoResource(tipoVeiculo, linkTo(methodOn(TipoVeiculoController.class).get(tipoVeiculo.getId())).withSelfRel());
	}
	
	@Override
	protected TipoVeiculoResource instantiateResource(TipoVeiculo tipoVeiculo) {
		return new TipoVeiculoResource(tipoVeiculo);
	}

}