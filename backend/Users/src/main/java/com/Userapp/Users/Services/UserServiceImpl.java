package com.Userapp.Users.Services;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Userapp.Users.Exception.*;
import com.Userapp.Users.DTO.BookingDTO;
import com.Userapp.Users.DTO.FlightDTO;
import com.Userapp.Users.DTO.PassangerDTO;
import com.Userapp.Users.DTO.UserDTO;
import com.Userapp.Users.Model.Booking;
import com.Userapp.Users.Model.Passanger;
import com.Userapp.Users.Repos.BookingRepo;
import com.Userapp.Users.RestAPI.Client;
import com.Userapp.Users.Util.Convert;
import com.Userapp.Users.Util.DateTimeAPI;
import com.Userapp.Users.Util.PNR;
import com.Userapp.Users.Util.SeatType;
import com.Userapp.Users.emailService.Email;
import com.Userapp.Users.emailService.EmailBody;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BookingRepo bookingRepository;
	
	@Autowired
	Client client;
	
	@Autowired
	Email email;
	
	@Transactional
	@Override
	public String bookTheFlight(UserDTO dto) {
		try {
			Booking bookingdetails = getTheBookingObject(dto);
			Booking save = bookingRepository.save(bookingdetails);
			updatingTheFlightData(dto);
			sendBookingEmail(save.getPnr(), save.getEmail());
			return save.getPnr();
		}
		catch(IllegalArgumentException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
	//updating the flight microservice RestAPI Calling
	private List<Integer> updatingTheFlightData(UserDTO dto) {
		
			Long flightId = dto.getFlightId();
			int bussinesSeat = 0;
			int NonBusinessSeat = 0;
				for(PassangerDTO x : dto.getPassangerDetails()){
				if(x.getSeatType().equals(SeatType.Business.toString())){
					++bussinesSeat;
				}
				else {
					++NonBusinessSeat;
				}
			}
			Set<PassangerDTO> passangerDetails = new HashSet<>(dto.getPassangerDetails());
			Set<Integer> collect = passangerDetails.stream().map((x)->{
				return x.getSeatNumber();
			}).collect(Collectors.toSet());
			List<Integer> bookTheFlight = null;
			try {	
			bookTheFlight = client.bookTheFlight(collect, flightId, bussinesSeat, NonBusinessSeat);
			}
			catch(Exception e) {
				throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return bookTheFlight;
		
	}

	
	
	private Booking getTheBookingObject(UserDTO dto) {
		try {
		Booking booking = new Booking();
		FlightDTO flight = client.getFlight(dto.getFlightId());
		Set<Passanger> collect = dto.getPassangerDetails().stream().map((x)->{
		Passanger dtoToEntity = Convert.DtoToEntity(x);
		dtoToEntity.setBooking(booking);
		return dtoToEntity;
		}).collect(Collectors.toSet());
		booking.setPnr(PNR.getPNR());
		booking.setBookingDate(LocalDateTime.now());
		booking.setPassanger(collect);
		booking.setPhoneNumber(String.valueOf(dto.getPhoneNumber()));
		booking.setFlightID(flight.getFlightId());
		booking.setSource(flight.getSource());
		booking.setDestination(flight.getDestination());
		booking.setFlightNo(flight.getFlightNo());
		booking.setStartDate(flight.getStartTime());
		booking.setStartTime(flight.getStartTimeinHoursandMinute());
		booking.setBoardingTime(DateTimeAPI.getBoardingTime(flight.getStartTimeinHoursandMinute()));
		booking.setName(dto.getName());
		booking.setEmail(dto.getEmail());
		return booking;
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
	@Override
	public List<FlightDTO> search(String source, String destination, String date) {
		try {
		List<FlightDTO> getall=null;
				getall = client.getall(source, destination, date);
				if(getall==null) {
					throw new CustomException("No Flight Avaialbe", HttpStatus.ACCEPTED);
				}
				return (List<FlightDTO>) getall;
		}
		catch(Exception e) {
			throw new CustomException(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@Transactional
	@Override
	public boolean cancelTheFlight(String PhoneNumber, String PNR) {
			Booking bookingDetails = bookingRepository.findBypnrAndPhoneNumber(PNR, PhoneNumber);
			if(bookingDetails==null) {
				throw new CustomException("PNR number or phone number is incorrect. check and try again!", HttpStatus.NOT_ACCEPTABLE);
			}
//			System.out.println(bookingDetails);	
			if(isBefore24hours(bookingDetails.getStartDate())) {
				
			int businessSeat=0;
			int nonBusinessSeat=0;
			for(Passanger p: bookingDetails.getPassanger())
			{
				if(p.getSeatType().equals(SeatType.Business.toString())) {
					++businessSeat;
				}
				else {
					++nonBusinessSeat;
				}
			}
			Set<Integer> seats = bookingDetails.getPassanger().stream().map((x)->{
				return x.getSeatNumber();
			}).collect(Collectors.toSet());
				
			ResponseEntity<?> c = client.cancelFlight(seats, Long.valueOf(bookingDetails.getFlightID()), businessSeat, nonBusinessSeat);
			bookingRepository.delete(bookingDetails);
			
			}else {
				throw new CustomException("flight cancelation only available before 24 hours flight Timing!", HttpStatus.NOT_ACCEPTABLE);
			}
			return true;
	}

	
	//comparing the flight timeing and returning the boolean value
	private boolean isBefore24hours(String StartDate) {
//		System.out.println(StartDate);
		try {
		LocalDateTime dateTime = DateTimeAPI.convertStringtoLocalDateTime(StartDate);
		LocalDateTime minusHours = dateTime.minusHours(24);
//		System.out.println(minusHours);
		boolean before = minusHours.isAfter(LocalDateTime.now());
		return before;
		}
		catch(DateTimeException e) {
			throw new CustomException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	//get booking history
	@Override
	public List<BookingDTO> getHistory(String PhoneNumber) {
//			System.out.println("check");
//			System.out.println(bookingrepository.findByPhoneNumber(PhoneNumber)); 
			List<Booking> findByPhoneNumber = bookingRepository.findByPhoneNumber(PhoneNumber);
			if(findByPhoneNumber.size()<=0) {
				throw new CustomException("No Previous Booking avaiable", HttpStatus.NOT_ACCEPTABLE);
			}
			List<BookingDTO> collect = findByPhoneNumber.stream().map((x)->{
				BookingDTO dtoToEntity = Convert.DtoToEntity(x);
				return dtoToEntity;
			}).collect(Collectors.toList());
			if(collect.size()<=0) {
				throw new CustomException("No Previous Booking avaiable", HttpStatus.NOT_ACCEPTABLE);
			}
//			System.out.println(collect);
			return collect;
	}

	@Override
	public BookingDTO getBooking(String PNR, String phoneNumber) {
		Booking bookingDetails = bookingRepository.findBypnrAndPhoneNumber(PNR, phoneNumber);
		if(bookingDetails==null) {
			throw new CustomException("PNR number or phone number is incorrect. check and try again!", HttpStatus.NOT_ACCEPTABLE);
		}	
		return Convert.DtoToEntity(bookingDetails);		
	}


	@Override
	public void sendBlockEmail(String flight_id) {
		System.out.println(flight_id);
		Long valueOf = Long.valueOf(flight_id);
		List<Booking> booking = bookingRepository.findByFlightID(valueOf);
		if(booking.size()>0) {
			String source = booking.get(0).getSource();
			String destination = booking.get(0).getDestination();
			String startDate = booking.get(0).getStartDate();
			String[] array = booking.stream().map(((x)->{
				return x.getEmail();
			})).collect(Collectors.toList()).toArray(String[]::new);
			
			
			String Message = "Dear Customer,\n\nYour Flight From "+source+ " to " +destination+ " on " +startDate+ " has been Postponed due to some Wheather issue.\nwe have initiated your "
					+ "Amount Refund Policy which will take 24 hours Working days time. you will Receive your amount shortly. we are Really Sorry for this Inconvenience.\n\n Thank you\n ";    
			
			String subject = "Flight Postponed";
			email.sendEmail(new EmailBody(array, Message, subject));
		}
				
	}


	@Override
	public void sendBookingEmail(String PNR, String emailId) {
		String Message = "Dear Customer,\n\nYour Flight has been booked successfully."
				+ "your PNR number "+PNR+".\n\n Thank you\n ";    
		
		String subject = "Booking Confirmed";
		String[] array = new String[]{emailId};
		email.sendEmail(new EmailBody(array, Message, subject));
	}	
}


