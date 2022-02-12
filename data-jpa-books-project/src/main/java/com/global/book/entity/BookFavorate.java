package com.global.book.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.book.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class BookFavorate  extends BaseEntity<Long>{
	
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "auther_id")
	private Auther auther;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "book_id")
	private Book book;

}
