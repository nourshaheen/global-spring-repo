package com.global.security;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {

	@NotEmpty
	private String login;
	
	@NotEmpty
	private String password;
}
