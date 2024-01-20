package com.flightapp.Flights.Util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.http.HttpStatus;

import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomException;
import com.flightapp.Flights.Model.Flight;

public class Convert {

	public static Flight dtotoEntity(FlightDTO flightDTO) {
		try {
		Flight flight = new Flight();
		BeanUtils.copyProperties(flightDTO, flight);
		return flight;
		}
		catch(BeansException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	public static FlightDTO entitytoDTO(Flight flight) {
		try {
		FlightDTO dto = new FlightDTO();
		BeanUtils.copyProperties(flight, dto);
		//updating the date format
		dto.setStartTime(DateTimeAPI.getDate_Time(flight.getStartTime()));
		dto.setEndTime(DateTimeAPI.getDate_Time(flight.getEndTime()));
		dto.setStartTimeinHoursandMinute(DateTimeAPI.getTimeHoursAndMinute(flight.getStartTime()));
		dto.setEndTimeinHoursandMinute(DateTimeAPI.getTimeHoursAndMinute(flight.getEndTime()));
		
		return dto;
		}
		catch(BeansException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	
}
