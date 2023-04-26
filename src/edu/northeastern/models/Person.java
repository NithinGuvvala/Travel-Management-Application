package edu.northeastern.models;

public class Person {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int age;
	
	Person(String lastName, String firstName, String username, String password,int age){
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=username;
		this.password=password;
		this.age=age;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
