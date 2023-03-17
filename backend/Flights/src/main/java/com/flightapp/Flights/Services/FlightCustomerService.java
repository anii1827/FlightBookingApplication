package com.flightapp.Flights.Services;

import java.util.List;
import java.util.Set;
import com.flightapp.Flights.DTO.FlightDTO;

public interface FlightCustomerService {
		public List<Integer> bookFlight(long flightId, int businessSeat, int nonBusinessSeat, Set<Integer> seat);
		public boolean cancelFlight(long flightId, int businessSeat, int nonBusinessSeat, Set<Integer> seat);
		public List<FlightDTO>searchFlight(String source, String destination, String date);
		public List<FlightDTO> getAllFlight();
		public FlightDTO getFlight(Long Id);
}