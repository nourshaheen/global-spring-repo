package com.global.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	String [] PUBLIC_END_POINTS = {"/api/v1/auth/login", "/api/v1/auth/refresh-token", "/api/v1/auth/logout"};
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtUnAuthResponse jwtUnAuthResponse;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable()
	            .exceptionHandling()
	            .and().httpBasic()
	            .authenticationEntryPoint(jwtUnAuthResponse)
	            .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .authorizeRequests()
	            .antMatchers(PUBLIC_END_POINTS).permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	    }
	   
	   
	   @Override
	    @Bean
	    protected AuthenticationManager authenticationManager() throws Exception {
	        // TODO Auto-generated method stub
	        return super.authenticationManager();
	    }

	    @Bean
	    public AuthFilter authFilter() {
	        return new AuthFilter();
	    }

}
