package com.global.book.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Auther;
import com.global.book.entity.AutherSearch;
import com.global.book.error.DaplicateRecoredException;
import com.global.book.repository.AutherRepo;
import com.global.book.repository.AutherSpec;

@Service
public class AutherService extends BaseService<Auther, Long> {

	@Autowired
	private AutherRepo autherRepo;
	
	Logger log = LoggerFactory.getLogger(AutherService.class);

	@Override
	public Auther insert(Auther entity) {
		
	if (!entity.getEmail().isEmpty() && entity.getEmail() != null) {
		Optional<Auther> auther = findByEmail(entity.getEmail());
		
		log.info("author name is {} and email is {} " , entity.getName() , entity.getEmail());
				
		if(auther.isPresent()) {
			log.error("This email already found with anther author");
			throw new DaplicateRecoredException("This email already found with anther author");
		}

	}

		return super.insert(entity);
	}

	@Override
	public Auther update(Auther entity) {

		Auther auther = findById(entity.getId());

		auther.setName(entity.getName());

		return super.update(auther);
	}

	public List<Auther> findByAutherSpec(AutherSearch search) {

		AutherSpec spec = new AutherSpec(search);

		return autherRepo.findAll(spec);

	}

	public Optional<Auther> findByEmail(String email) {

		return autherRepo.findByEmail(email);
	}

}
