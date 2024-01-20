package com.flightapp.Flights.Services;

import java.time.LocalDate;
import java.util.LinkedHashSet;
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
import com.flightapp.Flights.Util.SeatType;
import com.flightapp.Flights.repositories.FlightRepository;

@Service
public class FlightAdminServiceImpl implements FlightAdminService {
	
	@Autowired
	FlightRepository flightRepository;

	@Override
	public FlightDTO addFlight(FlightDTO flightDTO) {
		Flight flight = getFlightObject(flightDTO);
		Flight save = flightRepository.save(flight);
		FlightDTO entitytoDTO = Convert.entitytoDTO(flight);
		return entitytoDTO;
	}

	private Flight getFlightObject(FlightDTO flightDTO) {
		Flight flight = Convert.dtotoEntity(flightDTO);
		flight.setStartTime(DateTimeAPI.getDate_Time(flightDTO.getStartTime()));
		flight.setDate(LocalDate.of(
				DateTimeAPI.getDate_Time(flightDTO.getStartTime()).getYear(),
				DateTimeAPI.getDate_Time(flightDTO.getStartTime()).getMonth(),
				DateTimeAPI.getDate_Time(flightDTO.getStartTime()).getDayOfMonth()));
		flight.setEndTime(DateTimeAPI.getDate_Time(flightDTO.getEndTime()));
		String totalTime = DateTimeAPI.getDateTimeBetween(flight.getStartTime(), flight.getEndTime());
		flight.setTotalTime(totalTime);
		flight.setTotalSeatInBusinessClass(flightDTO.getAvailableSeatInBusinessClass());
		flight.setTotalSeatInNonBusinessClass(flightDTO.getAvailableSeatInNonBusinessClass());
		flight.setAvailable(true);
		flight.setTotalSeat(flight.getTotalSeatInBusinessClass()+flight.getTotalSeatInNonBusinessClass());
		Set<Seats> seatdetails = seatdetails(flight, flight.getTotalSeatInNonBusinessClass(), flight.getTotalSeatInBusinessClass());
		flight.setSeats(seatdetails);
		flight.setFstatus(FlightStatus.UnBlocked.toString());
		return flight;
	}
	
	
	//making set object of seat to save in database
	private Set<Seats> seatdetails(Flight flight, int NonBusinessClass, int BusinessClass){
		Set<Seats> seats = new LinkedHashSet<>(NonBusinessClass+BusinessClass);
		int i=1;
		while(i<=NonBusinessClass+BusinessClass) {
			String type;
			if(i<=NonBusinessClass) {
				type = SeatType.NonBusiness.toString();
			}
			else {
				type = SeatType.Business.toString();
			}
			Seats seat = new Seats(flight, i, type, false);
			seats.add(seat);
			++i;
		}
		return seats;
	}
	
	
	@Transactional
	@Override
	public List<FlightDTO> blockFlight(long flightId) throws RuntimeException {
		 Optional<Flight> flightOptional = flightRepository.findById(flightId);
		 
		 Flight flight = flightOptional.orElseThrow(()->{
			 return new CustomException(String.format("There is No fight available with this id = %d",flightId), HttpStatus.NOT_FOUND); 
		 });
		
		 if(flight.getFstatus().equals(FlightStatus.Blocked.toString())) {
			 throw new CustomException(String.format("The Flight is allready blocked with id : %d",flightId), HttpStatus.NOT_ACCEPTABLE);
		 }
		 
		 flight.setFstatus(FlightStatus.Blocked.toString());
		 flightRepository.save(flight);
		 return getAll();
	}

	@Override
	public List<FlightDTO> getAll() throws RuntimeException{
		List<Flight> findAll = (List<Flight>) flightRepository.findAll();
		if(findAll.size()==0) {
			throw new RuntimeException("No Flights available");
		}
		List<FlightDTO> collect = findAll.stream().map(x->
				 Convert.entitytoDTO(x)
				).collect(Collectors.toList());
		return collect;
	}

	//extra feature in case required.
	@Override
	public List<FlightDTO> updateFlight(long FlightID, FlightDTO flighhDTO) {
		Flight flightObject = getFlightObject(flighhDTO);
		return null;
	}	
}
