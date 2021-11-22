package com.sienge.transporte.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.sienge.transporte.domain.Rodovia;

public class RodoviaResource extends Resource<Rodovia> {
	
	public RodoviaResource(Rodovia rodovia, Link... links) {
		super(rodovia, links);
	}
	
}