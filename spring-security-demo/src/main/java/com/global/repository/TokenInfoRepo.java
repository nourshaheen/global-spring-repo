package com.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.entity.TokenInfo;

@Repository
public interface TokenInfoRepo extends JpaRepository<TokenInfo, Long> {
	
	TokenInfo findByRefreshToken (String refreshToken);

}
