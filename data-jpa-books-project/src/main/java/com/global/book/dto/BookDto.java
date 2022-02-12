package com.global.book.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.global.book.base.BaseDto;
import com.global.book.entity.Auther;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class BookDto extends BaseDto<Long> {
		
	@NotBlank
	private String name ;
	
	@Min(value = 5)
	@Max(value = 5000)
	private double price;
	
	@NotNull
	private AutherDto auther;
	
	private String autherName;
	
	private String autherEmail;
	
	private Boolean isFavorate;

	
}
