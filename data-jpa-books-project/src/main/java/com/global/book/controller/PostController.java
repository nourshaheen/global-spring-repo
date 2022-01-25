package com.global.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.PostDto;
import com.global.book.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById (@PathVariable Long id) {
	
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	
	@GetMapping("")
	public ResponseEntity<?> getAllPost () {
	
		return ResponseEntity.ok(postService.getAllPost());
	}
	
	
	@PostMapping("")
	public ResponseEntity<?> addPost (@RequestBody PostDto dto) {
	
		return ResponseEntity.ok(postService.addPost(dto));
	}

}
