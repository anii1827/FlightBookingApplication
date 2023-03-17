package com.Userapp.Users.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class PassangerDTO implements Serializable{
	private static final long serialVersionUID = 7566013758366712135L;
			private String firstname;
			private String lastName;
			private LocalDate dateOfBirth;
			private int seatNumber;
			private String seatType;

			public PassangerDTO() {
				// TODO Auto-generated constructor stub
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
				return seatType;
			}
			public void setSeatType(String seatType) {
				this.seatType = seatType;
			}
}	
