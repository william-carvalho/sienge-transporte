package com.sienge.transporte.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sienge.transporte.domain.TipoRodovia;

@RepositoryRestResource(exported = false)
public interface TipoRodoviaRepository extends JpaRepository<TipoRodovia, Long> {	

	@Query("select b from TipoRodovia b where UPPER(b.descricao) like %?1% ")
	Page<TipoRodovia> findByDescricaoContaining(String descricao, Pageable pageable);
	
	@Query("select b from TipoRodovia b where UPPER(b.descricao) like %?1% ")
	List<TipoRodovia> findByDescricaoContaining(String descricao);
	
	
}
