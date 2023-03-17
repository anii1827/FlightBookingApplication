package com.Userapp.Users.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Passanger implements Serializable{
	private static final long serialVersionUID = 6740330732007321132L;
	
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private long PassangerId;
			private String firstname;
			private String lastName;
			private LocalDate dateOfBirth;
			private int seatNumber;
			private String SeatType;
			
			@ManyToOne(optional = false, fetch = FetchType.LAZY)
		    @JoinColumn(name = "bookid", nullable = true)
			private Booking booking;

			public long getPassangerId() {
				return PassangerId;
			}
			
			public void setPassangerId(long passangerId) {
				PassangerId = passangerId;
			}

			public String getFirstname() {
				return firstname;
			}

			public void setFirstname(String firstname) {
				this.firstname = firstname;
			}

			public String getLastName() {
				return lastName;
			}

			public void setLastName(String lastName) {
				this.lastName = lastName;
			}

			public LocalDate getDateOfBirth() {
				return dateOfBirth;
			}

			public void setDateOfBirth(LocalDate dateOfBirth) {
				this.dateOfBirth = dateOfBirth;
			}

			public int getSeatNumber() {
				return seatNumber;
			}

			public void setSeatNumber(int seatNumber) {
				this.seatNumber = seatNumber;
			}

			public String getSeatType() {
				return SeatType;
			}

			public void setSeatType(String seatType) {
				SeatType = seatType;
			}

			public Booking getBooking() {
				return booking;
			}

			public void setBooking(Booking booking) {
				this.booking = booking;
			}

			public Passanger(long passangerId, String firstname, String lastName, LocalDate dateOfBirth, int seatNumber,
					String seatType, Booking booking) {
				super();
				PassangerId = passangerId;
				this.firstname = firstname;
				this.lastName = lastName;
				this.dateOfBirth = dateOfBirth;
				this.seatNumber = seatNumber;
				SeatType = seatType;
				this.booking = booking;
			}

			public Passanger() {
			
			}

			@Override
			public String toString() {
				return "Passanger [PassangerId=" + PassangerId + ", firstname=" + firstname + ", lastName=" + lastName
						+ ", dateOfBirth=" + dateOfBirth + ", seatNumber=" + seatNumber + ", SeatType=" + SeatType
						+ ", booking=" + booking + "]";
			}
			
		
			
		
			
}	
