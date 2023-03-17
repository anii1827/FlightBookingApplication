package com.flightApplication.Admin.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import feign.RetryableException;

@ControllerAdvice
public class ExceptionConfig {
		@ExceptionHandler(CustomException.class)
		public ResponseEntity<CustomResponseData> exception(CustomException e){
					CustomResponseData c = new CustomResponseData("Admin Application", LocalDateTime.now().toString(), e.getStatus(), e.getMessage());
					System.out.println(c.getMessage());
					return new ResponseEntity<CustomResponseData>(c,c.getStatus());
		}
		
		@ExceptionHandler(RetryableException.class)
		public ResponseEntity<CustomResponseData> exception(RetryableException e){
					CustomResponseData c = new CustomResponseData("Admin Application", LocalDateTime.now().toString(), HttpStatus.valueOf(e.status()), e.getMessage());
					System.out.println(c.getMessage());
					return new ResponseEntity<CustomResponseData>(c,c.getStatus());
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<CustomResponseData> exception(Exception e){
					CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
					System.out.println("working");
					System.out.println(c);
					return new ResponseEntity<CustomResponseData>(c,HttpStatus.INTERNAL_SERVER_ERROR);
		}
}

