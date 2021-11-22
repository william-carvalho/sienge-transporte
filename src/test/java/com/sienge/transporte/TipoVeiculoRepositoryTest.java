package com.sienge.transporte;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sienge.transporte.domain.TipoVeiculo;
import com.sienge.transporte.repository.TipoVeiculoRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TipoVeiculoRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	TipoVeiculoRepository repository;
	
	@Test
	public void testSaveTipoVeiculo() throws Exception {
		TipoVeiculo tipoVeiculo = TipoVeiculo.builder()
				.descricao("Veiculo ruim").custo(0.50).build();
		
		tipoVeiculo = repository.save(tipoVeiculo);
		
		assertNotNull(tipoVeiculo);
		assertNotNull(tipoVeiculo.getId());
	}
	
	@Test
	public void testDeleteTipoVeiculo() throws Exception {
		TipoVeiculo tipoVeiculo = entityManager.persist(TipoVeiculo.builder()
				.descricao("Veiculo ruim").custo(0.55).build());
		
		repository.delete(tipoVeiculo);		
		tipoVeiculo = repository.findOne(tipoVeiculo.getId());
		
		assertNull(tipoVeiculo);
	}
	
	@Test
	public void testFindByDescricao() throws Exception {
		entityManager.persistAndFlush(TipoVeiculo.builder()
				.descricao("Veiculo ruim").custo(0.50).build());
		
		List<TipoVeiculo> tipoVeiculos = repository.findByDescricaoContaining("Veiculo ruim");
		
		assertNotNull(tipoVeiculos);
		assertFalse(tipoVeiculos.isEmpty());
		assertEquals("Veiculo ruim", tipoVeiculos.get(0).getDescricao());
	}
	
	
	

}
