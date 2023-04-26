package edu.northeastern.models;

import java.util.Date;

public class Travel {
	private double cost;
	private Location toLocation;
	private Location fromLocation;
	private Date departureTime;
	private Date arrivalTime;
	
	public Travel(double cost, Location fromLocation, Location toLocation, Date departureTime, Date arrivalTime) {
		this.cost=cost;
		this.fromLocation=fromLocation;
		this.toLocation=toLocation;
		this.departureTime=departureTime;
		this.arrivalTime=arrivalTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Location getToLocation() {
		return toLocation;
	}

	public void setToLocation(Location toLocation) {
		this.toLocation = toLocation;
	}

	public Location getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(Location fromLocation) {
		this.fromLocation = fromLocation;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
