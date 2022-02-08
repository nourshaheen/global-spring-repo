package com.global.book.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Formula;

import com.global.book.base.BaseDto;
import com.global.book.validator.IpAddress;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AutherDto extends BaseDto<Long> {	
	
	@NotBlank
	private String name;
	
	@IpAddress()
	private String ipAddress;
	
	@Email(message = "{validation.constraints.email.message}")
	private String email;

	private long bookCount;

	private String imagePath;

}
