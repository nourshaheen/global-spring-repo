package com.global.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;

@Service
public class BookService {

	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	public Book findById(Long id) {

		return bookRepo.findById(id).orElseThrow();
	}

	public List<Book> findAll() {

		return bookRepo.findAll();
	}

	public Book insert(Book entity) {

		if (entity.getId() != null) {

			throw new RuntimeException();
		}

		return bookRepo.save(entity);
	}
	
	
	public List<Book> insertAll(List<Book> entities) {
		
		return bookRepo.saveAll(entities);
	}

	public Book update(Book entity) {

		Book book = findById(entity.getId());

		book.setName(entity.getName());

		return bookRepo.save(book);
	}

	public void deleteById(Long id) {

		bookRepo.deleteById(id);
	}
	
	public int deleteByAutherId (Long id) {
		
		return bookRepo.deleteByAutherId(id);
	}

}
