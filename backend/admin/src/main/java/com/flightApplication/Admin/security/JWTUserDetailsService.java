package com.flightApplication.Admin.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService implements UserDetailsService{

	@Value("${Admin.UserName}")
	private String user;
	
	@Value("${Admin.password}")
	private String password;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals(user)) {
				return new User(username,this.password,new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User Not Found with Username: "+username);
		}
	}

}
