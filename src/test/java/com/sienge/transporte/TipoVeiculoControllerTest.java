package com.sienge.transporte;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sienge.transporte.controller.TipoVeiculoController;
import com.sienge.transporte.domain.TipoVeiculo;

@RunWith(SpringRunner.class)
@WebMvcTest(TipoVeiculoController.class)
public class TipoVeiculoControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonParser;
	
	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/tiporodovias"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].id", equalTo(1)))
				.andExpect(jsonPath("$.[0].descricao", equalTo("Caminhão  baú")))
				.andExpect(jsonPath("$.[1].id", equalTo(2)))
				.andExpect(jsonPath("$.[1].descricao", equalTo("Caminhão  caçamba")));
		

	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/tiporodovias/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.descricao", equalTo("Caminhão  baú")));
	}

	@Test
	public void testCreate() throws Exception {
		TipoVeiculo tipoVeiculo = new TipoVeiculo(6l, "Caminhão  bi trem", 4.00);
		mockMvc.perform(post("/tiporodovias").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonParser.writeValueAsString(tipoVeiculo)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo(6)))
				.andExpect(jsonPath("$.descricao", equalTo("Caminhão  bi trem")));
	}
	
}
