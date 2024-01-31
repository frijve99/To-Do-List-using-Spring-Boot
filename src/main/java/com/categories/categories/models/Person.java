package com.categories.categories.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity //Creating a Table Name Person
public class Person {
	@Id
	private String userName; // It is a Primary Key
	private String email;
	private String password;
	
	public Person() {

	}
	
	public Person(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Person [userName=" + userName + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
