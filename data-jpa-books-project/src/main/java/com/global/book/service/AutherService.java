package com.global.book.service;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Auther;

@Service
public class AutherService extends BaseService<Auther, Long> {

	@Override
	public Auther update(Auther entity) {

		Auther auther = findById(entity.getId());
		
		auther.setName(entity.getName());
		
		return super.update(auther);
	}

}
