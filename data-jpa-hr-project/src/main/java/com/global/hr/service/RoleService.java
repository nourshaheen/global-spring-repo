package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Role;
import com.global.hr.repository.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;

	public Role findById(Long id) {

		return roleRepo.findById(id).orElseThrow();
	}

	public Role insert(Role role) {

		return roleRepo.save(role);
	}

	public Role update(Role role) {

		Role current = roleRepo.findById(role.getId()).orElseThrow();

		current.setName(role.getName());

		return roleRepo.save(current);
	}

	public List<Role> findAll() {

		return roleRepo.findAll();
	}

}
