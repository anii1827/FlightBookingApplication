package com.flightApplication.Admin.DTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class FlightDTO {

	 private static final long serialVersionUID = 3810242876515034475L;
	 private Long inventory_Id;
	 private Long FlightId;
	 private String FlightNo;
	 private String Source;
	 private String Destination;
	 private String AirLine;
	 private int AvailableSeatInBusinessClass;
	 private int AvailableSeatInNonBusinessClass;
	 private int TotalSeatInBusinessClass;
	 private int TotalSeatInNonBusinessClass;
	 private LocalDateTime RegisterDate;
	 public LocalDateTime getRegisterDate() {
		return RegisterDate;
	 }
	 public void setRegisterDate(LocalDateTime registerDate) {
		RegisterDate = registerDate;
	 }
	 private String StartTime;
	 private String EndTime;
	 private boolean Available;
	 private boolean isSechduled;
	 private String TotalTime;
	 private String startTimeinHoursandMinute;
	 private String endTimeinHoursandMinute;
	 private double TicketPriceForBusinessClass;
	 private double TicketPriceForNonBusinessClass;
	 private HashMap<String, List<Integer>> AvailableSeatNumber;
	
	 
	public boolean isSechduled() {
		return isSechduled;
	}
	
	public void setSechduled(boolean isSechduled) {
		this.isSechduled = isSechduled;
	}
	 
	public Long getInventory_Id() {
		return inventory_Id;
	}
	
	
	public void setInventory_Id(Long inventory_Id) {
		this.inventory_Id = inventory_Id;
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
		TotalSeatInNonBusinessClass = totalSeatInNonBusinessClass;
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
	@JsonIgnore
	public void setAvailable(boolean available) {
		Available = available;
	}
	public String getTotalTime() {
		return TotalTime;
	}
	@JsonIgnore
	public void setTotalTime(String totalTime) {
		TotalTime = totalTime;
	}
	public String getStartTimeinHoursandMinute() {
		return startTimeinHoursandMinute;
	}
	@JsonIgnore
	public void setStartTimeinHoursandMinute(String startTimeinHoursandMinute) {
		this.startTimeinHoursandMinute = startTimeinHoursandMinute;
	}
	public String getEndTimeinHoursandMinute() {
		return endTimeinHoursandMinute;
	}
	@JsonIgnore
	public void setEndTimeinHoursandMinute(String endTimeinHoursandMinute) {
		this.endTimeinHoursandMinute = endTimeinHoursandMinute;
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
	public HashMap<String, List<Integer>> getAvailableSeatNumber() {
		return AvailableSeatNumber;
	}
	@JsonIgnore
	public void setAvailableSeatNumber(HashMap<String, List<Integer>> availableSeatNumber) {
		AvailableSeatNumber = availableSeatNumber;
	}
	@Override
	public String toString() {
		return "FlightDTO [inventory_Id=" + inventory_Id + "\n, FlightId=" + FlightId + "\n, FlightNo=" + FlightNo
				+ "\n, Source=" + Source + "\n, Destination=" + Destination + "\n, AirLine=" + AirLine
				+ "\n, AvailableSeatInBusinessClass=" + AvailableSeatInBusinessClass
				+ "\n, AvailableSeatInNonBusinessClass=" + AvailableSeatInNonBusinessClass + "\n, TotalSeatInBusinessClass="
				+ TotalSeatInBusinessClass + "\n, TotalSeatInNonBusinessClass=" + TotalSeatInNonBusinessClass
				+ "\n, StartTime=" + StartTime + "\n, EndTime=" + EndTime + "\n, Available=" + Available + "\n, TotalTime="
				+ TotalTime + "\n, startTimeinHoursandMinute=" + startTimeinHoursandMinute + "\n, endTimeinHoursandMinute="
				+ endTimeinHoursandMinute + "\n, TicketPriceForBusinessClass=" + TicketPriceForBusinessClass
				+ "\n, TicketPriceForNonBusinessClass=" + TicketPriceForNonBusinessClass + "\n, AvailableSeatNumber="
				+ AvailableSeatNumber + "]";
	}

	 
	
	
	 
	 
	
}
