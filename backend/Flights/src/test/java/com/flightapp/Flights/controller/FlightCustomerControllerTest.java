package com.flightapp.Flights.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp.Flights.Controller.FlightCustomerController;
import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomException;
import com.flightapp.Flights.Exception.NoDataFoundException;
import com.flightapp.Flights.Services.FlightAdminServiceImpl;
import com.flightapp.Flights.Services.FlightCustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FlightCustomerController.class)
public class FlightCustomerControllerTest {
	
	private static final String BASE_URL="/api/v1.0/flight/customer";
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	FlightCustomerServiceImpl service;
	
	@MockBean
	FlightAdminServiceImpl adminservice;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@Test
	public void testSearchFlight() throws Exception {
		FlightDTO dto = getFlight();
		when(service.searchFlight("DEL", "BLR", "2023-11-12")).thenReturn(List.of(dto));
		
		String url = BASE_URL+"/search";
		
		this.mockMvc
		.perform(get(url).queryParam("source", "DEL").queryParam("destination", "BLR").queryParam("date", "2023-11-12"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].source").value(dto.getSource()))
		.andExpect(jsonPath("$[0].destination").value(dto.getDestination()))
		.andExpect(jsonPath("$[0].airLine").value(dto.getAirLine()));
	
	}
	
	@Test
	public void testSearchFlightWithEmptyParameter() throws Exception{
		FlightDTO dto = getFlight();
		when(service.searchFlight("DEL", "BLR", "2023-11-12")).thenReturn(List.of(dto));
		
		String url = BASE_URL+"/search";
		
		this.mockMvc
		.perform(get(url).queryParam("source", "").queryParam("destination", "BLR").queryParam("date", "2023-11-12"))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("source should not be null or empty"));
		
		this.mockMvc
		.perform(get(url).queryParam("source", "DEL").queryParam("destination", "").queryParam("date", "2023-11-12"))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("destination should not be null or empty"));
		
