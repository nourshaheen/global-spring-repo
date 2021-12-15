package com.global.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
