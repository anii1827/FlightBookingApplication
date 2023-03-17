package com.flightapp.Flights.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.Flights.Model.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
	List<Flight> findBySourceAndDestinationAndDate(String Source, String Destination, LocalDate Date);
}
