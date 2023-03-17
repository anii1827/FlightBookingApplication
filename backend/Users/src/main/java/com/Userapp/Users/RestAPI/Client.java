package com.Userapp.Users.RestAPI;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.Userapp.Users.DTO.FlightDTO;
import com.Userapp.Users.Util.Constant;

@FeignClient(name = "FLIGHT-SERVICE")
public interface Client {
	
	@GetMapping(Constant.FlightSearch+"/search")
	List<FlightDTO> getall(@RequestParam("source") String source, @RequestParam("destination") String Desination, @RequestParam("date") String date);
	
	@PostMapping(Constant.FlightSearch+"")
	List<Integer> bookTheFlight(@RequestBody Set<Integer> seat, @RequestParam("flightId") long flightId, @RequestParam("businessClassSeat") int businessClassSeat, @RequestParam("NonbusinessclassSeat") int NonbusinessclassSeat);
	
	@DeleteMapping(Constant.FlightSearch+"")
	public ResponseEntity<?> cancelFlight(@RequestBody Set<Integer> seat, @RequestParam("flightId") long flightId,@RequestParam("businessClassSeat") int businessClassSeat,@RequestParam("NonbusinessclassSeat") int NonbusinessclassSeat);
	
	@GetMapping(Constant.FlightSearch+"/get")
	public FlightDTO getFlight(@RequestParam long flightId);
	
	@GetMapping(Constant.FlightSearch+"/check")
	public String checking();
}
