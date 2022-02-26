package com.global.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.entity.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
	
	Optional<AppUser> findByUserName (String userName) ;

}
