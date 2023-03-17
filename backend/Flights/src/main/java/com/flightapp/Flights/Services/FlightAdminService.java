package com.flightapp.Flights.Services;
import java.util.List;
import com.flightapp.Flights.DTO.FlightDTO;

public interface FlightAdminService {

	public FlightDTO addFlight(FlightDTO flighhDTO);
    public List<FlightDTO> blockFlight(long flightId);
    public List<FlightDTO> getAll();
    public List<FlightDTO> updateFlight(long FlightID, FlightDTO flighhDTO);
	
}
