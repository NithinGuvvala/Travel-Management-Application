package edu.northeastern.models;

import java.util.Date;

public class Flight extends TravelBooked {
	
	  public Flight(String reservationId, Date reservationTime, double cost, Location fromLocation, Location toLocation,
			Date departureTime, Date arrivalTime) {
		super(reservationId, reservationTime, cost, fromLocation, toLocation, departureTime, arrivalTime);
	}
	private String flightNo;
	private String airline;
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	  
}
