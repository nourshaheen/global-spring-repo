package com.global.book.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.global.book.base.BaseRepository;
import com.global.book.entity.BookFavorate;

@Repository
public interface BookFavorateRepo extends BaseRepository<BookFavorate, Long> {
	
	
	Optional<BookFavorate> findByAutherIdAndBookId (Long autherId , Long bookId);
	
}
