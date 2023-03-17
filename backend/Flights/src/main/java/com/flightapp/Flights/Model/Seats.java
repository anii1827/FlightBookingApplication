package com.flightapp.Flights.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Seats implements Serializable{
	 
	private static final long serialVersionUID = -2402971478454404437L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private int SeatNumber;
	private boolean Booked;
	private String type;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flight_id", nullable = true)
	private Flight Flight;
	
	public Seats(Flight flight, int seatNumber, String type, boolean booked) {
		this.SeatNumber = seatNumber;
		this.Booked = booked;
		this.type = type;
		this.Flight = flight;
	}
	
	public Seats() {
	}

	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public int getSeatNumber() {
		return SeatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		SeatNumber = seatNumber;
	}
	public boolean isBooked() {
		return Booked;
	}
	public void setBooked(boolean booked) {
		Booked = booked;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Flight getFlight() {
		return Flight;
	}
	public void setFlight(Flight flight) {
		Flight = flight;
	}
	
	
	
	
	
	
}
