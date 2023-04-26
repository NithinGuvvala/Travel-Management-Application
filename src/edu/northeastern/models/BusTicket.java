package edu.northeastern.models;

import java.util.Date;

public class BusTicket extends TravelTickets{
	public BusTicket(double cost, Location fromLocation, Location toLocation, Date departureTime, Date arrivalTime) {
		super(cost, fromLocation, toLocation, departureTime, arrivalTime);
	}

	private String busNo;

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	
}
