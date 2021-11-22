package com.sienge.transporte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.sienge.transporte.domain.TransporteRodovia;

@RepositoryRestResource(exported = false)
public interface TransporteRodoviaRepository extends JpaRepository<TransporteRodovia, Long> {	

	List<TransporteRodovia> findByTransporteId(Long id);
	
	
}
