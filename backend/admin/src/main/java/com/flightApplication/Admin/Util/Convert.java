package com.flightApplication.Admin.Util;

import org.springframework.beans.BeanUtils;

import com.flightApplication.Admin.DTO.FlightDTO;
import com.flightApplication.Admin.Model.Flight;

public class Convert {
			public static Flight dtoToEntity(FlightDTO dto) {
				Flight flight = new Flight();
				BeanUtils.copyProperties(dto, flight);
				return flight;
			}
			public static FlightDTO entityToDTO(Flight flight) {
				FlightDTO flightDto = new FlightDTO();
				BeanUtils.copyProperties(flight, flightDto);
				return flightDto;
			}
}
