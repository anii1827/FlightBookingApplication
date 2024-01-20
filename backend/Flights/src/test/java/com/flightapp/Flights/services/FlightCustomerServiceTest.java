package com.flightapp.Flights.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomException;
import com.flightapp.Flights.Exception.NoDataFoundException;
import com.flightapp.Flights.Model.Flight;
import com.flightapp.Flights.Model.Seats;
import com.flightapp.Flights.Services.FlightCustomerServiceImpl;
import com.flightapp.Flights.Util.FlightStatus;
import com.flightapp.Flights.Util.SeatType;
import com.flightapp.Flights.repositories.FlightRepository;



@SpringBootTest
public class FlightCustomerServiceTest {
	
	@Mock
	FlightRepository flightRepo;
	
	
	@InjectMocks
	private FlightCustomerServiceImpl flightService;
	
	@Test
	public void testSearchFlightWithRequiredParameter() {
		Flight flight = getFlight();
		when(flightRepo.findBySourceAndDestinationAndDate("DEL", "BLR", LocalDate.of(2023, 11, 12))).thenReturn(List.of(flight));
		
		List<FlightDTO> flights = flightService.searchFlight("DEL", "BLR", "2023-11-12");
		
		assertEquals(1, flights.size());
		assertEquals(flight.getAirLine(), flights.get(0).getAirLine());
		assertTrue(flights.get(0).isAvailable());
		assertEquals(flight.getFlightNo(), flights.get(0).getFlightNo());
		assertEquals(flight.getFlightId(), flights.get(0).getFlightId());
		assertEquals(flight.getSource(), flights.get(0).getSource());
		assertEquals(flight.getDestination(), flights.get(0).getDestination());
		assertEquals(10, flights.get(0).getAvailableSeatNumber().get(SeatType.Business.toString()).size());
		assertEquals(10, flights.get(0).getAvailableSeatNumber().get(SeatType.NonBusiness.toString()).size());
	}
	
	@Test
	public void testSearchWithNotAvailableData() {
		Flight flight = getFlight();
		when(flightRepo.findBySourceAndDestinationAndDate("DEL", "BLR", LocalDate.of(2023, 11, 12))).thenReturn(List.of(flight));
		
		assertThrows(NoDataFoundException.class, ()->{
			flightService.searchFlight("BLR", "DEL", "2023-11-12");
		});

	}
	
	@Test
	public void testSearchWithInCorrectFormatDate() {
		Flight flight = getFlight();
		when(flightRepo.findBySourceAndDestinationAndDate("DEL", "BLR", LocalDate.of(2023, 11, 12))).thenReturn(List.of(flight));
		
		CustomException exception = assertThrows(CustomException.class, ()->{
			flightService.searchFlight("DEL", "BLR", "12.11.2023");
		});
		
		assertEquals(String.format("Date format should be in yyyy-MM-d for %s", "12.11.2023"), exception.getMessage());
	}
	
	
	@Test
	public void testGetAllFlightApi() {
		Flight flight = getFlight();
		when(flightRepo.findAll()).thenReturn(List.of(flight));
		
		List<FlightDTO> flights = flightService.getAllFlight();
		
		assertEquals(1, flights.size());
		assertEquals(flight.getAirLine(), flights.get(0).getAirLine());
		assertTrue(flights.get(0).isAvailable());
		assertEquals(flight.getFlightNo(), flights.get(0).getFlightNo());
		assertEquals(flight.getFlightId(), flights.get(0).getFlightId());
		assertEquals(flight.getSource(), flights.get(0).getSource());
		assertEquals(flight.getDestination(), flights.get(0).getDestination());
		assertEquals(10, flights.get(0).getAvailableSeatNumber().get(SeatType.Business.toString()).size());
		assertEquals(10, flights.get(0).getAvailableSeatNumber().get(SeatType.NonBusiness.toString()).size());
	}
	
	@Test
	public void testGetAllAPIWithNoDataFoundException() {
		when(flightRepo.findAll()).thenReturn(List.of());
		NoDataFoundException exception = assertThrows(NoDataFoundException.class, ()->{
			flightService.getAllFlight();
		});
		
		assertEquals("No Flight has been sechduled yet", exception.getMessage());
	}
	
	@Test
	public void testGetflightById() {
		when(flightRepo.findById(12l)).thenReturn(Optional.of(getFlight()));
		
		FlightDTO flight = this.flightService.getFlight(12l);
		assertEquals(getFlight().getAirLine(), flight.getAirLine());
		assertTrue(flight.isAvailable());
		assertEquals(getFlight().getFlightNo(), flight.getFlightNo());
		assertEquals(getFlight().getFlightId(), flight.getFlightId());
		assertEquals(getFlight().getSource(), flight.getSource());
		assertEquals(getFlight().getDestination(), flight.getDestination());
		assertEquals(10, flight.getAvailableSeatNumber().get(SeatType.Business.toString()).size());
		assertEquals(10, flight.getAvailableSeatNumber().get(SeatType.NonBusiness.toString()).size());
	}
	
