package com.sienge.transporte.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.sienge.transporte.domain.Transporte;

public class TransporteResource extends Resource<Transporte> {
	
	public TransporteResource(Transporte transporte, Link... links) {
		super(transporte, links);
	}
	
}