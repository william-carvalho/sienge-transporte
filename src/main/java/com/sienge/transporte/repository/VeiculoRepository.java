package com.sienge.transporte.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sienge.transporte.domain.Veiculo;

@RepositoryRestResource(exported = false)
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {	

	@Query("select b from Veiculo b where UPPER(b.nome) like %?1% ")
	Page<Veiculo> findByNomeContaining(String descricao, Pageable pageable);
	
	@Query("select b from Veiculo b where UPPER(b.nome) like %?1% ")
	List<Veiculo> findByNomeContaining(String descricao);
	
	
}
