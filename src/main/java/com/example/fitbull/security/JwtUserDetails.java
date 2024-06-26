package com.example.fitbull.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDetails implements UserDetails{
	Long id;
	private String email;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUserDetails(Long id, String email, String username, String password,Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.authorities=authorities;
	}
	
	public static JwtUserDetails create(User user,GymOwner gymOwner ) {
		List<GrantedAuthority> authoritiesList = new ArrayList<>(); 

		if (user != null) {
			authoritiesList.add(new SimpleGrantedAuthority("user"));
			return new JwtUserDetails(user.getId(),user.getEmail(),user.getUsername(),user.getPassword(),authoritiesList);
	    } else if (gymOwner != null) {
	        authoritiesList.add(new SimpleGrantedAuthority("gym_owner"));
			return new JwtUserDetails(gymOwner.getId(),gymOwner.getEmail(),gymOwner.getUsername(),gymOwner.getPassword(),authoritiesList);
	    } else {
	        throw new IllegalArgumentException("Hem user hem de gymOwner null olamaz");
	    }
		
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	


	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}

}
