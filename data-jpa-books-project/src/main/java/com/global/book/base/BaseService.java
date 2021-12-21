package com.global.book.base;

import java.util.List;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;

@MappedSuperclass
public class BaseService<T extends BaseEntity<ID>, ID extends Number> {

	@Autowired
	private BaseRepository<T, ID> baseRepository;

	public T findById(ID id) {

		return baseRepository.findById(id).orElseThrow();
	}

	public T getById(ID id) {

		return baseRepository.getById(id);
	}

	public List<T> findAll() {

		return baseRepository.findAll();
	}

	public T insert(T entity) {

		if (entity.getId() != null) {

			throw new RuntimeException();
		}

		return baseRepository.save(entity);
	}

	public List<T> insertAll(List<T> entity) {

		return baseRepository.saveAll(entity);
	}

	public T update(T entity) {

		return baseRepository.save(entity);
	}

	public void deleteById(ID id) {

		baseRepository.deleteById(id);
	}

}
