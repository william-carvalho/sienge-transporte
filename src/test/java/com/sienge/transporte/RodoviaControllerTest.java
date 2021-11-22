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
import com.sienge.transporte.controller.RodoviaController;
import com.sienge.transporte.domain.Rodovia;
import com.sienge.transporte.domain.TipoRodovia;

@RunWith(SpringRunner.class)
@WebMvcTest(RodoviaController.class)
public class RodoviaControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonParser;
	
	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/rodovias"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].id", equalTo(1)))
				.andExpect(jsonPath("$.[0].nome", equalTo("BR-101")))
				.andExpect(jsonPath("$.[1].id", equalTo(2)))
				.andExpect(jsonPath("$.[1].nome", equalTo("BR-282")));
		

	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/rodovias/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.nome", equalTo("BR-101")));
	}

	@Test
	public void testCreate() throws Exception {
		Rodovia rodovia = new Rodovia(6l, "chape", new TipoRodovia(1L));
		mockMvc.perform(post("/rodovias").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonParser.writeValueAsString(rodovia)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo(6)))
				.andExpect(jsonPath("$.nome", equalTo("chape")));
	}
	
}
