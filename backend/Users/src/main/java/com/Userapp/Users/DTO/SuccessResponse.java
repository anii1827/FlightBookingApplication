/**
 * 
 */
package com.Userapp.Users.DTO;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class SuccessResponse implements Serializable {
	
	private static final long serialVersionUID = -6292360895741983405L;
	
		private String Message;
		private HttpStatus status;
		
		public SuccessResponse(String message, HttpStatus status) {
			super();
			Message = message;
			this.status = status;
		}
		
		
		public String getMessage() {
			return Message;
		}
		public void setMessage(String message) {
			Message = message;
		}
		public HttpStatus getStatus() {
			return status;
		}
		public void setStatus(HttpStatus status) {
			this.status = status;
		}
		
		
		
}
