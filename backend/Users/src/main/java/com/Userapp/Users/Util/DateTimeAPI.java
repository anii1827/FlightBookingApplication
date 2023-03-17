package com.Userapp.Users.Util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.http.HttpStatus;

import com.Userapp.Users.Exception.CustomException;

public class DateTimeAPI {
		public static String getBoardingTime(String time) {
		try {
			String getDate = "HH:mm";
			DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(getDate, Locale.US);
			LocalTime parse = LocalTime.parse(time, ofPattern);
			LocalTime minusHours = parse.minusHours(1);
			return ofPattern.format(minusHours).toString();
		}
		catch(IllegalArgumentException i) {
			throw new CustomException(i.getMessage()+time, HttpStatus.NOT_ACCEPTABLE);
		}
		catch(DateTimeParseException e) {
			throw new CustomException(e.getMessage()+time, HttpStatus.NOT_ACCEPTABLE);
		}
		
		}
		
		public static LocalDateTime convertStringtoLocalDateTime(String time) {
			try {
				DateTimeFormatter pattern = DateTimeFormatter.ofPattern("EEEE, d MMM yyyy HH:mm", Locale.US);
				LocalDateTime parse = LocalDateTime.parse(time, pattern);
				return parse;
				}
			catch(IllegalArgumentException i) {
				throw new CustomException(i.getMessage()+"! Date Time format should be in d-MM-yyyy HH:mm for "+time, HttpStatus.NOT_ACCEPTABLE);
			}catch(DateTimeParseException e) {
				throw new CustomException(e.getMessage()+"! Date Time format should be in d-MM-yyyy HH:mm for "+time, HttpStatus.NOT_ACCEPTABLE);
			}
		}
}
