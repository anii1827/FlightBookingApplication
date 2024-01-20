package com.flightapp.Flights.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomException;
import com.flightapp.Flights.Model.Flight;
import com.flightapp.Flights.Model.Seats;
import com.flightapp.Flights.Services.FlightAdminServiceImpl;
import com.flightapp.Flights.Util.FlightStatus;
import com.flightapp.Flights.Util.SeatType;
import com.flightapp.Flights.repositories.FlightRepository;

@SpringBootTest
public class FlightAdminServiceTest {
	
	@Mock
	FlightRepository flightRepo;
	
	
	@InjectMocks
	private FlightAdminServiceImpl flightAdminService;
	
	
	@Test
	public void testAddFlight() {
	  ArgumentCaptor<Flight> captor = ArgumentCaptor.forClass(Flight.class);
	  when(flightRepo.save(captor.capture())).thenReturn(getFlight());
	  
	  flightAdminService.addFlight(getFlightDto());
	  
	  Flight value = captor.getValue();
	  assertEquals(getFlightDto().getAirLine(), value.getAirLine());
	  assertEquals(getFlightDto().getFlightNo(), value.getFlightNo());
	  assertEquals(40, value.getTotalSeat());
	  assertEquals(getFlightDto().getSource(), value.getSource());
	  assertEquals(getFlightDto().getDestination(), value.getDestination());
	  assertEquals("24h",value.getTotalTime());
	  assertEquals(getFlightDto().getTicketPriceForBusinessClass(), value.getTicketPriceForBusinessClass());
	  assertEquals(getFlightDto().getTicketPriceForNonBusinessClass(), value.getTicketPriceForNonBusinessClass());
	}
		
	@Test
	public void blockFlight() {
		when(flightRepo.findById(5l)).thenReturn(Optional.of(getFlight()));
		ArgumentCaptor<Flight> captor = ArgumentCaptor.forClass(Flight.class);
		when(flightRepo.save(captor.capture())).thenReturn(getFlight());
		List<Flight> list =new ArrayList<>();
		list.add(getFlight());
		when(flightRepo.findAll()).thenReturn(list);
		
		flightAdminService.blockFlight(5l);
		
		Flight value = captor.getValue();
		assertEquals(FlightStatus.Blocked.toString(), value.getFstatus());
		
	}
	
	@Test
	public void blockNotExistFlight() {
		CustomException ex = assertThrows(CustomException.class, ()->{
			this.flightAdminService.blockFlight(5l);
		});
		
		assertEquals(String.format("There is No fight available with this id = %d",5l), ex.getMessage());
	}
	
	@Test
	public void blockAlreadyBlockFlight() {
		Flight flight = getFlight();
		flight.setFstatus(FlightStatus.Blocked.toString());
		when(flightRepo.findById(5l)).thenReturn(Optional.of(flight));
		
		CustomException ex = assertThrows(CustomException.class, ()->{
			this.flightAdminService.blockFlight(5l);
		});
		
		assertEquals(String.format("The Flight is allready blocked with id : %d",5l), ex.getMessage());
	}
		

	public FlightDTO getFlightDto() {
		FlightDTO dto = new FlightDTO();
		dto.setAirLine("INDIGO");
		dto.setFlightId(5l);
		dto.setFlightNo("2210");
		dto.setSource("DEL");
		dto.setDestination("BLR");
		dto.setAvailableSeatInBusinessClass(20);
		dto.setAvailableSeatInNonBusinessClass(20);
		dto.setTicketPriceForBusinessClass(6000);
		dto.setTicketPriceForNonBusinessClass(3000);
		dto.setStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm")));
		dto.setEndTime(LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm")));
		return dto;
		
	}
	
	private Flight getFlight() {
		Flight flight = new Flight();
		flight.setAirLine("INDIGO");
		flight.setAvailable(true);
		flight.setFstatus(FlightStatus.UnBlocked.toString());
		flight.setDestination("BANGALORE");
		flight.setSource("DELHI");
		flight.setFlightNo("2123");
		flight.setFlightId(5l);
		flight.setTotalSeat(50);
		flight.setTotalSeatInBusinessClass(10);
		flight.setTotalSeatInNonBusinessClass(10);
		flight.setAvailableSeatInBusinessClass(10);
		flight.setAvailableSeatInNonBusinessClass(10);;
		flight.setStartTime(LocalDateTime.now());
		flight.setEndTime(LocalDateTime.now().plusDays(1));
		
		Seats seat1 = new Seats(flight, 1, SeatType.Business.toString(), false);
		Seats seat2 = new Seats(flight, 2, SeatType.Business.toString(), false);
		Seats seat3= new Seats(flight, 3, SeatType.Business.toString(), false);
		Seats seat4= new Seats(flight, 4, SeatType.Business.toString(), false);
		Seats seat5= new Seats(flight, 5, SeatType.Business.toString(), false);
		Seats seat6= new Seats(flight, 6, SeatType.NonBusiness.toString(), false);
		Seats seat7 = new Seats(flight, 7, SeatType.NonBusiness.toString(), false);
		Seats seat8 = new Seats(flight, 8, SeatType.NonBusiness.toString(), false);
		Seats seat9 = new Seats(flight, 9, SeatType.NonBusiness.toString(), false);
		Seats seat10 = new Seats(flight, 10, SeatType.NonBusiness.toString(), false);
		Seats seat11 = new Seats(flight, 11, SeatType.Business.toString(), false);
		Seats seat12 = new Seats(flight, 12, SeatType.Business.toString(), false);
		Seats seat13= new Seats(flight, 13, SeatType.Business.toString(), false);
		Seats seat14= new Seats(flight, 14, SeatType.Business.toString(), false);
		Seats seat15= new Seats(flight, 15, SeatType.Business.toString(), false);
		Seats seat16= new Seats(flight, 16, SeatType.NonBusiness.toString(), false);
		Seats seat17 = new Seats(flight, 17, SeatType.NonBusiness.toString(), false);
		Seats seat18 = new Seats(flight, 18, SeatType.NonBusiness.toString(), false);
		Seats seat19 = new Seats(flight, 19, SeatType.NonBusiness.toString(), false);
		Seats seat20 = new Seats(flight, 20, SeatType.NonBusiness.toString(), false);
		
		Set<Seats> seats = new HashSet<>();
		seats.add(seat1);seats.add(seat2);seats.add(seat3);seats.add(seat4);seats.add(seat5);seats.add(seat6);seats.add(seat7);seats.add(seat8);seats.add(seat9);
		seats.add(seat18);seats.add(seat17);seats.add(seat14);seats.add(seat13);seats.add(seat10);
		seats.add(seat20);seats.add(seat19);seats.add(seat16);seats.add(seat15);seats.add(seat12);seats.add(seat11);
		flight.setSeats(seats);
		return flight;
	}
	
}
