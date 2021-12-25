package com.global.book.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Auther;
import com.global.book.service.AutherService;

@Validated
@RestController
@RequestMapping("/auther")
public class AutherController {

	private AutherService autherService;

	public AutherController(AutherService autherService) {
		super();
		this.autherService = autherService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable @Min(value = 10) @Max(value = 200) Long id) {

		return ResponseEntity.ok(autherService.findById(id));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(autherService.findAll());
	}

	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody @Valid Auther entity) {

		return ResponseEntity.ok(autherService.insert(entity));
	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody @Valid Auther entity) {

		return ResponseEntity.ok(autherService.update(entity));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		autherService.deleteById(id);
		return ResponseEntity.ok(null);
	}

}
