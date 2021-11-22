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
import com.sienge.transporte.controller.VeiculoController;
import com.sienge.transporte.domain.TipoVeiculo;
import com.sienge.transporte.domain.Veiculo;

@RunWith(SpringRunner.class)
@WebMvcTest(VeiculoController.class)
public class VeiculoControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonParser;
	
	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/veiculos"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].id", equalTo(1)))
				.andExpect(jsonPath("$.[0].nome", equalTo("DAILY TEST 2")))
				.andExpect(jsonPath("$.[1].id", equalTo(2)))
				.andExpect(jsonPath("$.[1].nome", equalTo("DAILY TEST")));

	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/veiculos/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.nome", equalTo("DAILY CHASSI 35.10/ 35.13/ 40.13")));
	}

	@Test
	public void testCreate() throws Exception {
		Veiculo veiculos = new Veiculo(20L, "DAILY CHASSI 35.10/ 35.13/ 40.13", "IVECO","QQQ 1234", "VERMELHO", "2010", "1213155DFDAAS", "TRUCK", new TipoVeiculo(1L));
;
		mockMvc.perform(post("/veiculos").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonParser.writeValueAsString(veiculos)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo(20)))
				.andExpect(jsonPath("$.nome", equalTo("DAILY CHASSI 35.10/ 35.13/ 40.13")));
	}
	
}
