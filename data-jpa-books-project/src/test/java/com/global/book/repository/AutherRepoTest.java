package com.global.book.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.global.book.entity.Auther;

@SpringBootTest
public class AutherRepoTest {

	@Autowired
	AutherRepo autherRepo;

	@Test
	void findByEmailNotFoundTest() {

		Optional<Auther> auther = autherRepo.findByEmail("mail@gmail.com");

		assertEquals(false, auther.isPresent());

	}
	
}
