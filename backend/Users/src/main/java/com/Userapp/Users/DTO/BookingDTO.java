package com.Userapp.Users.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.Userapp.Users.Model.Passanger;

public class BookingDTO implements Serializable {
	
	private static final long serialVersionUID = -4241586556214653040L;
	private long bookingId;
	private LocalDateTime bookingDate;
	private String source;
	private String destination;
	private String flightNo;
	private String startDate;
	private String startTime;
	private String boardingTime;
	private String pnr;
	
	
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	private Set<PassangerDTO> passanger;
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
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
	public Set<PassangerDTO> getPassanger() {
		return passanger;
	}
	public void setPassanger(Set<PassangerDTO> passanger) {
		this.passanger = passanger;
	}
	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", source=" + source
				+ ", destination=" + destination + ", flightNo=" + flightNo + ", startDate=" + startDate
				+ ", startTime=" + startTime + ", boardingTime=" + boardingTime + ", pnr=" + pnr + ", passanger="
				+ passanger + "]";
	}
	
	
	
	
	
	
	
}
