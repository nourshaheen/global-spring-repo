package com.global.product.config;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.product.entity.Product;
import com.global.product.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
@Component
public class StartupApp implements CommandLineRunner {

	private final ProductService productService;
	
	
	@Override
	public void run(String... args) {

		log.info("=========>> in Command Line Runner");
		try {
		productService.update(new Product(null, "product 1" , 333.5, "category 1"));
		productService.update(new Product(null, "product 2" , 333.5, "category 1"));
		productService.update(new Product(null, "product 3" , 333.5, "category 2"));
		productService.update(new Product(null, "product 4" , 333.5, "category 2"));
		}catch (Exception e) {

			e.printStackTrace();
		}
	}

}
