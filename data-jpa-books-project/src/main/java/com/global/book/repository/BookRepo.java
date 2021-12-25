package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Book;
import com.global.book.entity.BookDto;

@Repository
public interface BookRepo extends BaseRepository<Book, Long> {
	
	@Override
	@EntityGraph(value = "loadAuther")
	Optional<Book> findById(Long id) ;
	
	@Override
	@EntityGraph(attributePaths = {"auther"})
	List<Book> findAll();
	
	@EntityGraph(attributePaths = {"auther"})
	@Query("select book from Book book join book.auther auther where "
			+ " (:name is null or book.name like :name)"
			+ " and (:price is null or book.price >= :price)"
			+ " and (:autherId is null or auther.id = autherId)")
	List<Book> filter(String name, double price , Long autherId);
	
	@Transactional
	@Modifying
	@Query("delete from Book where auther.id = :id")
	int deleteByAutherId (Long id);
	
	
	@Transactional
	@Query(value = "UPDATE Book b SET b.isDeleted = false WHERE b.auther.id = ?1")
	@Modifying
	public void restoreByAuthorId(Long autherId);
	
	
	//Map a Stored Procedure Name Directly
	@Procedure
	int GET_BOOK_BY_AUTHER(String auther_id_in);

	@Procedure("GET_BOOK_BY_AUTHER")
	int getTotalCarsByModel(String auther_id_in);
	
	int getBookByAuther(String auther_id_in);
	
	//Stored Procedure with @Query Annotation
	@Query(value = "CALL GET_BOOK_BY_AUTHER(:auther_id_in);", nativeQuery = true)
	List<Book> findCarsAfterYear(@Param("auther_id_in") Integer year_in);
	

}
