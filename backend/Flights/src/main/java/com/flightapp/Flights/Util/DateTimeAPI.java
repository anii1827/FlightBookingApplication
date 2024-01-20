package com.flightapp.Flights.Util;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flightapp.Flights.Exception.CustomException;

@Service
public class DateTimeAPI {
	
	static String getDateFormatfromAdmin;
	@Value("${DateTime.format.front}")
	public void setDateTimeFormat(String name) {
		getDateFormatfromAdmin = name;
	}
	
	static String getDateFormat;
	@Value("${Date.format.db}")
	public void setDateTimeFormatDb(String name) {
		getDateFormat = name;
	}
	
	
	static String getTimeFormat;
	@Value("${Time.format.db}")
	public void setTimeFormat(String name) {
		getTimeFormat = name;
	}
	
	
	static String getDate;
	@Value("${Date.format}")
	public void setDateFormat(String name) {
		getDate = name;
	}
	
	//get date and time into string in specific format for end customer
	public static String getDate_Time(LocalDateTime dateTime){
		try {
		 DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(getDateFormat, Locale.US);
		 String format = ofPattern.format(dateTime);
		 return format;
		}
		catch(IllegalArgumentException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(DateTimeException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	//convert string to locadatetime object for aggregation
	public static LocalDateTime getDate_Time(String date) {
		try {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(getDateFormatfromAdmin, Locale.US);
	    LocalDateTime parse = LocalDateTime.parse(date, ofPattern);
	    return parse;
		}
		catch(IllegalArgumentException e) {
			throw new CustomException(String.format("Date format should be in %s for %s", getDate, date), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(DateTimeParseException e) {
			throw new CustomException(String.format("Date format should be in %s for %s", getDate, date), HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	//get the difference between two local times
	public static String getDateTimeBetween(LocalDateTime start, LocalDateTime end){
		try {
 		long hours = Duration.between(start,end).toSeconds()/3600;
		long minutes = (Duration.between(start, end).toSeconds()%3600)/60;
		StringBuilder TotalDuration = new StringBuilder().append(hours).append("h");
		if(minutes!=0) {
			TotalDuration.append(" "+minutes).append("m");
		}
		return TotalDuration.toString();
		}
		catch(ArithmeticException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(DateTimeException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}


	//get time into string in specific format
	public static String getTimeHoursAndMinute(LocalDateTime date) {
		try {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(getTimeFormat, Locale.US);
		String format = ofPattern.format(date);
	    return format;
		}
		catch(IllegalArgumentException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(DateTimeException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	
	public static LocalDate getDate(String date) {
		try {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(getDate, Locale.US);	
		LocalDate parse = LocalDate.parse(date, ofPattern);
		return parse;
		}
		
		catch(IllegalArgumentException e) {
			throw new CustomException(String.format("Date format should be in %s for %s", getDate, date), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(DateTimeParseException e) {
			throw new CustomException(String.format("Date format should be in %s for %s", getDate, date), HttpStatus.NOT_ACCEPTABLE);
		}
	}
		
	
}
