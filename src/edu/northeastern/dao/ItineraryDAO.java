package edu.northeastern.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.northeastern.dbObject.DBConnectionUtil;
import edu.northeastern.models.Bus;
import edu.northeastern.models.Customer;
import edu.northeastern.models.Flight;
import edu.northeastern.models.Itinerary;
import edu.northeastern.models.Location;
import edu.northeastern.models.Train;
import edu.northeastern.models.TravelTickets;

public class ItineraryDAO {
	
	public void insertNewItinerary(String itineraryId, String origin, String destination,double totalCost) throws SQLException {
		String insertQuery = "Insert into Itinerary values('"+itineraryId+"','"+origin+"','"+destination+"',"+totalCost+")";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	public Itinerary getItinerary(String itinearyID) throws SQLException {
		String getItineraryQuery = "Select * from Itinerary where ItinearyID = '"+itinearyID+"'";
		DBConnectionUtil dbConnectionUtil = new DBConnectionUtil();
		ResultSet results = dbConnectionUtil.selectOperations(getItineraryQuery);
		results.next();
		String origin = results.getString("Origin");
		String destination = results.getString("Destination");
		LocationDAO locationDAO = new LocationDAO();
		Location originLocation = locationDAO.getLocation(origin);
		Location destLocation = locationDAO.getLocation(destination);
		Itinerary itinerary = new Itinerary(itinearyID, originLocation, destLocation);
		return itinerary;
	}
	
	public ArrayList<String> getRoutes(String itinearyID)throws SQLException{
		String routesQuery = "Select * from ItinerartTravelRel where ItineraryID ='"+ itinearyID+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();	
		ResultSet resultSet = dbConnectionUtil.selectOperations(routesQuery);
		ArrayList<String> routesList = new ArrayList<>();
		while(resultSet.next()) {
			routesList.add(resultSet.getString("TravelID"));
		}
		return routesList;
	}
	
	public void insertRoute(String itinearyId, String routeId, int rank) throws SQLException {
		String insertQuery = "insert into ItineraryTravelRel values('"+itinearyId+"','"+routeId+"',"+rank+")";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	public void updateTicket(TravelTickets ticket) throws SQLException {
		String updateQuery = "Update Tickets set TicketCount="+ticket.getTicketCount()+"where TicketID='"+ticket.getTicketID()+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(updateQuery);
	}
	
	public void deleteTicket(TravelTickets ticket) throws SQLException {
		String updateQuery = "Delete from Tickets where TicketID='"+ticket.getTicketID()+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(updateQuery);
	}
	
	public void insertNewCustomerItinearyRel(Customer customer, Itinerary itinerary) throws SQLException {
		String insertQuery = "Insert into Itinerary values('"+customer.getUserName()+"','"+itinerary.getItineraryID()+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	public void insertBookedFlight(Flight flight) throws SQLException {
		String insertQuery = "Insert into Travel values ('"+flight.getReservationId()+"','"+flight.getReservationTime()+"','"+flight.getFlightNo()+"','"+flight.getAirline()+"',null,null,'"+flight.getCost()+"','"+flight.getFromLocation().getLocationName()+"','"+flight.getToLocation().getLocationName()+"','"+flight.getDepartureTime()+"','"+flight.getArrivalTime()+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	public void insertBookedTrain(Train train) throws SQLException {
		String insertQuery = "Insert into Travel values ('"+train.getReservationId()+"','"+train.getReservationTime()+"',null,null,null,'"+train.getTrainNo()+"','"+train.getCost()+"','"+train.getFromLocation().getLocationName()+"','"+train.getToLocation().getLocationName()+"','"+train.getDepartureTime()+"','"+train.getArrivalTime()+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	
	public void insertBookedBus(Bus bus) throws SQLException {
		String insertQuery = "Insert into Travel values ('"+bus.getReservationId()+"','"+bus.getReservationTime()+"',null,null,'"+bus.getBusNo()+"',null,'"+bus.getCost()+"','"+bus.getFromLocation().getLocationName()+"','"+bus.getToLocation().getLocationName()+"','"+bus.getDepartureTime()+"','"+bus.getArrivalTime()+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}

}