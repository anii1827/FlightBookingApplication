package com.flightApplication.Admin.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightApplication.Admin.DTO.FlightDTO;
import com.flightApplication.Admin.Exception.CustomException;
import com.flightApplication.Admin.Model.Flight;
import com.flightApplication.Admin.Repository.AdminRepository;
import com.flightApplication.Admin.RestAPI.Client;
import com.flightApplication.Admin.Util.Constant;
import com.flightApplication.Admin.Util.Convert;
import com.flightApplication.Admin.kafka.KafkaMessager;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	Client client;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired 
	KafkaMessager message;
	
	@Override
	public List<FlightDTO> addFlight(FlightDTO FlightDTO) {
		try {
			Flight flight = Convert.dtoToEntity(FlightDTO);
			flight.setRegisterDate(LocalDateTime.now());
			flight.setSechduled(false);
			flight.setTotalSeat(FlightDTO.getTotalSeatInNonBusinessClass()+FlightDTO.getTotalSeatInBusinessClass());
			System.out.println(flight);
			adminRepository.save(flight);
			return getAll();
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public boolean blockTheFlight(Long Inventory_id) {
		try {
		Optional<Flight> optional = adminRepository.findById(Inventory_id);
		Flight flight = optional.get();
		long longValue = flight.getFlight_id().longValue();
		String valueOf = String.valueOf(longValue);
		client.blockFlight(longValue);
		message.sendMessageToConsumer(Constant.KafkaTopic, valueOf);
		return true;
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public List<FlightDTO> getAll() {
		try {
			List<Flight> list = (List<Flight>)adminRepository.findAll();
			List<FlightDTO> collect = list.stream().map((x)->{
				return Convert.entityToDTO(x);
			}).collect(Collectors.toList());
			return collect;
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<FlightDTO> sechduledFlight(Long Inventory_id, String startTime, String endtime) {
		try {
			Optional<Flight> findById = adminRepository.findById(Inventory_id);
			Flight flight = findById.orElseThrow(()-> new CustomException("No flight is avaialbe with FlightId "+ Inventory_id, HttpStatus.NOT_FOUND));
			if(flight.isSechduled()) {
				throw new CustomException("The Flight with id "+Inventory_id+" has been allready sechduled!", HttpStatus.NOT_ACCEPTABLE);
			}
			FlightDTO flightDto = Convert.entityToDTO(flight);
			flightDto.setStartTime(startTime);
			flightDto.setEndTime(endtime);
			flightDto.setAvailableSeatInBusinessClass(flight.getTotalSeatInBusinessClass());
			flightDto.setAvailableSeatInNonBusinessClass(flight.getTotalSeatInNonBusinessClass());
			System.out.println("object: "+ flightDto);
			FlightDTO addFlight = client.AddFlight(flightDto); //call the Flight APIT to book the flight
			Long flight_id = addFlight.getFlightId();//this data return from fligt api after adding flight
			System.out.println(flight_id);
			flight.setFlight_id(flight_id);
			flight.setSechduled(true);
			adminRepository.save(flight);
			return getAll();
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




}
