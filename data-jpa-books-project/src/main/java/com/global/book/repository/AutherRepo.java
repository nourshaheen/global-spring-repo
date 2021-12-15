package com.global.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Auther;

@Repository
public interface AutherRepo extends JpaRepository<Auther, Long> {

}
