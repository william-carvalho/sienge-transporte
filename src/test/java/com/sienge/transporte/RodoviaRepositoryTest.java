package com.sienge.transporte;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sienge.transporte.domain.Rodovia;
import com.sienge.transporte.repository.RodoviaRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RodoviaRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	RodoviaRepository repository;
	
	@Test
	public void testSaveRodovia() throws Exception {
		Rodovia rodovia = Rodovia.builder()
				.nome("BR-1001").build();
		
		rodovia = repository.save(rodovia);
		
		assertNotNull(rodovia);
		assertNotNull(rodovia.getId());
	}
	
	@Test
	public void testDeleteRodovia() throws Exception {
		Rodovia rodovia = entityManager.persist(Rodovia.builder()
				.nome("BR-1001").build());
		
		repository.delete(rodovia);		
		rodovia = repository.findOne(rodovia.getId());
		
		assertNull(rodovia);
	}
	
	@Test
	public void testFindByDescricao() throws Exception {
		entityManager.persistAndFlush(Rodovia.builder()
				.nome("BR-1001").build());
		
		List<Rodovia> rodovias = repository.findByNomeContaining("BR-1001");
		
		assertNotNull(rodovias);
		assertFalse(rodovias.isEmpty());
		assertEquals("BR-1001", rodovias.get(0).getNome());
	}
	
	
	

}
