package com.global.book.base;

import java.util.List;
import java.util.Optional;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;

import com.global.book.config.MessageUtils;
import com.global.book.error.RecoredNotFoundExecption;

@MappedSuperclass
public class BaseService<T extends BaseEntity<ID>, ID extends Number> {

	@Autowired
	private BaseRepository<T, ID> baseRepository;
	
	@Autowired
	private MessageUtils messageUtils;

	public T findById(ID id) {

		Optional<T> entity = baseRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		} else {
//			Locale local = new Locale("en");
			String [] msgParam = {id.toString()};
			String msg = messageUtils.getMessage("validation.recoredNotFound.message", msgParam);
			
			throw new RecoredNotFoundExecption(msg);
		}

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