		this.mockMvc
		.perform(get(url).queryParam("source", "DEL").queryParam("destination", "BLR").queryParam("date", ""))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("date should not be null or empty"));
	}
	
	@Test 
	public void testSearchUnavailableData() throws Exception {
		when(service.searchFlight("DEL", "BLR", "2023-11-12")).thenThrow(new NoDataFoundException("No Flight Avaialble from DEL to BLR"));

		String url = BASE_URL+"/search";
		this.mockMvc
		.perform(get(url).queryParam("source", "DEL").queryParam("destination", "BLR").queryParam("date", "2023-11-12"))
		.andExpect(status().isNoContent())
		.andExpect(jsonPath("$.message").value("No Flight Avaialble from DEL to BLR"));	
	}
	
	@Test
	public void testSearchWithWrongDateFormat() throws Exception {
		when(service.searchFlight("DEL", "BLR", "2023.11.12")).thenThrow(new CustomException(String.format("Date format should be in yyyy-MM-d for %s", "12.11.2023"), HttpStatus.NOT_ACCEPTABLE));

		String url = BASE_URL+"/search";
		this.mockMvc
		.perform(get(url).queryParam("source", "DEL").queryParam("destination", "BLR").queryParam("date", "2023.11.12"))
		.andExpect(status().isNotAcceptable())
		.andExpect(jsonPath("$.message").value(String.format("Date format should be in yyyy-MM-d for %s", "12.11.2023")));
	}
	
	@Test
	public void testSearchWithGetAllFlightApi() throws Exception {
		
		FlightDTO dto = getFlight();
		when(service.getAllFlight()).thenReturn(List.of(dto));
		
		String url = BASE_URL;
		
		this.mockMvc
		.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].source").value(dto.getSource()))
		.andExpect(jsonPath("$[0].destination").value(dto.getDestination()))
		.andExpect(jsonPath("$[0].airLine").value(dto.getAirLine()));

	}
	
	@Test
	public void testSearchWithGetAllFlightApiWithEmptyData() throws Exception {
		when(service.getAllFlight()).thenThrow(new NoDataFoundException("No Flight has been sechduled yet"));
		String url = BASE_URL;
		
		this.mockMvc
		.perform(get(url))
		.andExpect(status().isNoContent())
		.andExpect(jsonPath("$.message").value("No Flight has been sechduled yet"));

	}
	
	@Test
	public void testGetFlighByID() throws Exception {
		when(service.getFlight(13l)).thenReturn(getFlight());
		String url = BASE_URL+"/get";
		
		this.mockMvc.perform(get(url).queryParam("flightId", "13"))
		.andExpect(jsonPath("$.source").value(getFlight().getSource()))
		.andExpect(jsonPath("$.destination").value(getFlight().getDestination()))
		.andExpect(jsonPath("$.airLine").value(getFlight().getAirLine()));
	}
	
	@Test
	public void testGetFlightByNotExistId() throws Exception {
		when(service.getFlight(13l)).thenThrow(new NoDataFoundException("No Flight Avaialbe with id : 13"));
		String url = BASE_URL+"/get";
	
		this.mockMvc.perform(get(url).queryParam("flightId", "13"))
		.andExpect(status().isNoContent())
		.andExpect(jsonPath("$.message").value("No Flight Avaialbe with id : 13"));
	}
	
	@Test
	public void testBookFlightAPI() throws Exception {
			Set<Integer> seats = Set.of(2,4,5,6);
			when(service.bookFlight(1, 2, 2, seats)).thenReturn(Collections.nCopies(25, 1));
			
			this.mockMvc
				.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(seats))
				.queryParam("flightId", "1").queryParam("businessClassSeat", "2").queryParam("NonbusinessclassSeat", "2"))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value("25"));
	}
	
	
	@Test
	public void testBookFlightApiForNotAvailableFlight() throws JsonProcessingException, Exception {
		 Set<Integer> seat = new HashSet<>();
		 seat.add(1);
		 seat.add(2);
		 seat.add(3);
		 seat.add(4);
		
		 when(service.bookFlight(5l, 2, 2 , seat)).thenThrow(new CustomException(String.format("No Flight available with %d! ", 5l), HttpStatus.NOT_FOUND));
		 
		 this.mockMvc.perform(post(BASE_URL)
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(mapper.writeValueAsString(seat))
				 .queryParam("flightId", "5").queryParam("businessClassSeat", "2").queryParam("NonbusinessclassSeat", "2"))
		 		 .andExpect(status().isNotFound())
		 		 .andExpect(jsonPath("$.message").value(String.format("No Flight available with %d! ", 5l)));
	}
	
	@Test
	public void testCancelledFlight() throws JsonProcessingException, Exception {
		Set<Integer> seat = new HashSet<>();
		 seat.add(1);
		 seat.add(2);
		 seat.add(3);
		 seat.add(4);
		 
		 when(service.bookFlight(5l, 2, 2, seat)).thenThrow(new CustomException("Flight either has been cancel or full!", HttpStatus.INSUFFICIENT_STORAGE));
		 
		 this.mockMvc.perform(post(BASE_URL)
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(mapper.writeValueAsString(seat))
				 .queryParam("flightId", "5").queryParam("businessClassSeat", "2").queryParam("NonbusinessclassSeat", "2"))
		 		 .andExpect(status().isInsufficientStorage())
		 		 .andExpect(jsonPath("$.message").value("Flight either has been cancel or full!"));
	}
	
	@Test
	public void testBookFlighApiForBookedSeat() throws JsonProcessingException, Exception {
		Set<Integer> seat = new HashSet<>();
		 seat.add(1);
		 seat.add(2);
		 seat.add(3);
		 seat.add(4);
		 
		 when(service.bookFlight(5l, 2, 2, seat)).thenThrow(new CustomException("requested Seat already booked! ", HttpStatus.CONFLICT));
		 
		 this.mockMvc.perform(post(BASE_URL)
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(mapper.writeValueAsString(seat))
				 .queryParam("flightId", "5").queryParam("businessClassSeat", "2").queryParam("NonbusinessclassSeat", "2"))
		 		 .andExpect(status().isConflict())
		 		 .andExpect(jsonPath("$.message").value("requested Seat already booked! "));
		
	}
	
	@Test
	public void testCancelFlight() throws JsonProcessingException, Exception {
		Set<Integer> Bookedseat = new HashSet<>();
		Bookedseat.add(1);
		Bookedseat.add(2);
		Bookedseat.add(3);
		Bookedseat.add(4);
		
		when(service.cancelFlight(5l, 2, 2, Bookedseat)).thenReturn(true);
		
		this.mockMvc.perform(delete(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(Bookedseat))
				.queryParam("flightId", "5").queryParam("businessClassSeat", "2").queryParam("NonbusinessclassSeat", "2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Booking has been canceled"));
		 
	}
	
	private FlightDTO getFlight(){
		FlightDTO dto = new FlightDTO();
		dto.setAirLine("INDIGO");
		dto.setAvailable(true);
		dto.setDestination("BANGALORE");
		dto.setSource("DELHI");
		dto.setAvailableSeatInBusinessClass(25);
		dto.setAvailableSeatInNonBusinessClass(25);
		return dto;
	}
	
}

