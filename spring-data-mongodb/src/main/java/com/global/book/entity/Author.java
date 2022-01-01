package com.global.book.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class Author {
	
	@Id
	private String id ;
	
	@NotEmpty
	private String name;
	
	@Email
	@Indexed(unique = true, name = "email", useGeneratedName = true)
	private String email;
	
	@Indexed(unique = true)
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
