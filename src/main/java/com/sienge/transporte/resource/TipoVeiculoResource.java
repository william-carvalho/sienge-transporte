package com.sienge.transporte.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.sienge.transporte.domain.TipoVeiculo;

public class TipoVeiculoResource extends Resource<TipoVeiculo> {
	
	public TipoVeiculoResource(TipoVeiculo tipoVeiculo, Link... links) {
		super(tipoVeiculo, links);
	}
	
}