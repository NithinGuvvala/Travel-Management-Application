package edu.northeastern.models;

import java.util.Date;

public class Train extends TravelBooked{

	public Train(String reservationId, Date reservationTime, double cost, Location fromLocation, Location toLocation,
			Date departureTime, Date arrivalTime) {
		super(reservationId, reservationTime, cost, fromLocation, toLocation, departureTime, arrivalTime);
	}

	private String trainNo;

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

}
