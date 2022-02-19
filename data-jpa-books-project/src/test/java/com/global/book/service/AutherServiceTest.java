package com.global.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;

@SpringBootTest
public class AutherServiceTest {

	@InjectMocks
	AutherService autherService;

	@Mock
	AutherRepo autherRepo;

	@Test
	public void findByEmailFoundTest() {

		Optional<Auther> autherParam = Optional.of(new Auther("Ali", "19.2.125.52", "ali@gmail.com", 0, null));

		Mockito.when(autherRepo.findByEmail(Mockito.anyString())).thenReturn(autherParam);

		Optional<Auther> auther = autherService.findByEmail("ali@gmail.com");

		assertEquals(true, auther.isPresent());
		assertEquals("ali@gmail.com", auther.get().getEmail());
	}

}
