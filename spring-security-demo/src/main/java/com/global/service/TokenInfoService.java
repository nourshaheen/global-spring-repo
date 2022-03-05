package com.global.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.global.entity.TokenInfo;
import com.global.repository.TokenInfoRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenInfoService {

	private final TokenInfoRepo tokenInfoRepo;

	public TokenInfo findById(Long id) {

		return tokenInfoRepo.findById(id).orElse(null);
	}

	public Optional<TokenInfo> findByRefreshToken(String refreshToken) {

		return tokenInfoRepo.findByRefreshToken(refreshToken);
	}

	public TokenInfo save(TokenInfo entity) {

		return tokenInfoRepo.save(entity);
	}
	
	public void deleteById (Long id) {
		
		tokenInfoRepo.deleteById(id);
	}
}
