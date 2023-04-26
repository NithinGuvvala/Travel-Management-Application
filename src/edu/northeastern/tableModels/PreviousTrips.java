package edu.northeastern.tableModels;

public class PreviousTrips {
	private String travelId;
	private String from;
	private String to;
	private String departureTime;
	private String arrivalTime;
	private String status;
	private double cost;
	public PreviousTrips(String travelId, String from, String to, String departureTime, String arrivalTime,
			String status, double cost) {
		super();
		this.travelId = travelId;
		this.from = from;
		this.to = to;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.status = status;
		this.cost = cost;
	}
	public String getTravelId() {
		return travelId;
	}
	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
