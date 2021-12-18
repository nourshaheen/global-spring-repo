package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	
	@Override
	@EntityGraph(value = "loadAuther")
	Optional<Book> findById(Long id) ;
	
	@Override
	@EntityGraph(attributePaths = {"auther"})
	List<Book> findAll();
	
	@Transactional
	@Modifying
	@Query("delete from Book where auther.id = :id")
	int deleteByAutherId (Long id);
	

}
