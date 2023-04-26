package edu.northeastern.models;

import java.util.Date;

public class TrainTickets extends TravelTickets{
	
	private String trainNo;

	public TrainTickets(double cost, Location fromLocation, Location toLocation, Date departureTime, Date arrivalTime) {
		super(cost, fromLocation, toLocation, departureTime, arrivalTime);
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	
	
}
