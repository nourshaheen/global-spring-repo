package com.global.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AutherSearch {
	
	private String autherName;
	
	private String email ;
	
	private String ipAddress;
	
	private String bookName ;
	
	private Double price;

	
}
