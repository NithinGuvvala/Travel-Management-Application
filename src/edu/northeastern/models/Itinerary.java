package edu.northeastern.models;

public class Itinerary {
	private String itineraryID;
	private Location origin;
	private Location destination;
	public Itinerary(String itineraryID,Location origin, Location destination) {
		this.itineraryID=itineraryID;
		this.origin=origin;
		this.destination=destination;
	}
	public String getItineraryID() {
		return itineraryID;
	}
	public void setItineraryID(String itineraryID) {
		this.itineraryID = itineraryID;
	}
	public Location getOrigin() {
		return origin;
	}
	public void setOrigin(Location origin) {
		this.origin = origin;
	}
	public Location getDestination() {
		return destination;
	}
	public void setDestination(Location destination) {
		this.destination = destination;
	}

}
