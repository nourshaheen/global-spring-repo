package com.global.hr.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.hr.entity.Role;
import com.global.hr.entity.User;
import com.global.hr.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleService roleService;

	public User findById(Long id) {

		return userRepo.findById(id).orElseThrow();
	}

	public User insert(User emp) {

		return userRepo.save(emp);
	}

	public User update(User emp) {

		User current = userRepo.findById(emp.getId()).orElseThrow();

		current.setUserName(emp.getUserName());
		current.setPassword(emp.getPassword());

		return userRepo.save(current);
	}

	public List<User> findAll() {

		return userRepo.findAll();
	}
	
	@Transactional
	public void addRoleForAllUsers(String roleName) {
		
		// start transaction
		Role role = roleService.findByName(roleName);
		
		findAll().forEach( user ->{
			
			user.addRole(role);
			
			userRepo.save(user);
			
		} );
		
		
		// commit  or rollback
		
		// end transaction 
		
	}

}
