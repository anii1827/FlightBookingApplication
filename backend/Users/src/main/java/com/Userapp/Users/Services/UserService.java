package com.Userapp.Users.Services;

import java.util.List;
import java.util.Set;

import com.Userapp.Users.DTO.BookingDTO;
import com.Userapp.Users.DTO.FlightDTO;
import com.Userapp.Users.DTO.UserDTO;

public interface UserService {
	
	String bookTheFlight(UserDTO dto);

	boolean cancelTheFlight(String MobileNumber, String PNRID);
	
	List<FlightDTO> search(String source, String destination, String date);
	
	List<BookingDTO> getHistory(String PhoneNumber);
	
	BookingDTO getBooking(String PNR, String phoneNumber);
	
	void sendBlockEmail(String flight_id);
	
	void sendBookingEmail(String PNR, String email);
	
}
