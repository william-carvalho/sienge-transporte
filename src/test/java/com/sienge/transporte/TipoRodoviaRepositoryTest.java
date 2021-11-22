package com.sienge.transporte;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sienge.transporte.domain.TipoRodovia;
import com.sienge.transporte.repository.TipoRodoviaRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TipoRodoviaRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	TipoRodoviaRepository repository;
	
	@Test
	public void testSaveTipoRodovia() throws Exception {
		TipoRodovia tipoRodovia = TipoRodovia.builder()
				.descricao("Rodovia ruim").custo(0.50).build();
		
		tipoRodovia = repository.save(tipoRodovia);
		
		assertNotNull(tipoRodovia);
		assertNotNull(tipoRodovia.getId());
	}
	
	@Test
	public void testDeleteTipoRodovia() throws Exception {
		TipoRodovia tipoRodovia = entityManager.persist(TipoRodovia.builder()
				.descricao("Rodovia ruim").custo(0.50).build());
		
		repository.delete(tipoRodovia);		
		tipoRodovia = repository.findOne(tipoRodovia.getId());
		
		assertNull(tipoRodovia);
	}
	
	@Test
	public void testFindByDescricao() throws Exception {
		entityManager.persistAndFlush(TipoRodovia.builder()
				.descricao("Rodovia ruim").custo(0.50).build());
		
		List<TipoRodovia> tipoRodovias = repository.findByDescricaoContaining("Rodovia ruim");
		
		assertNotNull(tipoRodovias);
		assertFalse(tipoRodovias.isEmpty());
		assertEquals("Rodovia ruim", tipoRodovias.get(0).getDescricao());
	}
	
	
	

}
