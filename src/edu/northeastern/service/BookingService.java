package edu.northeastern.service;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import edu.northeastern.dao.CustomerDAO;
import edu.northeastern.dao.ItineraryDAO;
import edu.northeastern.dao.TravelDAO;
import edu.northeastern.models.Bus;
import edu.northeastern.models.BusTicket;
import edu.northeastern.models.Customer;
import edu.northeastern.models.Flight;
import edu.northeastern.models.FlightTickets;
import edu.northeastern.models.Itinerary;
import edu.northeastern.models.Location;
import edu.northeastern.models.Train;
import edu.northeastern.models.TrainTickets;
import edu.northeastern.models.TravelBooked;
import edu.northeastern.models.TravelTickets;

public class BookingService {
	
	private ArrayList<ArrayList<TravelTickets>> allRoutes;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	private String randomString(int len){
	   StringBuilder sb = new StringBuilder(len);
	   for(int i = 0; i < len; i++)
	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
	   return sb.toString();
	}
	
	public ArrayList<TravelTickets> getTickets(Date departDate) throws SQLException{
		TravelDAO travelDAO = new TravelDAO();
		ArrayList<TravelTickets> allTickets=travelDAO.findAllTicket();
		ArrayList<TravelTickets> ticketList = new ArrayList<>();
		for(TravelTickets ticket: allTickets) {
			if(ticket.getDepartureTime().compareTo(departDate)==0 || ticket.getDepartureTime().compareTo(departDate)>0) {
				ticketList.add(ticket);
			}
		}
		return ticketList;
	}
	
	private void findRoutesUtil(ArrayList<TravelTickets> route, HashSet<String> visited, ArrayList<TravelTickets> allTickets, TravelTickets currentTicket, Location destination){
		if(currentTicket.getToLocation().getLocationName().equals(destination.getLocationName())) {
			this.allRoutes.add(route);
			return;
		}
		for(TravelTickets ticket: allTickets) {
			
			if(ticket.getFromLocation().getLocationName().equals(currentTicket.getToLocation().getLocationName())){
				if(!visited.contains(ticket.getToLocation().getLocationName())) {
					visited.add(ticket.getToLocation().getLocationName());
					route.add(ticket);
					findRoutesUtil((ArrayList<TravelTickets>) route.clone(), (HashSet<String>) visited.clone(), allTickets, ticket, destination);
					route.remove(ticket);
					visited.remove(ticket.getToLocation().getLocationName());
				}
			}
		}
	}
	
	public ArrayList<ArrayList<TravelTickets>> findRoutes(Location source, Location destination, Date departDate) throws SQLException {
		this.allRoutes = new ArrayList<ArrayList<TravelTickets>>();
		ArrayList<TravelTickets> allTickets = getTickets(departDate);
		for(TravelTickets ticket: allTickets) {
			if(ticket.getFromLocation().getLocationName().equals(source.getLocationName())) {
				HashSet<String> visited = new HashSet<String>();
				visited.add(source.getLocationName());
				ArrayList<TravelTickets> route = new ArrayList<>();
				route.add(ticket);
				visited.add(ticket.getToLocation().getLocationName());
				findRoutesUtil(route, visited,allTickets, ticket, destination);
			}
		}
		return this.allRoutes;
	}
	
	public String bookTickets(ArrayList<TravelTickets> tickets, Customer customer, Itinerary itinerary) throws SQLException {
		double totalCost = 0;
		for(TravelTickets ticket: tickets)
			totalCost+=ticket.getCost();
		ItineraryDAO itineraryDAO = new ItineraryDAO();
		itineraryDAO.insertNewItinerary(itinerary.getItineraryID(),itinerary.getOrigin().getLocationName(), itinerary.getDestination().getLocationName(), totalCost);
		customer.setBankBalance(customer.getBankBalance()-totalCost);
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.updateCustomer(customer);
		int rank=1;
		
		for(TravelTickets ticket: tickets) {
			String reservationId=randomString(10);
			Date date = new Date();
			if(ticket instanceof TrainTickets) {
				Train train = new Train(reservationId, new Timestamp(System.currentTimeMillis()), ticket.getCost(), ticket.getFromLocation(), ticket.getToLocation(), ticket.getDepartureTime(), ticket.getArrivalTime());
				train.setTrainNo(ticket.getTicketID());
			}
			else if(ticket instanceof BusTicket) {
				Bus bus = new Bus(reservationId, new Timestamp(System.currentTimeMillis()), ticket.getCost(), ticket.getFromLocation(), ticket.getToLocation(), ticket.getDepartureTime(), ticket.getArrivalTime());
				bus.setBusNo(ticket.getTicketID());
			}
			else {
				Flight flight = new Flight(reservationId, new Timestamp(System.currentTimeMillis()),ticket.getCost(),ticket.getFromLocation(), ticket.getToLocation(), ticket.getDepartureTime(), ticket.getArrivalTime());
				FlightTickets flightTicket = (FlightTickets) ticket; 
				flight.setAirline(flightTicket.getCarrier());
				flight.setFlightNo(ticket.getTicketID());
			}
			itineraryDAO.insertRoute(itinerary.getItineraryID(), ticket.getTicketID(), rank);
			rank+=1;
		}
		itineraryDAO.insertNewCustomerItinearyRel(customer, itinerary);
		return "success";
		
	}
}
