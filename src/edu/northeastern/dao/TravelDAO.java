package edu.northeastern.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.northeastern.dbObject.DBConnectionUtil;
import edu.northeastern.models.Bus;
import edu.northeastern.models.BusTicket;
import edu.northeastern.models.Flight;
import edu.northeastern.models.FlightTickets;
import edu.northeastern.models.Location;
import edu.northeastern.models.Train;
import edu.northeastern.models.TrainTickets;
import edu.northeastern.models.TravelBooked;
import edu.northeastern.models.TravelTickets;

public class TravelDAO {
	
	public void insertNewTravel(String reservation, Date reservationTime, double cost, Location toLocation, Location fromLocation,Date departureTime, Date arrivalTime, double flightNo, String airline, double busNo, double trainNo) throws SQLException {
		String insertQuery = "Insert into Travel values('"+reservation+"','"+reservationTime+"','"+flightNo+"','"+airline+"','"+busNo+"','"+trainNo+"','"+cost+"','"+fromLocation+"','"+toLocation+"' ,'"+ departureTime+"' ,'"+arrivalTime+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	
	public TravelBooked getTravel(String travelID) throws SQLException {
		String getTravelQuery = "SELECT * from Travel where TravelID='"+travelID+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet result=dbConnectionUtil.selectOperations(getTravelQuery);
		result.next();
		String reservationId = result.getString("ReservationID");
		Date reservationDate = result.getDate("ReservationTime");
		String flightNo = result.getString("FlightNo");
		String airline = result.getString("Airline");
		String busNo = result.getString("BusNo");
		String trainNo = result.getString("TrainNo");
		double cost = result.getDouble("TravelCost");
		String from = result.getString("FromLocation");
		String to = result.getString("ToLocation");
		Date departureTime = result.getDate("DepartureTime");
		Date arrivalTime = result.getDate("ArrivalTime");
		LocationDAO locationDAO = new LocationDAO();
		Location fromLocation = locationDAO.getLocation(from);
		Location toLocation = locationDAO.getLocation(to);
		TravelBooked travel = new TravelBooked(reservationId, reservationDate,cost,fromLocation,toLocation,departureTime,arrivalTime);
		if(flightNo!=null) {
			Flight flight = (Flight)travel;
			flight.setAirline(airline);
			flight.setFlightNo(flightNo);
			return flight;
		}
		else if(busNo!=null) {
			Bus bus = (Bus)travel;
			bus.setBusNo(busNo);
			return bus;
		}
		else if(trainNo!=null) {
			Train train = (Train)travel;
			train.setTrainNo(trainNo);
			return train;
		}
		return travel;
	}
	
	public ArrayList<TravelTickets> findTicket(Location source, Location destination) throws SQLException {
		String findTicketQuery = "Select * from Tickets where FromLocation='"+source.getLocationName()+"' and ToLocation='"+destination.getLocationName()+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet result=dbConnectionUtil.selectOperations(findTicketQuery);
		ArrayList<TravelTickets> ticketsList = new ArrayList<>();
		while(result.next()) {
			String flightNo = result.getString("FlightNo");
			String airline = result.getString("Airline");
			String busNo = result.getString("BusNo");
			String trainNo = result.getString("TrainNo");
			double cost = result.getDouble("TravelCost");
			String from = result.getString("FromLocation");
			String to = result.getString("ToLocation");
			Date departureTime = result.getDate("DepartureTime");
			Date arrivalTime = result.getDate("ArrivalTime");
			LocationDAO locationDAO = new LocationDAO();
			Location fromLocation = locationDAO.getLocation(from);
			Location toLocation = locationDAO.getLocation(to);
			TravelTickets travel = new TravelTickets(cost, fromLocation, toLocation, departureTime, arrivalTime);
			
			if(flightNo!=null) {
				FlightTickets flight = (FlightTickets)travel;
				flight.setCarrier(airline);
				flight.setFlightNo(flightNo);
				ticketsList.add(flight);
			}
			else if(busNo!=null) {
				BusTicket bus = (BusTicket)travel;
				bus.setBusNo(busNo);
				ticketsList.add(bus);
			}
			else if(trainNo!=null) {
				TrainTickets train = (TrainTickets)travel;
				train.setTrainNo(trainNo);
				ticketsList.add(train);
			}
			
		}
		return ticketsList;
	}
	
	public ArrayList<TravelTickets> findAllTicket() throws SQLException {
		String findTicketQuery = "Select * from Tickets";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet result=dbConnectionUtil.selectOperations(findTicketQuery);
		ArrayList<TravelTickets> ticketsList = new ArrayList<>();
		while(result.next()) {
			String flightNo = result.getString("FlightNo");
			String airline = result.getString("Airline");
			String busNo = result.getString("BusNo");
			String trainNo = result.getString("TrainNo");
			double cost = result.getDouble("TravelCost");
			String from = result.getString("FromLocation");
			String to = result.getString("ToLocation");
			Date departureTime = result.getDate("DepartureTime");
			Date arrivalTime = result.getDate("ArrivalTime");
			LocationDAO locationDAO = new LocationDAO();
			Location fromLocation = locationDAO.getLocation(from);
			Location toLocation = locationDAO.getLocation(to);
			TravelTickets travel = new TravelTickets(cost, fromLocation, toLocation, departureTime, arrivalTime);
			
			if(flightNo!=null) {
				FlightTickets flight = new FlightTickets(cost, fromLocation, toLocation, departureTime, arrivalTime);
				flight.setCarrier(airline);
				flight.setFlightNo(flightNo);
				ticketsList.add(flight);
			}
			else if(busNo!=null) {
				BusTicket bus = new BusTicket(cost,fromLocation,toLocation,departureTime, arrivalTime);
				bus.setBusNo(busNo);
				bus.setCarrier(airline);
				ticketsList.add(bus);
			}
			else if(trainNo!=null) {
				TrainTickets train = new TrainTickets(cost,fromLocation,toLocation,departureTime, arrivalTime);
				train.setTrainNo(trainNo);
				train.setCarrier(airline);
				ticketsList.add(train);
			}
			
		}
		return ticketsList;
	}
	
}