	@Test
	public void testGetFlightByIdNoDataException() {
		when(flightRepo.findById(12l)).thenReturn(Optional.of(getFlight()));
		NoDataFoundException exception = assertThrows(NoDataFoundException.class, ()->{
			this.flightService.getFlight(13l);
		});
		assertEquals("No Flight Avaialbe with id : 13", exception.getMessage());	
	}
	
	@Test
	public void testBookFlightApi() {
		 when(flightRepo.findById(5l)).thenReturn(Optional.of(getFlight()));
		 ArgumentCaptor<Flight> captor = ArgumentCaptor.forClass(Flight.class);
		 when(flightRepo.save(captor.capture())).thenReturn(getFlight());
		 
		 Set<Integer> set = new HashSet<>();
		 set.add(1);
		 set.add(2);
		 set.add(3);
		 set.add(4);
		 this.flightService.bookFlight(5l, 2, 2, set);
		 
		 Flight value = captor.getValue();
		 
		 assertEquals(8, value.getAvailableSeatInBusinessClass());
		 assertEquals(8, value.getAvailableSeatInNonBusinessClass());
	}
	
	@Test
	public void testBookFlightApiForNotAvailableFlight() {
		 when(flightRepo.findById(5l)).thenReturn(Optional.of(getFlight()));
		 ArgumentCaptor<Flight> captor = ArgumentCaptor.forClass(Flight.class);
		 when(flightRepo.save(captor.capture())).thenReturn(getFlight());
		 
		 Set<Integer> set = new HashSet<>();
		 set.add(1);
		 set.add(2);
		 set.add(3);
		 set.add(4);
		 
		 CustomException ex = assertThrows(CustomException.class, ()->{
			 this.flightService.bookFlight(6l, 2, 2, set);
		 });
		 
		 assertEquals(String.format("No Flight available with %d! ", 6), ex.getMessage());
		 assertEquals(HttpStatus.NOT_FOUND, ex.status);
	}
	
	@Test
	public void testCancelledFlight() {
		Flight flight = getFlight();
		flight.setAvailable(false);
		when(flightRepo.findById(5l)).thenReturn(Optional.of(flight));
		
		Set<Integer> bookSeat = new HashSet<>();
		bookSeat.add(1);
		bookSeat.add(3);
		bookSeat.add(6);
		bookSeat.add(8);
		
		CustomException ex = assertThrows(CustomException.class,  ()-> {
			this.flightService.bookFlight(5l, 2, 2, bookSeat);
		});
		
		assertEquals("Flight either has been cancel or full!", ex.getMessage());
		
	}
	
	@Test
	public void testBookFlighApiForBookedSeat() {
		Flight flight = getFlight();
		Set<Seats> seats = flight.getSeats().stream().map(x->{
			if(x.getSeatNumber()==1 || x.getSeatNumber()==2 || x.getSeatNumber()==6 || x.getSeatNumber()==7) {
				x.setBooked(true);
			}
			return x;
		}).collect(Collectors.toSet());
		flight.setSeats(seats);
		
		when(flightRepo.findById(5l)).thenReturn(Optional.of(flight));
		
		Set<Integer> bookSeat = new HashSet<>();
		bookSeat.add(1);
		bookSeat.add(3);
		bookSeat.add(6);
		bookSeat.add(8);
		
		CustomException ex = assertThrows(CustomException.class,  ()-> {
			this.flightService.bookFlight(5l, 2, 2, bookSeat);
		});
		
		assertEquals("requested Seat already booked! ", ex.getMessage());
		
	}
	
	@Test
	public void testCancelFlightApi() {
		 Flight flight = getFlight();
		 flight.setAvailableSeatInBusinessClass(8);
		 flight.setAvailableSeatInNonBusinessClass(8);
		 Set<Seats> seats = flight.getSeats().stream().map(x->{
				if(x.getSeatNumber()==1 || x.getSeatNumber()==2 || x.getSeatNumber()==6 || x.getSeatNumber()==7) {
					x.setBooked(true);
				}
				return x;
			}).collect(Collectors.toSet());
			flight.setSeats(seats);
			
		 when(flightRepo.findById(5l)).thenReturn(Optional.of(flight));
		 ArgumentCaptor<Flight> captor = ArgumentCaptor.forClass(Flight.class);
		 when(flightRepo.save(captor.capture())).thenReturn(getFlight());
		 
		 Set<Integer> seat = new HashSet<>();
		 seat.add(1);
		 seat.add(2);
		 seat.add(6);
		 seat.add(7);
		 this.flightService.cancelFlight(5l, 2, 2, seat);
		 
		 Flight value = captor.getValue();
		 
		 assertEquals(10, value.getAvailableSeatInBusinessClass());
		 assertEquals(10, value.getAvailableSeatInNonBusinessClass());
	}
	
	
	
	
	
	//make sure don't change this dummy data 
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


