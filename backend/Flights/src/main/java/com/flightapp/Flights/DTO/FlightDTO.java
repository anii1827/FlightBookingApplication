package com.flightapp.Flights.DTO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.flightapp.Flights.Model.Seats;
import com.flightapp.Flights.Util.SeatType;

public class FlightDTO implements Serializable{
	 private static final long serialVersionUID = 3810242876515034475L;
	 private Long FlightId;
	 private String FlightNo;
	 private String Source;
	 private String Destination;
	 private String AirLine;
	 private int AvailableSeatInBusinessClass;
	 private int AvailableSeatInNonBusinessClass;
	 private String StartTime;
	 private String EndTime;
	 private boolean Available;
	 private String TotalTime; 
	 private String startTimeinHoursandMinute;
	 private String endTimeinHoursandMinute;
	 private double TicketPriceForBusinessClass;
	 private double TicketPriceForNonBusinessClass;
	 private HashMap<String, List<Integer>> AvailableSeatNumber;
		
	 public FlightDTO() {
		// TODO Auto-generated constructor stub
	}
	 
	public HashMap<String, List<Integer>> getAvailableSeatNumber() {
		return AvailableSeatNumber;
	}
	public void setAvailableSeatNumber(HashMap<String, List<Integer>> availableSeatNumber) {
		AvailableSeatNumber = availableSeatNumber;
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
	public String getStartTimeinHoursandMinute() {
		return startTimeinHoursandMinute;
	}
	public void setStartTimeinHoursandMinute(String startTimeinHoursandMinute) {
		this.startTimeinHoursandMinute = startTimeinHoursandMinute;
	}
	public String getEndTimeinHoursandMinute() {
		return endTimeinHoursandMinute;
	}
	public void setEndTimeinHoursandMinute(String endTimeinHoursandMinute) {
		this.endTimeinHoursandMinute = endTimeinHoursandMinute;
	}
	public void setId(Long id) {
		FlightId = id;
	}
	public String getFlightNo() {
		return FlightNo;
	}
	public void setFlightNo(String flightNo) {
		FlightNo = flightNo;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public String getAirLine() {
		return AirLine;
	}
	public void setAirLine(String airLine) {
		AirLine = airLine;
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
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public boolean isAvailable() {
		return Available;
	}
	public void setAvailable(boolean available) {
		Available = available;
	}
	public String getTotalTime() {
		return TotalTime;
	}
	public void setTotalTime(String totalTime) {
		TotalTime = totalTime;
	}
	@Override
	public String toString() {
		return "FlightDTO [FlightId=" + FlightId + ", FlightNo=" + FlightNo + ", Source=" + Source + ", Destination="
				+ Destination + ", AirLine=" + AirLine + ", AvailableSeatInBusinessClass="
				+ AvailableSeatInBusinessClass + ", AvailableSeatInNonBusinessClass=" + AvailableSeatInNonBusinessClass
				+ ", StartTime=" + StartTime + ", EndTime=" + EndTime + ", Available=" + Available + ", TotalTime="
				+ TotalTime + ", startTimeinHoursandMinute=" + startTimeinHoursandMinute + ", endTimeinHoursandMinute="
				+ endTimeinHoursandMinute + ", TicketPriceForBusinessClass=" + TicketPriceForBusinessClass
				+ ", TicketPriceForNonBusinessClass=" + TicketPriceForNonBusinessClass + ", AvailableSeatNumber="
				+ AvailableSeatNumber + "]";
	}
	
	
	
	
	
		
}
