package com.flightapp.Flights.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.Flights.DTO.FlightDTO;
import com.flightapp.Flights.Exception.CustomResponseData;
import com.flightapp.Flights.Services.FlightCustomerService;

@RestController
@RequestMapping("/api/v1.0/flight/customer")
public class FlightCustomerController {
	
	private static final Logger logger = LogManager.getLogger(FlightAdminController.class);
	
	@Autowired
	FlightCustomerService service;
	
	@GetMapping("/search")
	public List<FlightDTO> search(@RequestParam("source") String source, @RequestParam("destination") String desination, @RequestParam("date") String date) {
		if(source.isEmpty() || source.isBlank()) {
		 	logger.error(String.format("received the null value for source"));
		 	throw new IllegalArgumentException(String.format("source should not be null or empty"));
		}
		if(desination.isEmpty() || desination.isBlank()) {
		 	logger.error(String.format("received the null value for destination"));
		 	throw new IllegalArgumentException(String.format("destination should not be null or empty"));
		}
		if(date.isEmpty() || date.isBlank()) {
			logger.error(String.format("received the null value for date"));
		 	throw new IllegalArgumentException(String.format("date should not be null or empty"));
		}
		 List<FlightDTO> searchFlight = service.searchFlight(source, desination, date);
		 System.out.println(searchFlight);
		 return searchFlight;
	}
	
	@GetMapping("")	
	public List<FlightDTO> getAllFlight() {
		return service.getAllFlight();
	}
	
	@PostMapping("")
	public List<Integer> bookFlight(@RequestBody Set<Integer> seat, @RequestParam("flightId") long flightId, @RequestParam("businessClassSeat") int businessClassSeat, @RequestParam("NonbusinessclassSeat") int NonbusinessclassSeat) {
		 return service.bookFlight(flightId, businessClassSeat, NonbusinessclassSeat, seat);
	}
	
	@DeleteMapping("")
	public ResponseEntity<?> cancelFlight(@RequestBody Set<Integer> seat, @RequestParam("flightId") long flightId,@RequestParam("businessClassSeat") int businessClassSeat,@RequestParam("NonbusinessclassSeat") int NonbusinessclassSeat) {
		ResponseEntity<?> responseEntity = new ResponseEntity<String>(new String("unable to perform the request"), HttpStatus.INTERNAL_SERVER_ERROR);
		if(service.cancelFlight(flightId, businessClassSeat, NonbusinessclassSeat, seat)) {
			responseEntity = new ResponseEntity<CustomResponseData>(
					new CustomResponseData("Flight Application",LocalDateTime.now().toString(),HttpStatus.OK,"Booking has been canceled"),
					HttpStatus.OK
					);
		}
		return responseEntity;
	}	
	
	
	@GetMapping("/get")
	public FlightDTO getFlight(@RequestParam("flightId") long flightId) {
		return service.getFlight(flightId);
	}
	
	
	@GetMapping("/check")
	public String checking() {
		System.out.println("it came here in custtommer controller");
		System.out.println("its working");
		return "it's working";
	}
}
