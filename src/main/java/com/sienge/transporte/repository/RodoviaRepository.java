package com.sienge.transporte.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sienge.transporte.domain.Rodovia;

@RepositoryRestResource(exported = false)
public interface RodoviaRepository extends JpaRepository<Rodovia, Long> {	

	@Query("select b from Rodovia b where UPPER(b.nome) like %?1% ")
	Page<Rodovia> findByNomeContaining(String nome, Pageable pageable);

	@Query("select b from Rodovia b where UPPER(b.nome) like %?1% ")
	List<Rodovia> findByNomeContaining(String nome);

	
}
