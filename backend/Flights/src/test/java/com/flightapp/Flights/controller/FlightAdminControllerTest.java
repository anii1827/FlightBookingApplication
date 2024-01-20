package com.flightapp.Flights.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import com.flightapp.Flights.Controller.FlightAdminController;
import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomException;
import com.flightapp.Flights.Services.FlightAdminServiceImpl;
import com.flightapp.Flights.Services.FlightCustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FlightAdminController.class)
public class FlightAdminControllerTest {
	
private static final String BASE_URL="/api/v1.0/flight/admin";
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	FlightCustomerServiceImpl service;
	
	@MockBean
	FlightAdminServiceImpl adminservice;
	
	@Autowired
	ObjectMapper mapper;
	
	@Test
	public void testAddFlightwitEmptyData() throws JsonProcessingException, Exception {
		when(adminservice.addFlight(getEmptyFlightDto())).thenReturn(getEmptyFlightDto());
		this.mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(getEmptyFlightDto())))
				.andExpect(status().isBadRequest());		
	}
	
	
	@Test
	public void testAddFlightwithData() throws JsonProcessingException, Exception {
		when(adminservice.addFlight(getFlightDto())).thenReturn(getFlightDto());
		this.mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(getFlightDto())))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void blockFlight() throws Exception {
		when(adminservice.blockFlight(5)).thenReturn(List.of(getFlightDto()));
		
		this.mockMvc.perform(put(BASE_URL+"/block/5"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].airLine").value("INDIGO"))
		.andExpect(jsonPath("$[0].flightId").value("5"))
		.andExpect(jsonPath("$[0].flightNo").value("2210"));
	}
	
	@Test
	public void blockNotExistFlight() throws Exception {
		when(adminservice.blockFlight(5l)).thenThrow(new CustomException(String.format("There is No fight available with this id = %d",5l), HttpStatus.NOT_FOUND));
		
		this.mockMvc.perform(put(BASE_URL+"/block/5"))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.message").value(String.format("There is No fight available with this id = %d",5l)));
		
	}
	
	@Test
	public void blockAlreadyBlockFlight() throws Exception {
		when(adminservice.blockFlight(5l)).thenThrow(new CustomException(String.format("The Flight is allready blocked with id : %d",5l), HttpStatus.NOT_ACCEPTABLE));
		
		this.mockMvc.perform(put(BASE_URL+"/block/5"))
		.andExpect(status().isNotAcceptable())
		.andExpect(jsonPath("$.message").value(String.format("The Flight is allready blocked with id : %d",5l)));
	}
	
	public FlightDTO getFlightDto() {
		FlightDTO dto = new FlightDTO();
		dto.setSource("DEL");
		dto.setDestination("BLR");
		dto.setAirLine("INDIGO");
		dto.setFlightId(5l);
		dto.setFlightNo("2210");
		dto.setAvailableSeatInBusinessClass(20);
		dto.setAvailableSeatInNonBusinessClass(20);
		dto.setTicketPriceForBusinessClass(6000);
		dto.setTicketPriceForNonBusinessClass(3000);
		dto.setStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm")));
		dto.setEndTime(LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm")));
		return dto;
		
	}
	public FlightDTO getEmptyFlightDto(){
		return new FlightDTO();
	}
	
}
