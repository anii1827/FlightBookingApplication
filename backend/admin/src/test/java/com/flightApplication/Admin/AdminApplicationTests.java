package com.flightApplication.Admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flightApplication.Admin.DTO.FlightDTO;
import com.flightApplication.Admin.Services.AdminService;
import com.flightApplication.Admin.Util.Constant;
import com.flightApplication.Admin.kafka.KafkaMessager;

@SpringBootTest
class AdminApplicationTests {
	
//	@Autowired
//	AdminService adminService;
//	
//	@Autowired 
//	KafkaMessager message;
//	
//	@Test
//	void contextLoads() {
////		private String source; 
////		 private String destination;
////		 private String airLine;
////		 private int TotalSeatInBusinessClass;
////		 private int TotalSeatInNonBusinessClass;
////		 private double TicketPriceForBusinessClass;
////		 private double TicketPriceForNonBusinessClass;
//		FlightDTO flight = new FlightDTO();
//		flight.setAirLine("jatAirWays");
//		flight.setSource("indore");
//		flight.setDestination("bangalore");
//		flight.setFlightNo("Jat-302");
//		flight.setTotalSeatInNonBusinessClass(30);
//		flight.setTotalSeatInBusinessClass(30);
//		flight.setTicketPriceForBusinessClass(3000);
//		flight.setTicketPriceForNonBusinessClass(2000);
//		adminService.addFlight(flight);
//	}
//	
//	@Test
//	void sechduledFlight() {
//			DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm");
//			LocalDateTime start = LocalDateTime.now().plusDays(2);
//			LocalDateTime end = LocalDateTime.now().plusDays(3);
//			adminService.sechduledFlight(13l, format.format(start), format.format(end));
//	}
//	
//	@Test
//	void blockTheFlight() {
//		adminService.blockTheFlight(19l);
////		message.sendMessageToConsumer(Constant.KafkaTopic, 15l);
//	}

}
