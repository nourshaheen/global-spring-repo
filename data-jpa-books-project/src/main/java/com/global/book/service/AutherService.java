package com.global.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;

@Service
public class AutherService {

	private AutherRepo autherRepo;

	public AutherService(AutherRepo autherRepo) {
		super();
		this.autherRepo = autherRepo;
	}

	public Auther findById(Long id) {

		return autherRepo.findById(id).orElseThrow();
	}
	
	public Auther getById(Long id) {

		return autherRepo.getById(id);
	}

	public List<Auther> findAll() {

		return autherRepo.findAll();
	}

	public Auther insert(Auther entity) {

		if (entity.getId() != null) {

			throw new RuntimeException();
		}

		return autherRepo.save(entity);
	}
	
	
	public List<Auther> insertAll(List<Auther> entity) {

		return autherRepo.saveAll(entity);
	}

	public Auther update(Auther entity) {

		Auther auther = findById(entity.getId());

		auther.setName(entity.getName());

		return autherRepo.save(auther);
	}

	public void deleteById(Long id) {

		autherRepo.deleteById(id);
	}

}
