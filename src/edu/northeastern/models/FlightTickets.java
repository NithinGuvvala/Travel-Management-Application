package edu.northeastern.models;

import java.util.Date;

public class FlightTickets extends TravelTickets{
	
	private String flightNo;
	private String carrier;
	
	public FlightTickets(double cost, Location fromLocation, Location toLocation, Date departureTime,
			Date arrivalTime) {
		super(cost, fromLocation, toLocation, departureTime, arrivalTime);
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	
	
}
