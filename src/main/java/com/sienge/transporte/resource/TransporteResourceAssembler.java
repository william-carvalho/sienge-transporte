package com.sienge.transporte.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.sienge.transporte.controller.TransporteController;
import com.sienge.transporte.domain.Transporte;

public class TransporteResourceAssembler extends ResourceAssemblerSupport<Transporte, TransporteResource> {
	
	public TransporteResourceAssembler() {
		super(Transporte.class, TransporteResource.class);
	}

	@Override
	public TransporteResource toResource(Transporte transporte) {
		return new TransporteResource(transporte, linkTo(methodOn(TransporteController.class).get(transporte.getId())).withSelfRel());
	}
	
	@Override
	protected TransporteResource instantiateResource(Transporte transporte) {
		return new TransporteResource(transporte);
	}

}