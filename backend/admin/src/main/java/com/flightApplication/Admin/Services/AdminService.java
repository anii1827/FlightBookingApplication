package com.flightApplication.Admin.Services;

import java.util.List;

import com.flightApplication.Admin.DTO.FlightDTO;

public interface AdminService {
	
	public List<FlightDTO> addFlight(FlightDTO FlightDTO);
	
	public boolean blockTheFlight(Long FlightId);
	
	public List<FlightDTO> getAll();
	
	public List<FlightDTO> sechduledFlight(Long FlightId, String startTime, String endtime);


}
