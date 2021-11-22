package com.sienge.transporte.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.sienge.transporte.domain.Veiculo;

public class VeiculoResource extends Resource<Veiculo> {
	
	public VeiculoResource(Veiculo veiculo, Link... links) {
		super(veiculo, links);
	}
	
}