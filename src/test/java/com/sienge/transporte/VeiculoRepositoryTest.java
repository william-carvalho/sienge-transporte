package com.sienge.transporte;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sienge.transporte.domain.Veiculo;
import com.sienge.transporte.repository.VeiculoRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VeiculoRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	VeiculoRepository repository;
	
	@Test
	public void testSaveVeiculo() throws Exception {
		Veiculo veiculo = Veiculo.builder()
				.nome("DAILY TEST").marca("IVECO").placa("RRR 1234").cor("AZUL").placa("2018").chassi("12314141421").fabricante("TRUCK").build();

		veiculo = repository.save(veiculo);
		
		assertNotNull(veiculo);
		assertNotNull(veiculo.getId());
	}
	
	@Test
	public void testDeleteVeiculo() throws Exception {
		Veiculo veiculo = entityManager.persist(Veiculo.builder()
				.nome("DAILY TEST1").build());
		
		repository.delete(veiculo);		
		veiculo = repository.findOne(veiculo.getId());
		
		assertNull(veiculo);
	}
	
	@Test
	public void testFindByDescricao() throws Exception {
		entityManager.persistAndFlush(Veiculo.builder()
				.nome("DAILY TEST 2").build());
		
		List<Veiculo> veiculos = repository.findByNomeContaining("DAILY TEST 2");
		
		assertNotNull(veiculos);
		assertFalse(veiculos.isEmpty());
		assertEquals("DAILY TEST 2", veiculos.get(0).getNome());
	}
	
	
	

}
