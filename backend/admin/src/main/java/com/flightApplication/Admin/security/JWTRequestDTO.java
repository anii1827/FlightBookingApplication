package com.flightApplication.Admin.security;

import java.io.Serializable;

public class JWTRequestDTO implements Serializable {

	private static final long serialVersionUID = -447341340645044817L;
	private String username;
	private String password;
	public JWTRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	public JWTRequestDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
