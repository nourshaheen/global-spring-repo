package com.global.security;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.global.entity.AppUser;
import com.global.entity.TokenInfo;
import com.global.service.TokenInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {	

	 private final AuthenticationManager authManager;
	 
	 private final HttpServletRequest httpRequest;
	 
	 private final TokenInfoService tokenInfoService;
	 
	 public JWTResponseDto login(String login, String password) {
	        Authentication authentication = authManager.authenticate(
	        		new UsernamePasswordAuthenticationToken(login, password));
	        
	        log.debug("Valid userDetails credentials.");

	        AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();
	        
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        log.debug("SecurityContextHolder updated. [login={}]", login);
	        
	        
	        TokenInfo  tokenInfo = createLoginToken(login, userDetails.getId());

	        
	        return JWTResponseDto.builder()
	        		.accessToken(tokenInfo.getAccessToken())
	        		.refreshToken(tokenInfo.getRefreshToken())
	        		.build();
	}
	 
	 
	 public TokenInfo createLoginToken(String userName, Long userId) {
			String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);
			InetAddress ip = null;
			try {
				ip = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String accessTokenId = UUID.randomUUID().toString();
			String accessToken = JwtTokenUtils.generateToken(userName, accessTokenId, false);
			log.info("Access token created. [tokenId={}]", accessTokenId);

			String refreshTokenId = UUID.randomUUID().toString();
			String refreshToken = JwtTokenUtils.generateToken(userName, refreshTokenId, true);
			log.info("Refresh token created. [tokenId={}]", accessTokenId);

			TokenInfo tokenInfo = new TokenInfo(accessToken, refreshToken);
			tokenInfo.setUser(new AppUser(userId));
			tokenInfo.setUserAgentText(userAgent);
			tokenInfo.setLocalIpAddress(ip.getHostAddress());
			tokenInfo.setRemoteIpAddress(httpRequest.getRemoteAddr());
			// tokenInfo.setLoginInfo(createLoginInfoFromRequestUserAgent());
			return tokenInfoService.save(tokenInfo);
		}

}
