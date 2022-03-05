package com.global.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.global.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppUserDetail implements UserDetails {
	
    private Long id ;
	
	private String fullName;
	
	private String userName;
	
	private String password ; 
	
	private List<GrantedAuthority> authorities ;
	
    private boolean isEnabled;
	
	private boolean isCredentialsNonExpired;
	
	private boolean isAccountNonLocked;
	
	private boolean isAccountNonExpired;
	
	

	public AppUserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppUserDetail(AppUser user) {
		super();
		this.id= user.getId();
		this.fullName =user.getFullName();
		this.userName= user.getUserName();
		this.password= user.getPassword();
		this.isEnabled = user.isEnabled();
		this.isCredentialsNonExpired = user.isCredentialsNonExpired();
		this.isAccountNonExpired = user.isAccountNonExpired();
		this.isAccountNonLocked = user.isAccountNonLocked();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		 if(!user.getRoles().isEmpty()) {
		        	user.getRoles().forEach(role -> {
		        		authorities.add(new SimpleGrantedAuthority(role.getName()));	
		        	});
		      }
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

}
