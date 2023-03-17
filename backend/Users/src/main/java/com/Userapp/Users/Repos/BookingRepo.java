package com.Userapp.Users.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Userapp.Users.Model.Booking;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {
		public Booking findBypnrAndPhoneNumber(String PNR,String Phone);
		public List<Booking> findByPhoneNumber(String number);
		public List<Booking> findByFlightID(Long Flight_id);
}
