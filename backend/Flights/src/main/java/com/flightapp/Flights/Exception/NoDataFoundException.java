package com.flightapp.Flights.Exception;

public class NoDataFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -1831994906690609500L;
	public String message;
	
	
	public NoDataFoundException(String message) {
		super(message);
	}
	
}
