package edu.northeastern.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.northeastern.dbObject.DBConnectionUtil;
import edu.northeastern.models.Customer;

public class CustomerDAO {
	
	public void insertNewCustomer(String lastName, String firstName, String username, String password, double balance, int age) throws SQLException {
		String insertQuery = "Insert into Customer values('"+lastName+"','"+firstName+"','"+username+"','"+password+"','"+age+"','"+balance+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	public Customer getCustomer(String username, String password) throws Exception {
		String fetchQuery = "Select * from customer where username='"+username+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet resultSet = dbConnectionUtil.selectOperations(fetchQuery);
		resultSet.next();
		if(!password.equals(resultSet.getString("Password"))) {
			throw new Exception("Password Mismatch!");
		}
		Customer customer = new Customer(resultSet.getString("LastName"),resultSet.getString("FirstName"),resultSet.getString("UserName"),resultSet.getString("Password"),resultSet.getDouble("Balance"),resultSet.getInt("age"));
		return customer;
	}
	
	public void updateCustomer(Customer customer) throws SQLException {
		String updateQuery = "Update Customer set Balance="+customer.getBankBalance()+"where UserName='"+customer.getUserName()+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(updateQuery);
	}
	
	public ArrayList<String> getTravelHistory(Customer customer) throws SQLException{
		String travelHistoryQuery = "Select * from CustomerItineraryRel where Customer='"+customer.getUserName()+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet resultSet = dbConnectionUtil.selectOperations(travelHistoryQuery);
		ArrayList<String> travelHistory = new ArrayList<>();
		while(resultSet.next()) {
			travelHistory.add(resultSet.getString("ItineraryID"));
		}
		return travelHistory;
	}
}


