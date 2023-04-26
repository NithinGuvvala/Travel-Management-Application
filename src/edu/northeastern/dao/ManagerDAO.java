package edu.northeastern.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.northeastern.dbObject.DBConnectionUtil;
import edu.northeastern.models.Location;
import edu.northeastern.models.Manager;

public class ManagerDAO {
	
	public void insertNewManager(String lastName, String firstName, String username, String password, int age, String type) throws SQLException {
		String insertQuery = "Insert into Manager values('"+lastName+"','"+firstName+"','"+username+"','"+password+"',"+age+",'"+type+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	public Manager getManager(String username, String password) throws Exception {
		String fetchQuery = "Select * from manager where username='"+username+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet resultSet = dbConnectionUtil.selectOperations(fetchQuery);
		resultSet.next();
		if(!password.equals(resultSet.getString("password"))) {
			throw new Exception("Password Mismatch!");
		}
		Manager manager = new Manager(resultSet.getString("LastName"),resultSet.getString("FirstName"),resultSet.getString("UserName"),resultSet.getString("Password"),resultSet.getInt("age"), resultSet.getString("Type"));
		return manager;
	}
	
	public void addTicket(Location source, Location destination, String ticketId, int ticketCount, String flightNo, String airline, String busNo, String trainNo, double cost, Date departTime, Date arriveTime) throws SQLException {
		String insertQuery;
//		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss") ;
		Timestamp ts1=new Timestamp(departTime.getTime());  
		Timestamp ts2=new Timestamp(arriveTime.getTime()); 
//		String departureDate = departTime.toString();
		Date arrivalDate = new java.sql.Date(arriveTime.getTime());
		if(flightNo!=null) {
			insertQuery = "Insert into Tickets values ("+ticketCount+",'"+ticketId+"','"+flightNo+"','"+airline+"',null,null,"+cost+",'"+source.getLocationName()+"','"+destination.getLocationName()+"','"+ts1+"','"+ts2+"')";
		}
		else if(busNo!=null) {
			insertQuery = "Insert into Tickets values ("+ticketCount+",'"+ticketId+"',null,'"+airline+"','"+busNo+"',null,"+cost+",'"+source.getLocationName()+"','"+destination.getLocationName()+"','"+ts1+"','"+ts2+"')";
		}
		else {
			insertQuery = "Insert into Tickets values ("+ticketCount+",'"+ticketId+"',null,'"+airline+"',null,'"+trainNo+"','"+cost+"','"+source.getLocationName()+"','"+destination.getLocationName()+"','"+ts1+"','"+ts2+"')";
		}
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		System.out.println(insertQuery);
		dbConnectionUtil.queryOperations(insertQuery);
	}
}