package com.global.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.entity.Author;
import com.global.book.repo.AuthorRepo;
import com.global.book.repo.CustomAuthorRepo;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepo authorRepo;
	
	@Autowired
	private CustomAuthorRepo customAuthorRepo;
	
	public Author findById(String id) {
	
		return authorRepo.findById(id).get();
	}

	public List<Author> findAll() {

		return authorRepo.findAll();
	}

	public Author insert(Author entity) {

		if (entity.getId() != null) {

			throw new RuntimeException();
		}

		return authorRepo.insert(entity);
	}

	public List<Author> insertAll(List<Author> entity) {

		return authorRepo.saveAll(entity);
	}

	public Author update(Author entity) {
		
		Author author = findById(entity.getId());
		author.setName(entity.getName());
		author.setEmail(entity.getEmail());
		author.setPhone(entity.getPhone());
		
		return authorRepo.save(author);
	}
	
	public void updateEmail(String email, String name , String phone) {
		
		customAuthorRepo.updateEmail(email, name, phone);
	}
	
	public Author findAuthorByEmail (String email) {
		
		return authorRepo.findAuthorByEmail(email);
	}

	public void deleteById(String id) {

		authorRepo.deleteById(id);
	}


}
