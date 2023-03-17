package com.flightapp.Flights.Exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1831994906690609500L;
	public String message;
	public HttpStatus status;

	public CustomException() {
	}

	public CustomException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}