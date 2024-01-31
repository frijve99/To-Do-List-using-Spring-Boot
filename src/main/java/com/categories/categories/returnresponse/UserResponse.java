package com.categories.categories.returnresponse;

public class UserResponse {
	private String token;
	private String userName;
	private String email;
	public UserResponse(String userName, String email,String token) {
		super();
		this.token = token;
		this.userName = userName;
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	
	
}
