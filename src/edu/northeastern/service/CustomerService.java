package edu.northeastern.service;

import java.sql.SQLException;

import edu.northeastern.dao.CustomerDAO;
import edu.northeastern.models.Customer;

public class CustomerService {
	public String createCustomer(String lastName, String firstName, String username, String password, int age, double balance) throws SQLException{
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.insertNewCustomer(lastName, firstName, username, password, balance, age);
		return "Success";
	}
	
	public Customer login(String username, String password) throws Exception {
		try {
			CustomerDAO customerDAO = new CustomerDAO();
			Customer customer=customerDAO.getCustomer(username, password);
			return customer;
		}catch(Exception e) {
			throw new Exception("Something went wrong");
		}
	}
	
}
