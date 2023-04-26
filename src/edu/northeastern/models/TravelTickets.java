package edu.northeastern.models;

import java.util.Date;

public class TravelTickets extends Travel{
	private int ticketCount;
	private String carrier;
	private String ticketID;
	
	public TravelTickets(double cost, Location fromLocation, Location toLocation, Date departureTime,
			Date arrivalTime) {
		super(cost, fromLocation, toLocation, departureTime, arrivalTime);
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public String getTicketID() {
		return ticketID;
	}
	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
}
