package edu.northeastern.service;

import java.sql.SQLException;
import java.util.Date;

import edu.northeastern.dao.CustomerDAO;
import edu.northeastern.dao.LocationDAO;
import edu.northeastern.dao.ManagerDAO;
import edu.northeastern.models.Location;
import edu.northeastern.models.Manager;

public class ManagerService {
	
	public Manager login(String username, String password) throws Exception {
		try {
			ManagerDAO managerDAO = new ManagerDAO();
			Manager manager=managerDAO.getManager(username, password);
			return manager;
		}catch(Exception e) {
			throw new Exception("Something went wrong");
		}
	}
	
	public String createTicket(String source, String destination, int ticketCount, String flightNo, String airline, double cost, Date departTime, Date arriveTime, String ticketId ) throws SQLException {
		ManagerDAO managerDAO = new ManagerDAO();
		LocationDAO locationDAO = new LocationDAO();
		Location sourceLocation = locationDAO.getLocation(source);
		Location destLocation = locationDAO.getLocation(destination);
		managerDAO.addTicket(sourceLocation, destLocation, ticketId, ticketCount, flightNo, airline, null, null, cost, departTime, arriveTime);
		return "success";
	}
	
	public String createManager(String lastName, String firstName, String username, String password, int age, String type) throws SQLException{
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.insertNewManager(lastName, firstName, username, password, age, type);
		return "Success";
	}
}
