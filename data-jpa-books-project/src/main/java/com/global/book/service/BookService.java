package com.global.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;

@Service
public class BookService extends BaseService<Book, Long> {

	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}
	
	
	public List<Book> insertAll(List<Book> entities) {
		
		return bookRepo.saveAll(entities);
	}

	public Book update(Book entity) {

		Book book = findById(entity.getId());

		book.setName(entity.getName());

		return update(book);
	}

	
	public int deleteByAutherId (Long id) {
		
		return bookRepo.deleteByAutherId(id);
	}

}
