package com.flightApplication.Admin.RestAPI;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flightApplication.Admin.DTO.FlightDTO;
import com.flightApplication.Admin.Util.Constant;


@FeignClient(name = "FLIGHT-SERVICE")
public interface Client {

	
	@PostMapping(Constant.url)
	public FlightDTO AddFlight(@RequestBody FlightDTO Fdto);
	
	@PutMapping(Constant.url +"/block/{flightId}")
	public List<FlightDTO> blockFlight(@PathVariable("flightId") Long FlightID);
	
	
}
