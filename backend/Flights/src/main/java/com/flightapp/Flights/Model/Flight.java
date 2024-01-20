package com.flightapp.Flights.Model;

import java.io.Serializable;
import java.time.LocalDate;
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
public class Flight implements Serializable{
	 
	private static final long serialVersionUID = 6890593814747657183L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long FlightId;
	 private String FlightNo;
	 private String source; 
	 private String destination;
	 private String AirLine;
	 private int TotalSeat;
	 private LocalDate date;
	 private LocalDateTime StartTime;
	 private LocalDateTime EndTime;
	 private boolean Available;
	 private int TotalSeatInBusinessClass;
	 private int TotalSeatInNonBusinessClass;
	 private String TotalTime;
	 private int AvailableSeatInBusinessClass;
	 private int AvailableSeatInNonBusinessClass;
	 private double TicketPriceForBusinessClass;
	 private double TicketPriceForNonBusinessClass;
	 private String Fstatus;
	 @OneToMany(mappedBy = "Flight", fetch = FetchType.EAGER,
	            cascade = CascadeType.ALL)
	    private Set<Seats> seats;
	 
	 public Set<Seats> getSeats() {
		return seats;
	}

	 public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

	 
	 public String getFstatus() {
			return Fstatus;
		}
	    
		public void setFstatus(String fstatus) {
			Fstatus = fstatus;
		}
	 
	public void setSeats(Set<Seats> seats) {
		this.seats = seats;
	}

	public double getTicketPriceForBusinessClass() {
		return TicketPriceForBusinessClass;
	}

	public void setTicketPriceForBusinessClass(double ticketPriceForBusinessClass) {
		TicketPriceForBusinessClass = ticketPriceForBusinessClass;
	}

	public double getTicketPriceForNonBusinessClass() {
		return TicketPriceForNonBusinessClass;
	}

	public void setTicketPriceForNonBusinessClass(double ticketPriceForNonBusinessClass) {
		TicketPriceForNonBusinessClass = ticketPriceForNonBusinessClass;
	}

		
	public Long getFlightId() {
		return FlightId;
	}
	public void setFlightId(Long flightId) {
		FlightId = flightId;
	}
	public String getFlightNo() {
		return FlightNo;
	}
	public void setFlightNo(String flightNo) {
		FlightNo = flightNo;
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
		return AirLine;
	}
	public void setAirLine(String airLine) {
		AirLine = airLine;
	}
	public int getTotalSeat() {
		return TotalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		TotalSeat = totalSeat;
	}
	public LocalDateTime getStartTime() {
		return StartTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		StartTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return EndTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		EndTime = endTime;
	}
	public boolean isAvailable() {
		return Available;
	}
	public void setAvailable(boolean available) {
		Available = available;
	}
	public int getTotalSeatInBusinessClass() {
		return TotalSeatInBusinessClass;
	}
	public void setTotalSeatInBusinessClass(int totalSeatInBusinessClass) {
		TotalSeatInBusinessClass = totalSeatInBusinessClass;
	}
	public int getTotalSeatInNonBusinessClass() {
		return TotalSeatInNonBusinessClass;
	}
	public void setTotalSeatInNonBusinessClass(int totalSeatInNonBusinessClass) {
		TotalSeatInNonBusinessClass = totalSeatInNonBusinessClass;
	}
	public String getTotalTime() {
		return TotalTime;
	}
	public void setTotalTime(String totalTime) {
		TotalTime = totalTime;
	}
	public int getAvailableSeatInBusinessClass() {
		return AvailableSeatInBusinessClass;
	}
	public void setAvailableSeatInBusinessClass(int availableSeatInBusinessClass) {
		AvailableSeatInBusinessClass = availableSeatInBusinessClass;
	}
	public int getAvailableSeatInNonBusinessClass() {
		return AvailableSeatInNonBusinessClass;
	}
	public void setAvailableSeatInNonBusinessClass(int availableSeatInNonBusinessClass) {
		AvailableSeatInNonBusinessClass = availableSeatInNonBusinessClass;
	}

	@Override
	public String toString() {
		return "Flight [FlightId=" + FlightId + ", FlightNo=" + FlightNo + ", Source=" + source + ", Destination="
				+ destination + ", AirLine=" + AirLine + ", TotalSeat=" + TotalSeat + ", date=" + date + ", StartTime="
				+ StartTime + ", EndTime=" + EndTime + ", Available=" + Available + ", TotalSeatInBusinessClass="
				+ TotalSeatInBusinessClass + ", TotalSeatInNonBusinessClass=" + TotalSeatInNonBusinessClass
				+ ", TotalTime=" + TotalTime + ", AvailableSeatInBusinessClass=" + AvailableSeatInBusinessClass
				+ ", AvailableSeatInNonBusinessClass=" + AvailableSeatInNonBusinessClass
				+ ", TicketPriceForBusinessClass=" + TicketPriceForBusinessClass + ", TicketPriceForNonBusinessClass="
				+ TicketPriceForNonBusinessClass + ", seats=" + seats + "]";
	}

	
	
	
	 
	 
}
