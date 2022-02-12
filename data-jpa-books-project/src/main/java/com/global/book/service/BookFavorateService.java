package com.global.book.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.BookFavorate;
import com.global.book.repository.BookFavorateRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookFavorateService extends BaseService<BookFavorate, Long> {
	
	
	private final BookFavorateRepo bookFavorateRepo;
	
	public Optional<BookFavorate> findByAutherIdAndBookId (Long autherId , Long bookId){
		
		return bookFavorateRepo.findByAutherIdAndBookId(autherId, bookId);
	}

}
