package com.flightApplication.gatway.dto;

public class JWTResponse {
	private final String jwttoken;

	public JWTResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
