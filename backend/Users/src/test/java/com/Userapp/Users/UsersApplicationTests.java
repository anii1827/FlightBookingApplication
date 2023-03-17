package com.Userapp.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Userapp.Users.Services.UserService;

@SpringBootTest
class UsersApplicationTests {
	
	@Autowired
	UserService service;
//	
//	@Autowired
//	Email email;
//	
//	@Autowired
//	BookingRepo repo;
//	
//	@Test
//	void search() {
////		List<FlightDTO> search = service.search("indore", "delhi", "11-01-2022");
//		List<FlightDTO> search2 = service.search("mumbai", "delhi", "2022-01-11");
//		assertEquals(1, search2.size());
//	}
//	
//	@Test
//	void bookingTheFlight(){
//		UserDTO dto = new UserDTO();
//		PassangerDTO p1 = new PassangerDTO();
//		p1.setFirstname("gopal");
//		p1.setLastName("tailor");
//		p1.setDateOfBirth(LocalDate.of(1995, 05, 18));
//		p1.setSeatNumber(8);
//		p1.setSeatType(SeatType.Business.toString());
//		
////		PassangerDTO p2 = new PassangerDTO();
////		p2.setFirstname("nirmala");
////		p2.setLastName("tailor");
////		p2.setDateOfBirth(LocalDate.of(1990, 07, 17));
////		p2.setSeatType(SeatType.NonBusiness.toString());
////		p2.setSeatNumber(2);
////		
////		PassangerDTO p3 = new PassangerDTO();
////		p3.setFirstname("manju");
////		p3.setLastName("makwana");
////		p3.setDateOfBirth(LocalDate.of(1994, 05, 18));
////		p3.setSeatNumber(3);
////		p3.setSeatType(SeatType.NonBusiness.toString());
//		
//		HashSet<PassangerDTO> set  = new HashSet<>();
//		set.add(p1); // set.add(p2); set.add(p3);
//		
//		dto.setPassangerDetails(set);
//		dto.setFlightId(25l);
//		dto.setPhoneNumber("9414732474");
//		dto.setEmail("aniltailor500@gmail.com");
//		dto.setName("gopal tailor");
////		boolean bookTheFlight = service.bookTheFlight(dto);
////		assertEquals(set.size(), bookTheFlight.size());
//	}
//	
//	@Test
//	void getTheBoardingTime() {
//			String time = DateTimeAPI.getBoardingTime("23:50");
//			System.out.println(time);
//			assertEquals("22.50", time);
//	}
//	
//	@Test
//	void getTheCancelDetails() {
//			service.cancelTheFlight("8952034880", "XQUsCQ");
//	}
//	
//	@Test
//	void converDateTimeStringtoLocaleDateTime() {
//		LocalDateTime datetime = DateTimeAPI.convertStringtoLocalDateTime("Thursday, 30 Dec 2021 22:50");
//		System.out.println(datetime);
////		LocalDateTime dateTime = DateTimeAPI.convertStringtoLocalDateTime(bookingDetails.getStartDate());
//		LocalDateTime minusHours = datetime.minusHours(24);
//		System.out.println(minusHours);
//		boolean before = minusHours.isAfter(LocalDateTime.now());
//		System.out.println(before);
//		assertTrue(before);
//	}
//	
//	
//	@Test
//	void getBookingHistory() {
//		List<BookingDTO> history = service.getHistory("8952034880");
//		assertNotNull(history);
//		System.out.println(history);
//		assertEquals(1, history.size());
//	}
//	
//	@Test 
//	void searchflight() {
//		List<FlightDTO> search = service.search("udaipur", "mumbai", "2022-01-10");
//		System.out.println(search);
//	}
//	
//	@Test
//	void getBooking(){
//		BookingDTO booking = service.getBooking("DTWdy7", "89520034880");
//		System.out.println(booking);
//	}
//	
//	@Test
//	void sendMessage(){
//		EmailBody body = new EmailBody(new String[] {"muthunarendran18@gmail.com","aniltailor500@gmail.com"}, "welocme to web", "no subject");
//		email.sendEmail(body);
//	}
//	
//	@Test
//	void serchBookingdetailsByFlightId(){
//			List<Booking> findByFlightID = repo.findByFlightID(25l);
//			System.out.println(findByFlightID.get(0));
//	}
	
//	@Test
//	void sendEmailfromServiceClass(){
//		service.sendBookingEmail("AbcZyx", "aniltailor500@gmail.com");
//	}
	
		
}
