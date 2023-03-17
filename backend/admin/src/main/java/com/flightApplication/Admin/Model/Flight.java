package com.flightApplication.Admin.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "inventory")
@Entity(name = "Flight")
public class Flight {
		
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long inventory_Id;
	 private Long Flight_id;
	 private String FlightNo;
	 public String getFlightNo() {
		return FlightNo;
	}
	public void setFlightNo(String flightNo) {
		FlightNo = flightNo;
	}
	private String source; 
	 private String destination;
	 private String airLine;
	 private int totalSeat;
	 private LocalDateTime RegisterDate;
	 private int TotalSeatInBusinessClass;
	 private int TotalSeatInNonBusinessClass;
	 private double TicketPriceForBusinessClass;
	 private double TicketPriceForNonBusinessClass;
	 private boolean isSechduled;
	public Long getInventory_Id() {
		return inventory_Id;
	}
	public void setInventory_Id(Long inventory_Id) {
		this.inventory_Id = inventory_Id;
	}
	public Long getFlight_id() {
		return Flight_id;
	}
	public void setFlight_id(Long flight_id) {
		this.Flight_id = flight_id;
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
	public String getAirLine() {
		return airLine;
	}
	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public LocalDateTime getRegisterDate() {
		return RegisterDate;
	}
	public void setRegisterDate(LocalDateTime registerDate) {
		this.RegisterDate = registerDate;
	}
	public int getTotalSeatInBusinessClass() {
		return TotalSeatInBusinessClass;
	}
	public void setTotalSeatInBusinessClass(int totalSeatInBusinessClass) {
		this.TotalSeatInBusinessClass = totalSeatInBusinessClass;
	}
	
	public int getTotalSeatInNonBusinessClass() {
		return TotalSeatInNonBusinessClass;
	}
	public void setTotalSeatInNonBusinessClass(int totalSeatInNonBusinessClass) {
		this.TotalSeatInNonBusinessClass = totalSeatInNonBusinessClass;
	}
	public double getTicketPriceForBusinessClass() {
		return TicketPriceForBusinessClass;
	}
	public void setTicketPriceForBusinessClass(double ticketPriceForBusinessClass) {
		this.TicketPriceForBusinessClass = ticketPriceForBusinessClass;
	}
	public double getTicketPriceForNonBusinessClass() {
		return TicketPriceForNonBusinessClass;
	}
	public void setTicketPriceForNonBusinessClass(double ticketPriceForNonBusinessClass) {
		this.TicketPriceForNonBusinessClass = ticketPriceForNonBusinessClass;
	}
	public boolean isSechduled() {
		return isSechduled;
	}
	public void setSechduled(boolean isSechduled) {
		this.isSechduled = isSechduled;
	}
	@Override
	public String toString() {
		return "Flight [inventory_Id=" + inventory_Id + "\n, Flight_id=" + Flight_id + "\n, source=" + source
				+ "\n, destination=" + destination + "\n, airLine=" + airLine +"\n, FlightNo=" + FlightNo+ "\n, totalSeat=" + totalSeat
				+ "\n, RegisterDate=" + RegisterDate + "\n, TotalSeatInBusinessClass=" + TotalSeatInBusinessClass
				+ "\n, TotalSeatInNonBusinessClass=" + TotalSeatInNonBusinessClass + "\n, TicketPriceForBusinessClass="
				+ TicketPriceForBusinessClass + "\n, TicketPriceForNonBusinessClass=" + TicketPriceForNonBusinessClass
				+ "\n, isSechduled=" + isSechduled + "]";
	}
	
	 
	
	
}
