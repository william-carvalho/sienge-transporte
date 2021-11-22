package com.sienge.transporte.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sienge.transporte.domain.TipoVeiculo;

@RepositoryRestResource(exported = false)
public interface TipoVeiculoRepository extends JpaRepository<TipoVeiculo, Long> {	

	@Query("select b from TipoVeiculo b where UPPER(b.descricao) like %?1% ")
	Page<TipoVeiculo> findByDescricaoContaining(String descricao, Pageable pageable);
	
	@Query("select b from TipoVeiculo b where UPPER(b.descricao) like %?1% ")
	List<TipoVeiculo> findByDescricaoContaining(String descricao);
	
	
}
