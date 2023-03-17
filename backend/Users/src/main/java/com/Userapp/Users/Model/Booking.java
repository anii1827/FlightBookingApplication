package com.Userapp.Users.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Booking implements Serializable{
		
	   private static final long serialVersionUID = -2975689497115984542L;
	   
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long bookingId;
		private String pnr;
		private LocalDateTime bookingDate;
		private String phoneNumber;
		private long flightID;
		private String source;
		private String destination;
		private String flightNo;
		private String startDate;
		private String startTime;
		private String boardingTime;
		private String email;
		private String name;
		
		@OneToMany(mappedBy = "booking", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		private Set<Passanger> passanger;

		public long getBookingId() {
			return bookingId;
		}

		public void setBookingId(long bookingId) {
			this.bookingId = bookingId;
		}

		public String getPnr() {
			return pnr;
		}

		public void setPnr(String pnr) {
			this.pnr = pnr;
		}

		public LocalDateTime getBookingDate() {
			return bookingDate;
		}

		public void setBookingDate(LocalDateTime bookingDate) {
			this.bookingDate = bookingDate;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public long getFlightID() {
			return flightID;
		}

		public void setFlightID(long flightID) {
			this.flightID = flightID;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getFlightNo() {
			return flightNo;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}


		public void setFlightNo(String flightNo) {
			this.flightNo = flightNo;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getBoardingTime() {
			return boardingTime;
		}

		public void setBoardingTime(String boardingTime) {
			this.boardingTime = boardingTime;
		}

		public Set<Passanger> getPassanger() {
			return passanger;
		}

		public void setPassanger(Set<Passanger> passanger) {
			this.passanger = passanger;
		}

		public Booking() {
			
		}

		public Booking(long bookingId, String pnr, LocalDateTime bookingDate, String phoneNumber, long flightID,
				String source, String destination, String flightNo, String startDate, String startTime,
				String boardingTime, String email, String firstName, String lastName, Set<Passanger> passanger) {
			this.bookingId = bookingId;
			this.pnr = pnr;
			this.bookingDate = bookingDate;
			this.phoneNumber = phoneNumber;
			this.flightID = flightID;
			this.source = source;
			this.destination = destination;
			this.flightNo = flightNo;
			this.startDate = startDate;
			this.startTime = startTime;
			this.boardingTime = boardingTime;
			this.email = email;
			this.passanger = passanger;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		
}
