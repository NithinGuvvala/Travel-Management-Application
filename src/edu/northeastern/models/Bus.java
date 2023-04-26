package edu.northeastern.models;

import java.util.Date;

public class Bus extends TravelBooked{
	
	public Bus(String reservationId, Date reservationTime, double cost, Location fromLocation, Location toLocation,
			Date departureTime, Date arrivalTime) {
		super(reservationId, reservationTime, cost, fromLocation, toLocation, departureTime, arrivalTime);
	}

	private String busNo;

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	

}
