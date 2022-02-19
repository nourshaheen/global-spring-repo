package com.global.book.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.book.dto.AutherDto;
import com.global.book.entity.Auther;
import com.global.book.service.AutherService;

import lombok.extern.log4j.Log4j2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;




@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@Log4j2
public class AutherControllerTest {

//	@Autowired
//	TestRestTemplate restTemplate;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	AutherService autherService;
	
	@BeforeAll
	public void initMethod () {
	
		log.info("==== >> init method");
		
     Optional<Auther> autherParam = Optional.of(new Auther("Ali", "19.2.125.52", "ali@gmail.com", 0, null));
		
		Mockito.when(autherService.findByEmail(Mockito.anyString())).thenReturn(autherParam);
		
	}
	
//	@Test
//	public void findByEmailNotFoundTest() throws Exception {
//		
//		String email = "ali@gmail.com";
//		
//		mockMvc.perform(get("/auther/email/{email}", email)
//	            .contentType("application/json"))
////	            .param("sendWelcomeMail", "true")
////	            .content(objectMapper.writeValueAsString(new Auther("Ali", "19.2.125.52", "ali@gmail.com", 0, null))))
//		      .andExpect(status().isOk());
//
//	}
	
	
	@Test
	public void insertAutherTest() throws Exception {

		Auther autherParam = new Auther("Ali", "192.1682.125.52", "ali@gmail.com", 0, null);
		

		AutherDto dto = AutherDto.builder()
				.name("Ali")
				.email("Ali@gmail.com")
				.ipAddress(null)
				.build();

		Mockito.when(autherService.insert(Mockito.any(Auther.class))).thenReturn(autherParam);

		mockMvc.perform(post("/auther")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(dto)))
		      .andExpect(status().isOk());

	}
	

//	@Test
//	public void findByEmailNotFoundTest() {
//
//		Optional<Auther> autherParam = Optional.of(new Auther("Ali", "19.2.125.52", "ali@gmail.com", 0, null));
//
//		Mockito.when(autherService.findByEmail(Mockito.anyString())).thenReturn(autherParam);
//
//		String email = "ali@gmail.com";
//		ResponseEntity<AutherDto> respEntity = restTemplate.getForEntity("/auther/email/" + email, AutherDto.class);
//		assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
////	 assertThat(respEntity.getBody()).isEqualTo("OK");
//	}
	
	@AfterAll
	public void destroy () {
		log.info("=====>> in destroy method");
	}
}
