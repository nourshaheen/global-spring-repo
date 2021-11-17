package com.global.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class HrDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrDemoApplication.class, args);
	}

}
