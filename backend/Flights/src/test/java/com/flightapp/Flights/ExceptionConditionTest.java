package com.flightapp.Flights;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.flightapp.Flights.Services.FlightCustomerService;
import com.flightapp.Flights.Util.DateTimeAPI;

@SpringBootTest
public class ExceptionConditionTest {
	
	
//	@BeforeEach
//	void setproperties() {
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getDateFormatfromAdmin", "d-MM-yyyy HH:mm");
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getDateFormat", "EEEE, d MMM yy HH:mm");
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getTimeFormat", "HH:mm");
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getDate", "yyyy-MM-d");
//	}
//	
//	@Autowired
//	FlightCustomerService Cservice;
//	
//	@Test
//	void bookingExceptionTest() {
//		Set<Integer> seat = new HashSet<>();
//		seat.add(1);
//		seat.add(2);
//		seat.add(6);
//		seat.add(7);
//		Cservice.bookFlight(6l,2,2, seat);
//	}
//	
}
