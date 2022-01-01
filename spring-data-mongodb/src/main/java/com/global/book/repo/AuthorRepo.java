package com.global.book.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Author;

@Repository
public interface AuthorRepo extends MongoRepository<Author, String> {
	
	@Query(value= "{email:'?0'}" , fields="{'email' : 1}")
	Author findAuthorByEmail (String email);

}
