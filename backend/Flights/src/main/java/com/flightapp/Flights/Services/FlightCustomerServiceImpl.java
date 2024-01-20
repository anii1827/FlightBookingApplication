package com.flightapp.Flights.Services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightapp.Flights.Controller.FlightAdminController;
import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomException;
import com.flightapp.Flights.Exception.NoDataFoundException;
import com.flightapp.Flights.Model.Flight;
import com.flightapp.Flights.Model.Seats;
import com.flightapp.Flights.Util.Convert;
import com.flightapp.Flights.Util.DateTimeAPI;
import com.flightapp.Flights.Util.FlightStatus;
import com.flightapp.Flights.repositories.FlightRepository;

@Service
public class FlightCustomerServiceImpl implements FlightCustomerService {
	
	private static final Logger logger = LogManager.getLogger(FlightAdminController.class);
	
	@Autowired
	FlightRepository flightrepository;
	

	@Transactional
	@Override
	public List<Integer> bookFlight(long flightId, int businessSeat, int nonBusinessSeat, Set<Integer> seat){
		if(businessSeat+nonBusinessSeat!=seat.size()) {
			throw new CustomException("Seat Deatils are not Correct! ", HttpStatus.BAD_REQUEST);
		}
		
		Optional<Flight> optional = flightrepository.findById(flightId);
		
		//if flight data not available then custom Exception exception will be throw
		Flight flight = optional.orElseThrow(()->new CustomException(String.format("No Flight available with %d! ", flightId), HttpStatus.NOT_FOUND));
		
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
		LocalDate formatDate = DateTimeAPI.getDate(date);
		List<Flight> flight = flightrepository.findBySourceAndDestinationAndDate(source, destination, formatDate);
		if(flight.size()==0) {
			String message = String.format("Looking flight for from %s to %s. but No Flights are Available", source, destination);
			logger.warn(message);
			throw new NoDataFoundException(message);
		}
		
 		List<FlightDTO> collect = flight.stream().filter(f->f.isAvailable() && f.getFstatus().equals(FlightStatus.UnBlocked.toString())).map((f)->{
 					FlightDTO entitytoDTO = Convert.entitytoDTO(f);
 					entitytoDTO.setAvailableSeatNumber(getSeatNumber(f));
 					return entitytoDTO;
 				}).collect(Collectors.toList());
 		
 		if(collect.size()==0) {
 			String message = String.format("Looking flight for %s from %s to %s. but No Flights are Available", date, source, destination);
 			logger.warn(message);
 			throw new NoDataFoundException(message);
		}
 		
 		return collect;
	}
	
	@Transactional
	@Override
	public List<FlightDTO> getAllFlight() {
		List<Flight> flights = (List<Flight>) flightrepository.findAll();
		if(flights.size()==0) {
			String message = String.format("No Flight has been sechduled yet");
			throw new NoDataFoundException(message);
		}
		List<FlightDTO> collect = flights.stream().filter(f->f.isAvailable() && f.getFstatus().equals(FlightStatus.UnBlocked.toString())).map((f)->{
			FlightDTO entitytoDTO = Convert.entitytoDTO(f);
			entitytoDTO.setAvailableSeatNumber(getSeatNumber(f));
			return entitytoDTO;
		}).collect(Collectors.toList());
		
		if(collect.size()==0) {
			String message = String.format("No Flight has been sechduled yet");
			throw new NoDataFoundException(message);
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
		Optional<Flight> findById = flightrepository.findById(Id);
		Flight flight = findById.orElseThrow(()->new NoDataFoundException(String.format("No Flight Avaialbe with id : %d", Id)));
		FlightDTO flightDTO = Convert.entitytoDTO(flight);
		flightDTO.setAvailableSeatNumber(getSeatNumber(flight));
		return flightDTO;
	}
}

