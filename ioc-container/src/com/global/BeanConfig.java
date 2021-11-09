package com.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
	
	@Bean()
//	@Scope("prototype")
	public Department department () {
		return new Department();
	}

}
