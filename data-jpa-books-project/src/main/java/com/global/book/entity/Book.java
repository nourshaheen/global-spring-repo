package com.global.book.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.book.base.BaseEntity;


@NamedEntityGraph(name = "loadAuther" , attributeNodes = @NamedAttributeNode("auther"))
@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long> {
	
	private String name ;
	
	private double price;
	
	@Transient
	private double discounted;
	
	@Formula("(select count(*) from books)")
	private long bookCount;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auther_id")
	private Auther auther;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Auther getAuther() {
		return auther;
	}

	public void setAuther(Auther auther) {
		this.auther = auther;
	}

	@Override
	public String toString() {
		return "Book [id=" + getId() + ", name=" + name + ", price=" + price + "]";
	}

	public double getDiscounted() {
		return price * .25;
	}

	public void setDiscounted(double discounted) {
		this.discounted = discounted;
	}
	
	@PostLoad
	private void calcDiscount() {
		
		this.setDiscounted(price * .25);
	}

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}

}
