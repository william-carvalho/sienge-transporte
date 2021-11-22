package com.sienge.transporte.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.sienge.transporte.controller.TipoRodoviaController;
import com.sienge.transporte.domain.TipoRodovia;

public class TipoRodoviaResourceAssembler extends ResourceAssemblerSupport<TipoRodovia, TipoRodoviaResource> {
	
	public TipoRodoviaResourceAssembler() {
		super(TipoRodovia.class, TipoRodoviaResource.class);
	}

	@Override
	public TipoRodoviaResource toResource(TipoRodovia tipoRodovia) {
		return new TipoRodoviaResource(tipoRodovia, linkTo(methodOn(TipoRodoviaController.class).get(tipoRodovia.getId())).withSelfRel());
	}
	
	@Override
	protected TipoRodoviaResource instantiateResource(TipoRodovia tipoRodovia) {
		return new TipoRodoviaResource(tipoRodovia);
	}

}