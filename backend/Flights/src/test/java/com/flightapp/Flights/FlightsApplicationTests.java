package com.flightapp.Flights;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlightsApplicationTests {

//	@BeforeEach
//	void setproperties() {
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getDateFormatfromAdmin", "d-MM-yyyy HH:mm");
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getDateFormat", "EEEE, d MMM yy HH:mm");
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getTimeFormat", "HH:mm");
//		ReflectionTestUtils.setField(DateTimeAPI.class, "getDate", "yyyy-MM-d");
//	}
//	
//	@Autowired
//	FlightAdminService service;
//	
//	@Autowired 
//	FlightRepository repo;
//	
//	@Autowired
//	FlightCustomerService Cservice;
//	
//	@Test
//	void contextLoads() {
//		FlightDTO flightdto = new FlightDTO();
//		flightdto.setAirLine("jatAirways");
//		flightdto.setSource("delhi");
//		flightdto.setDestination("mumbai");
//		flightdto.setFlightNo("JAT-309");
//		flightdto.setAvailableSeatInBusinessClass(10);
//		flightdto.setAvailableSeatInNonBusinessClass(10);
//		flightdto.setStartTime("16-10-1980 15:10");
//		flightdto.setEndTime("16-10-1980 20:10");
//		flightdto = service.addFlight(flightdto);
////		System.out.println(addFlight.get(0));
////		assertEquals(1, addFlight.size());
//	}
//	@Test
//	void checkTimeDifference() {
//		String dateTimeBetween = DateTimeAPI.getDateTimeBetween(LocalDateTime.now(),LocalDateTime.now().plusDays(2).plusHours(5).plusMinutes(33));
//		assertEquals(new StringBuilder().append("53h 33m").toString(), dateTimeBetween);
//	}
//	
//	@Test
//	void stringtolocaltime(){
//		DateTimeAPI.getDate_Time("16-10-1980 15:10");
//	}
//	
//	@Test
//	void databasedatacheck() {
//		service.getAll();
//	}
//	
//	@Test
//	void blockTheFlight() {
//		List<FlightDTO> blockFlight = service.blockFlight(6l);
//		//blockFlight.stream().filter(x->x.getFlightId()==24l).forEach(System.out::println);
//	}
//	
//	@Test
//	void bookTheFlight(){
//		Set<Integer> seat = new HashSet<>();
//		seat.add(1);
//		seat.add(2);
//		seat.add(6);
//		seat.add(7);
//		Cservice.bookFlight(2l,2,2, seat);
//	}
//	
//	@Test
//	void cancelTheFlight(){
//		Set<Integer> seat = new HashSet<>();
//		seat.add(1);
//		seat.add(2);
//		seat.add(6);
//		seat.add(7);
//		Cservice.cancelFlight(2l,2,2, seat);
//	}
//	
//	@Test
//	void converStringIntoLocalDate() {
//		LocalDate date = DateTimeAPI.getDate("1980-08-8");
//		assertEquals(8,date.getDayOfMonth());
//		assertEquals(8, date.getMonthValue());
//		assertEquals(1980, date.getYear());
//	}
//	
//	@Test
//	void SearchFFlightinRepository() {
//		LocalDate of = LocalDate.of(1980, 10, 16);
//		System.out.println(of);
//		List<Flight> findBySourceAndDestinationAndDate = repo.findBySourceAndDestinationAndDate("bangalore", "indore", of);
//		assertEquals(2, findBySourceAndDestinationAndDate.size());
//	}
//	
//	@Test
//	void SearchFFlightinService() {
//		LocalDate of = LocalDate.of(1980, 10, 16);
//		System.out.println(of);
//		List<FlightDTO> findBySourceAndDestinationAndDate = Cservice.searchFlight("udaipur", "mumbai", "2022-01-17");
//		System.out.println();
//		findBySourceAndDestinationAndDate.stream().forEach(System.out::println);
//		System.out.println();
//		assertEquals(1, findBySourceAndDestinationAndDate.size());
//	} 
//	
//	@Test
//	void getallFlightinService() {
//		List<FlightDTO> allFlight = Cservice.getAllFlight();
//		assertEquals(2, allFlight.size());
//	} 
//	

}