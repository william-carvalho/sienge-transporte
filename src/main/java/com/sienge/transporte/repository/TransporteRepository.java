package com.sienge.transporte.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sienge.transporte.domain.Transporte;

@RepositoryRestResource(exported = false)
public interface TransporteRepository extends JpaRepository<Transporte, Long> {	

	@Query("select b from Transporte b where UPPER(b.descricao) like %?1% ")
	Page<Transporte> findByDescricao(String descricao, Pageable pageable);
	
	@Query("select b from Transporte b where UPPER(b.descricao) like %?1% ")
	List<Transporte> findByDescricao(String descricao);
	
	
}
