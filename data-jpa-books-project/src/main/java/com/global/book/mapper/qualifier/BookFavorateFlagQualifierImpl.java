package com.global.book.mapper.qualifier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.global.book.entity.Book;
import com.global.book.entity.BookFavorate;
import com.global.book.mapper.qualifier.BookFavorateQualifier.BookFavorateFlagMethodQualifier;
import com.global.book.service.BookFavorateService;

@BookFavorateQualifier
@Component
public class BookFavorateFlagQualifierImpl {
	
	@Autowired
	private BookFavorateService bookFavorateService;
	
	@BookFavorateFlagMethodQualifier
	public Boolean getIsFavorate (Book entity) {
		
		Optional<BookFavorate> bookFavorate = bookFavorateService.findByAutherIdAndBookId(entity.getId(), entity.getAuther().getId());
	
		return bookFavorate.isPresent() ? true : false;
	}

}
