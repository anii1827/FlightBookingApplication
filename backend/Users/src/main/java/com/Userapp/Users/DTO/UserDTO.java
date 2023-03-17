package com.Userapp.Users.DTO;

import java.util.Set;

import com.Userapp.Users.Model.Booking;

public class UserDTO {
		private String name;
		private String email;
		private String phoneNumber;
		private Long flightId;
		private Set<PassangerDTO> passangerDetails;
		public String getName() {   
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public Set<PassangerDTO> getPassangerDetails() {
			return passangerDetails;
		}
		public void setPassangerDetails(Set<PassangerDTO> passangerDetails) {
			this.passangerDetails = passangerDetails;
		}
		public Long getFlightId() {
			return flightId;
		}
		public void setFlightId(Long flightId) {
			this.flightId = flightId;
		}
		
		
}
