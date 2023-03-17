package com.flightapp.Flights.Services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomException;
import com.flightapp.Flights.Model.Flight;
import com.flightapp.Flights.Model.Seats;
import com.flightapp.Flights.Util.Convert;
import com.flightapp.Flights.Util.DateTimeAPI;
import com.flightapp.Flights.Util.FlightStatus;
import com.flightapp.Flights.repositories.FlightRepository;

@Service
public class FlightCustomerServiceImpl implements FlightCustomerService {
	
	@Autowired
	FlightRepository flightrepository;

	@Transactional
	@Override
	public List<Integer> bookFlight(long flightId, int businessSeat, int nonBusinessSeat, Set<Integer> seat){
		System.out.println("businessSeaat "+ businessSeat);
		System.out.println("NonBusinessSeat " +nonBusinessSeat);
		if(businessSeat+nonBusinessSeat!=seat.size()) {
			throw new CustomException("Seat Deatils is not Correct! ", HttpStatus.BAD_REQUEST);
		}
		Optional<Flight> optional = flightrepository.findById(flightId);
		//if flight data not available then customer Exception exception will be throw
		Flight flight = optional.orElseThrow(()->new CustomException("Data not Available with id! "+ flightId, HttpStatus.NOT_FOUND));
		if(!flight.isAvailable()) {
			throw new CustomException("Flight either has been cancel or full!", HttpStatus.INSUFFICIENT_STORAGE);
		}
		Set<Seats> seats = flight.getSeats();
		Set<Integer> resp = new HashSet<>(seat);
		for(Seats s : seats) {
			if(seat.contains(s.getSeatNumber())) {
				if(s.isBooked()) {
					throw new CustomException("requested Seat already booked! ", HttpStatus.CONFLICT);
				}
				else {
				s.setBooked(true);
				seat.remove(s.getSeatNumber());
				}
			}
		}
		if(seat.size()>0) {
			StringBuilder s = new StringBuilder("");
			seat.stream().forEach((x)->{
				s.append(x.toString()+", ");
			});
			throw new CustomException("kindly check the "+s+ " seat and book again", HttpStatus.CONFLICT);
		}
		flight.setAvailableSeatInBusinessClass(flight.getAvailableSeatInBusinessClass()-businessSeat);
		flight.setAvailableSeatInNonBusinessClass(flight.getAvailableSeatInNonBusinessClass()-nonBusinessSeat);
		if(flight.getAvailableSeatInBusinessClass()==0 && flight.getAvailableSeatInBusinessClass()==0) {
			flight.setAvailable(false);
		}
		flightrepository.save(flight);
		return resp.stream().sorted().collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public boolean cancelFlight(long flightId, int businessSeat, int nonBusinessSeat, Set<Integer> seat) {
		if(businessSeat+nonBusinessSeat!=seat.size()) {
			throw new CustomException("Seat Information is not Correct! ", HttpStatus.BAD_REQUEST);
		}
		if(businessSeat+nonBusinessSeat!=seat.size()) {
//			Handle Exception;
			return false;
		}
		Optional<Flight> optional = flightrepository.findById(flightId);
		Flight flight = optional.orElseThrow(()->new CustomException("Data not Available with id! "+ flightId, HttpStatus.NOT_FOUND));
		Set<Seats> seats = new HashSet<>(flight.getSeats());
		for(Seats s : seats) {
			if(seat.contains(s.getSeatNumber())==true && !s.isBooked()) {
				throw new CustomException("Seat Information is In Correct! ", HttpStatus.BAD_REQUEST);
			}
		}
		
		seats = seats.stream().map((x)->{
			if(seat.contains(x.getSeatNumber())) {
				x.setBooked(false);
			}
			return x;
		}).collect(Collectors.toSet());
		flight.setSeats(seats);
		
		flight.setAvailableSeatInBusinessClass(flight.getAvailableSeatInBusinessClass()+businessSeat);
		flight.setAvailableSeatInNonBusinessClass(flight.getAvailableSeatInNonBusinessClass()+nonBusinessSeat);
		if(flight.isAvailable()==false) {
			if(flight.getAvailableSeatInBusinessClass()>0 || flight.getAvailableSeatInNonBusinessClass()>0)
			flight.setAvailable(true);
		}
		flightrepository.save(flight);
		return true;
	}

	
	@Transactional
	@Override
	public List<FlightDTO> searchFlight(String source, String destination, String date) {
		LocalDate date1 = DateTimeAPI.getDate(date);
		List<Flight> flight = flightrepository.findBySourceAndDestinationAndDate(source, destination, date1);
		if(flight.size()==0) {
			throw new CustomException("No Flight Avaialble on date ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
 		List<FlightDTO> collect = flight.stream().filter(f->f.isAvailable() && f.getFstatus().equals(FlightStatus.UnBlocked.toString())).map((f)->{
 					FlightDTO entitytoDTO = Convert.entitytoDTO(f);
 					entitytoDTO.setAvailableSeatNumber(getSeatNumber(f));
 					return entitytoDTO;
 				}
 				).collect(Collectors.toList());
 		if(collect.size()==0) {
 			throw new CustomException("No Flight Avaialble on date "+date, HttpStatus.INTERNAL_SERVER_ERROR);
		}
 		return collect;
	}
	
	@Transactional
	@Override
	public List<FlightDTO> getAllFlight() {
		List<Flight> flights = (List<Flight>) flightrepository.findAll();
		if(flights.size()==0) {
			throw new CustomException("No Flight Avaialble", HttpStatus.ACCEPTED);
		}
		List<FlightDTO> collect = flights.stream().filter(f->f.isAvailable() && f.getFstatus().equals(FlightStatus.UnBlocked.toString())).map(
					f->Convert.entitytoDTO(f)
				).collect(Collectors.toList());
		if(collect.size()==0) {
			throw new CustomException("No Flight Avaialble", HttpStatus.ACCEPTED);
		}
		return collect;
	}
	
	//to collect the available seat number
	private HashMap<String, List<Integer>> getSeatNumber(Flight flight){
		Set<Seats> seats = new HashSet<>(flight.getSeats());
		HashMap<String, List<Seats>> collect = (HashMap<String, List<Seats>>) seats.stream().filter(s->!s.isBooked()).collect(Collectors.groupingBy(Seats::getType));
		HashMap<String, List<Integer>> map = new HashMap<>();
		collect.entrySet().forEach((x)->{
				map.put(x.getKey(),x.getValue().stream().map(y->y.getSeatNumber()).sorted().collect(Collectors.toList()));
		});
		return map;
	}
	
	@Override
	public FlightDTO getFlight(Long Id) {
		try {
		Optional<Flight> findById = flightrepository.findById(Id);
		Flight flight = findById.orElseThrow(()->new CustomException("No Flight Avaialbe with id"+Id, HttpStatus.NOT_FOUND));
		FlightDTO flightDTO = Convert.entitytoDTO(flight);
		return flightDTO;
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

