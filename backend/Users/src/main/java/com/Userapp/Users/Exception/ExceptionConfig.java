package com.Userapp.Users.Exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.Userapp.Users.Exception.CustomException;


@ControllerAdvice
public class ExceptionConfig {
	
		@ExceptionHandler(CustomException.class)
		public ResponseEntity<?> exception(CustomException e){
					CustomResponseData c = new CustomResponseData("User Application", LocalDateTime.now().toString(), e.getStatus(), e.getMessage());
					System.out.println(c);
					return new ResponseEntity<CustomResponseData>(c,e.getStatus());
		}
		
		@ExceptionHandler(HttpMessageNotReadableException.class)
		public ResponseEntity<?> exception(HttpMessageNotReadableException e){
					CustomResponseData c = new CustomResponseData("User Application", LocalDateTime.now().toString(), HttpStatus.NOT_ACCEPTABLE, e.getMessage()+"date format should be yyyy-mm-dd");
//					System.out.println(c);
					return new ResponseEntity<CustomResponseData>(c,HttpStatus.NOT_ACCEPTABLE);
		}
		
		@ExceptionHandler(DateTimeParseException.class)
		public ResponseEntity<?> exception(DateTimeParseException e){
			CustomResponseData c = new CustomResponseData("User Application", LocalDateTime.now().toString(), HttpStatus.NOT_ACCEPTABLE, e.getMessage()+e.getMessage()+"\n date format should be yyyy-mm-dd");
			System.out.println(c);
			return new ResponseEntity<CustomResponseData>(c,HttpStatus.NOT_ACCEPTABLE);
		}
	
		@ExceptionHandler(Exception.class)
		public ResponseEntity<CustomResponseData> exception(Exception e){
					CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
					System.out.println("working");
					System.out.println(c);
					return new ResponseEntity<CustomResponseData>(c,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
}

