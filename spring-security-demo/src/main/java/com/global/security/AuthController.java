package com.global.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;
	
	
	@PostMapping("/login")
	public ResponseEntity<JWTResponseDto> login (@RequestBody LoginRequestDto loginRequest){
		
		JWTResponseDto jwtResponseDto = authService.login(loginRequest.getLogin(), loginRequest.getPassword());
		
		return ResponseEntity.ok(jwtResponseDto);
	} 
	
	
	 @PostMapping("/refresh-token")
	 public ResponseEntity<AccessTokenDto> refreshAccessToken(@RequestParam String refreshToken) {
		 
		 AccessTokenDto dto = authService.refreshAccessToken(refreshToken);
		
		 return ResponseEntity.ok(dto);
	 }
	 
	 
	 @PostMapping("/logout")
	 public ResponseEntity<?> logout(@RequestParam String refreshToken) {
		 
		 authService.logoutUser(refreshToken);
		
		 return ResponseEntity.ok(null);
	 }

}
