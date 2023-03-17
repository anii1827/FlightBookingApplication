package com.flightapp.Flights.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Services.FlightAdminService;

@RestController
@RequestMapping("/api/v1.0/flight/admin")
public class FlightAdminController {
	@Autowired
	FlightAdminService adminService;
	
	@PostMapping("")
	public FlightDTO AddFlight(@RequestBody FlightDTO Fdto){
			System.out.println(Fdto);
			System.out.println();
			return adminService.addFlight(Fdto);
	}
	
//extra feature in case required.
//	@PutMapping("/{flightId}")
//	public List<FlightDTO> updateFlight(@RequestBody FlightDTO flightDTO, @PathVariable("flightId") long FlightID){
//			return adminService.updateFlight(FlightID, flightDTO);
//	}
	
	@GetMapping("")
	public List<FlightDTO> getAllFlight(){
		return adminService.getAll();
	}
	
	@PutMapping("/block/{flightId}")
	public List<FlightDTO> blockFlight(@PathVariable("flightId") Long FlightID) {
		return adminService.blockFlight(FlightID);
	}
	
}
