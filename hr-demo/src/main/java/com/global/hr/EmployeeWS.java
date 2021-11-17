package com.global.hr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeWS {
	
	@Value("${image.location.path}")
	private String imagePath ;
	
	@Value("${server.port}")
	private String serverPort ;

	@GetMapping("/name")
	public String getEmpName() {

		return "Hello Employee Nour and image path is >> " + imagePath + " and port is " + serverPort;
	}
	
}
