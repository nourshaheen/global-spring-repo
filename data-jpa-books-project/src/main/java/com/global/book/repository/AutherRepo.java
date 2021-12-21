package com.global.book.repository;

import org.springframework.stereotype.Repository;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Auther;

@Repository
public interface AutherRepo extends BaseRepository<Auther, Long> {

}
