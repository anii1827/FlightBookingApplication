package com.flightApplication.Admin.Exception;

import org.springframework.http.HttpStatus;

public class CustomResponseData {
	private String application;
	private String TimeStamp;
	private HttpStatus status;
	private String message;

	public CustomResponseData() {
	}

	public CustomResponseData(String application, String timeStamp, HttpStatus status, String message) {
		this.application=application;
		this.TimeStamp = timeStamp;
		this.status = status;
		this.message = message;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
