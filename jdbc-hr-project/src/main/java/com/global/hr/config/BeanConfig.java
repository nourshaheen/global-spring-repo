package com.global.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.global.hr.repository.impl.EmployeeJDBCRepo;

@Configuration
public class BeanConfig {
	
	@Bean()
	@Scope("singleton")
	public EmployeeJDBCRepo getEmployeeJDBCRepo() {
		
		return new EmployeeJDBCRepo();
	}

}
