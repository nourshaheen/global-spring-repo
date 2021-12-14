package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.hr.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	
	@Override
	@Transactional(readOnly = false)
	List<User> findAll();
	

}
