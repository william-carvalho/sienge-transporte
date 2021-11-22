package com.sienge.transporte.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.sienge.transporte.controller.RodoviaController;
import com.sienge.transporte.domain.Rodovia;

public class RodoviaResourceAssembler extends ResourceAssemblerSupport<Rodovia, RodoviaResource> {
	
	public RodoviaResourceAssembler() {
		super(Rodovia.class, RodoviaResource.class);
	}

	@Override
	public RodoviaResource toResource(Rodovia rodovia) {
		return new RodoviaResource(rodovia, linkTo(methodOn(RodoviaController.class).get(rodovia.getId())).withSelfRel());
	}
	
	@Override
	protected RodoviaResource instantiateResource(Rodovia rodovia) {
		return new RodoviaResource(rodovia);
	}

}