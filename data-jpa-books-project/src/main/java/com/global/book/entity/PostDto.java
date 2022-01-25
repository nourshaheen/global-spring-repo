package com.global.book.entity;

import java.io.Serializable;

public class PostDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -973790642300432737L;
	

	private Long id ;
	
	private String title ;
	
	private String body ;
	
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
