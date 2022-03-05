package com.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHanding {
	
	
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<?> handleDisabledException(DisabledException ex) {

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This Account is Disabled");
	}
	
	
	@ExceptionHandler(LockedException.class)
	public ResponseEntity<?> handleLockedException(LockedException ex) {

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This Account is Locked");
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
	}

}
