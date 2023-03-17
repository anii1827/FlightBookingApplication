package com.Userapp.Users.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Userapp.Users.DTO.BookingDTO;
import com.Userapp.Users.DTO.FlightDTO;
import com.Userapp.Users.DTO.SuccessResponse;
import com.Userapp.Users.DTO.UserDTO;
import com.Userapp.Users.Exception.CustomException;
import com.Userapp.Users.RestAPI.Client;
import com.Userapp.Users.Services.UserService;

import feign.FeignException;

@RestController
@RequestMapping("/user/v1.0/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	Client client;
	
	@PostMapping("")
	public ResponseEntity<?> bookflight(@RequestBody(required = false) UserDTO dto) {
		System.out.println(" check");
		if(dto==null || dto.getPassangerDetails().size()==0) {
			System.out.println("check");
			throw new CustomException("Provide the requestBody to book a flight", HttpStatus.NOT_ACCEPTABLE);
		}
		String PNR = userService.bookTheFlight(dto);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Your booking has been Confirmed!<br> ticket PNR number has been sent to your mail ID.<br> PNR : "+PNR, HttpStatus.OK), HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<?> cancelflight(@RequestParam("PhoneNumber") String MobileNumber,@RequestParam("PNR") String PNR) {
		if("".equals(MobileNumber) || "".equals(PNR)) {
			throw new CustomException("Provide the request Parameter", HttpStatus.NOT_ACCEPTABLE);
		}else {
			boolean cancelTheFlight = userService.cancelTheFlight(MobileNumber, PNR);
			if(cancelTheFlight) {
				return new ResponseEntity<SuccessResponse>(new SuccessResponse("your booking has been cancel Sucessfully!", HttpStatus.OK), HttpStatus.OK);
			}
		}
		return null;
	}
	
	@GetMapping("")
	public List<FlightDTO> getFlight(@RequestParam("source") String source,@RequestParam("destination") String Desination,@RequestParam("date") String date) {
		System.out.println(source+" "+Desination+" "+date);
		if("".equals(source) && "".equals(Desination) && "".equals(date)) {
			throw new CustomException("Provide the request Parameter", HttpStatus.NOT_ACCEPTABLE);
		}
		List<FlightDTO> search = userService.search(source, Desination, date);
		System.out.println(search);
		return search;
	}
	
	@GetMapping("/history")
	public ResponseEntity<?> getHistoy(@RequestParam("PhoneNumber") Long PhoneNumber) {
		String valueOf = String.valueOf(PhoneNumber);
		System.out.println(valueOf);
		if("".equals(valueOf)) {
			throw new CustomException("Provide the request Parameter", HttpStatus.NOT_ACCEPTABLE);
		}
		List<BookingDTO> history = userService.getHistory(valueOf);
		return new ResponseEntity<List<BookingDTO>>(history,HttpStatus.OK);
	}
	
	@GetMapping("/check")
	public String check() {
		try {
		return client.checking();
		}		
		catch(FeignException f) {
			throw new CustomException(f.getMessage(), HttpStatus.valueOf(f.status()));
		}
	}
	
	@GetMapping("/getBookingDetails")
	public BookingDTO getBooking(@RequestParam String PNR, @RequestParam String PhoneNumber) {
		return userService.getBooking(PNR, PhoneNumber);
	}
	
}
