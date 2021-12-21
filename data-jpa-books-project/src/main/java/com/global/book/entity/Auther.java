package com.global.book.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	private String name;
	
	@Formula("(select count(*) from books book where book.auther_id = id)")
	private long bookCount;
	
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


}
