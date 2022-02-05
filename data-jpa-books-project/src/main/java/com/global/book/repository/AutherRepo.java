package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Auther;

@Repository
public interface AutherRepo extends BaseRepository<Auther, Long>, JpaSpecificationExecutor<Auther> {
	
	
	@Transactional
	@Query(value = "UPDATE Auther a SET a.isDeleted = false WHERE a.id = ?1")
	@Modifying
	public void restoreById(Long id);
	
	Optional<Auther> findByEmail(String email);

	
	@Override
//	@EntityGraph(attributePaths = "books")
    List<Auther> findAll() ;
	
	@Override
//	@EntityGraph(attributePaths = "books")
	Optional<Auther> findById(Long id) ;
}
