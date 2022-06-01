package com.cts.airticketreservation.model;

public class Admin {
	

	String username;
	String password;
	String email_id;
	
	public Admin(String username, String password, String email_id) {
		super();
		this.username = username;
		this.password = password;
		this.email_id = email_id;
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}


}
