package com.global.book.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;

@SQLDelete(sql = "update authers set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@Entity
@Table(name = "authers")
public class Auther extends BaseEntity<Long> {
		
	@NotBlank
	private String name;
	
	@Pattern(regexp = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$")
	private String ipAddress;
	
	@Email
	private String email;
	
	@Formula("(select count(*) from books book where book.auther_id = id)")
	private long bookCount;
	
//	@NotEmpty
	@JsonManagedReference
	@OneToMany(mappedBy = "auther" , cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();
	
	
	public void addBook (Book book) {
		books.add(book);
	}
	
	public void removeBook (Book book) {
		books.remove(book);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
