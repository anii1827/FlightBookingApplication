package com.flightapp.Flights.Exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
	
		@ExceptionHandler(CustomException.class)
		public ResponseEntity<CustomResponseData> exception(CustomException e){
					CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), e.getStatus(), e.getMessage());
					return new ResponseEntity<CustomResponseData>(c,c.getStatus());
		}
		
		@ExceptionHandler(IllegalArgumentException.class)
		public ResponseEntity<CustomResponseData> exception(IllegalArgumentException e){
					CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, e.getMessage());
					return new ResponseEntity<CustomResponseData>(c,c.getStatus());
		}
		
		@ExceptionHandler(NoDataFoundException.class)
		public ResponseEntity<CustomResponseData> exception(NoDataFoundException e){
			CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), HttpStatus.NO_CONTENT, e.getMessage());
			return new ResponseEntity<CustomResponseData>(c,c.getStatus());
		}
		
		@ExceptionHandler(ConstraintViolationException.class)
		public ResponseEntity<CustomResponseData> exception(ConstraintViolationException e){
			StringBuilder error = new StringBuilder();
			e.getConstraintViolations().stream().forEach(x->{
				String fieldname = x.getPropertyPath().toString();
				String message = x.getMessage();
				error.append(fieldname+" "+message);
				error.append("\n");
			});
			CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, error.toString());
			return new ResponseEntity<CustomResponseData>(c,c.getStatus());
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<CustomResponseData> exception(MethodArgumentNotValidException e){
			BindingResult result = e.getBindingResult();
			StringBuilder error = new StringBuilder();
			result.getFieldErrors().stream().forEach(er->{
				String field = er.getField();
				String message = er.getDefaultMessage();
				error.append(field+" "+message);
				error.append("\n");
			});
			
			CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, error.toString());
			return new ResponseEntity<CustomResponseData>(c,c.getStatus());
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<CustomResponseData> exception(Exception e){
					CustomResponseData c = new CustomResponseData("Flight Application", LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
					return new ResponseEntity<CustomResponseData>(c,HttpStatus.INTERNAL_SERVER_ERROR);
		}
}

	