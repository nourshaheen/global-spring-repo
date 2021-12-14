package com.global.hr.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
	
	Long getId();
	
	String getFirstName();
	
	String getLastName();
	
	@Value("#{target.firstName + ' ' + target.lastName}")
	String getFullName();

}
