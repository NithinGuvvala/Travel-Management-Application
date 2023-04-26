package edu.northeastern.models;

public class Manager extends Person{
	public String type;
	public Manager(String lastName, String firstName, String username, String password,int age, String type) {
		super(lastName,firstName,username,password,age);
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
