package edu.northeastern.models;

import java.util.Date;

public class TravelBooked extends Travel{
	
	private String reservationId;
	private Date reservationTime;
	
	public TravelBooked(String reservationId, Date reservationTime, double cost, Location fromLocation, Location toLocation, Date departureTime, Date arrivalTime) {
		super(cost, fromLocation, toLocation, departureTime, arrivalTime);
		this.reservationId=reservationId;
		this.reservationTime=reservationTime;
		
	}
	
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public Date getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}
}
