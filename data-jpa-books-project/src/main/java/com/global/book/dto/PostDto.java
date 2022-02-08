package com.global.book.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -973790642300432737L;
	

	private Long id ;
	
	private String title ;
	
	private String body ;
	
	private Long userId;

	
}
