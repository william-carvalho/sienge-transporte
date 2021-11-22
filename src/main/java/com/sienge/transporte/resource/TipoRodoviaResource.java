package com.sienge.transporte.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.sienge.transporte.domain.TipoRodovia;

public class TipoRodoviaResource extends Resource<TipoRodovia> {
	
	public TipoRodoviaResource(TipoRodovia tipoRodovia, Link... links) {
		super(tipoRodovia, links);
	}
	
}