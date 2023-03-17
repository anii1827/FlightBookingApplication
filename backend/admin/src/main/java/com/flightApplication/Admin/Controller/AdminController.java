package com.flightApplication.Admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightApplication.Admin.DTO.FlightDTO;
import com.flightApplication.Admin.DTO.SuccessResponse;
import com.flightApplication.Admin.Exception.CustomException;
import com.flightApplication.Admin.Services.AdminService;
import com.flightApplication.Admin.security.JWTRequestDTO;
import com.flightApplication.Admin.security.JWTResponse;
import com.flightApplication.Admin.security.JWTUserDetailsService;
import com.flightApplication.Admin.security.JwtTokenUtil;

@RestController
@RequestMapping("/admin/v1.0/api")
public class AdminController {

	@Autowired
	AdminService service;

//		@Autowired
//		AuthenticationManager authManger;
//		
//		@Autowired
//		JWTUserDetailsService userDeatilsService;

	@Autowired
	JwtTokenUtil JwtUtil;

	@PostMapping("/inventory/add")
	public List<FlightDTO> addTheFlightDTO(@RequestBody FlightDTO dto) {
		if (dto == null) {
			throw new CustomException("provide the request body", HttpStatus.NOT_ACCEPTABLE);
		}
		System.out.println("working");
		return service.addFlight(dto);
	}

	@PutMapping("/inventory/Schedule")
	public List<FlightDTO> sechduleTheFlight(@RequestParam("flightId") Long flightId,
			@RequestParam("starttime") String StartTime, @RequestParam("endtime") String endTime) {
		if (flightId == null && "".equals(StartTime) && "".equals(endTime)) {
			throw new CustomException("Provide the request Parameter", HttpStatus.NOT_ACCEPTABLE);
		}
		List<FlightDTO> sechduledFlight = service.sechduledFlight(flightId, StartTime, endTime);
		return sechduledFlight;
	}

	@PutMapping("/inventory/block")
	public ResponseEntity<?> blockTheFlight(@RequestParam("flightId") Long FlightId) {
		if (FlightId == null) {
			throw new CustomException("Provide the request Parameter", HttpStatus.NOT_ACCEPTABLE);
		}
		boolean blockTheFlight = service.blockTheFlight(FlightId);
		if (blockTheFlight) {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Flight Has Been Blocked successfully!", HttpStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>("Unable to block the Flight!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("")
	public List<FlightDTO> getAllFlight() {
		return service.getAll();
	}

}
