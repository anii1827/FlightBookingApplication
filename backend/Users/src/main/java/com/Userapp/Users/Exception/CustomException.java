package com.Userapp.Users.Exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
	public String message;
	public HttpStatus status;
	public int errorCode;
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
