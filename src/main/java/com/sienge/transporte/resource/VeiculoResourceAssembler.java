package com.sienge.transporte.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.sienge.transporte.controller.VeiculoController;
import com.sienge.transporte.domain.Veiculo;

public class VeiculoResourceAssembler extends ResourceAssemblerSupport<Veiculo, VeiculoResource> {
	
	public VeiculoResourceAssembler() {
		super(Veiculo.class, VeiculoResource.class);
	}

	@Override
	public VeiculoResource toResource(Veiculo veiculo) {
		return new VeiculoResource(veiculo, linkTo(methodOn(VeiculoController.class).get(veiculo.getId())).withSelfRel());
	}
	
	@Override
	protected VeiculoResource instantiateResource(Veiculo veiculo) {
		return new VeiculoResource(veiculo);
	}

}