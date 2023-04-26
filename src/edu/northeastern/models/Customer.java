package edu.northeastern.models;

public class Customer extends Person {
	
	private double bankBalance;
	
	public Customer(String lastName, String firstName, String username, String password, double balance, int age){
		super(lastName,firstName,username,password,age);
		this.bankBalance=balance;
	}

	
	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}
	

}
