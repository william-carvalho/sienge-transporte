package com.sienge.transporte;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sienge.transporte.domain.Transporte;
import com.sienge.transporte.repository.TransporteRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransporteRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	TransporteRepository repository;
	
	@Test
	public void testSaveTransporte() throws Exception {
		Transporte transporte = Transporte.builder()
				.descricao("Rodovia ruim").custo(0.50).build();
		
		transporte = repository.save(transporte);
		
		assertNotNull(transporte);
		assertNotNull(transporte.getId());
	}
	
	@Test
	public void testDeleteTransporte() throws Exception {
		Transporte transporte = entityManager.persist(Transporte.builder()
				.descricao("Café").custo(0.50).build());
		
		repository.delete(transporte);		
		transporte = repository.findOne(transporte.getId());
		
		assertNull(transporte);
	}
	
	@Test
	public void testFindByDescricao() throws Exception {
		entityManager.persistAndFlush(Transporte.builder()
				.descricao("Café").custo(0.50).build());
		
		List<Transporte> transportes = repository.findByDescricao("Café");
		
		assertNotNull(transportes);
		assertFalse(transportes.isEmpty());
		assertEquals("Café", transportes.get(0).getDescricao());
	}
	
	
	

}
