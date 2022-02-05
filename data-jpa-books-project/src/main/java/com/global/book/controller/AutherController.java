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
import com.global.book.entity.AutherSearch;
import com.global.book.entity.Book;
import com.global.book.service.AutherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auther Controller")
@Validated
@RestController
@RequestMapping("/auther")
public class AutherController {

	private AutherService autherService;

	public AutherController(AutherService autherService) {
		super();
		this.autherService = autherService;
	}

	@Operation(summary = "Get a book by its id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found the book", 
			    content = { @Content(mediaType = "application/json", 
							schema = @Schema(implementation = Book.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Book not found", 
			    content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@Parameter(example = "20", name = "Book Id") @PathVariable @Min(value = 1) @Max(value = 200) Long id) {

		return ResponseEntity.ok(autherService.findById(id));
	}
	
	@Operation(summary = "Get a book by its email")
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {

		return ResponseEntity.ok(autherService.findByEmail(email));
	}

	@Operation(summary = "Get all books")
	@GetMapping()
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(autherService.findAll());
	}

	@Operation(summary = "Add book")
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody @Valid Auther entity) {

		return ResponseEntity.ok(autherService.insert(entity));
	}

	@Operation(summary = "update book")
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody @Valid Auther entity) {

		return ResponseEntity.ok(autherService.update(entity));
	}

	@Operation(summary = "delete a book by its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		autherService.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@Operation(summary = "Get a book by search ")
	@PostMapping("/spec")
	public ResponseEntity<?> findByAutherSpec(@RequestBody AutherSearch search){
		
		return ResponseEntity.ok(autherService.findByAutherSpec(search));
	}
}
