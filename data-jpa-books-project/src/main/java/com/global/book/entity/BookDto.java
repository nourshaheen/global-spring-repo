package com.global.book.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
	
	private Long id;
	
	@NotBlank
	private String name ;
	
	@Min(value = 5)
	@Max(value = 5000)
	private double price;
	
	@NotNull
	private Auther auther;

	
}
