package com.parking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterRequest {
	@NotEmpty(message = "Username shouldn't be empty")
	
	private String username;
	@NotEmpty(message = "Password shouldn't be empty")
	private String password;
	@Email
	private String email;
	private String role;
	
	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RegisterRequest(@NotEmpty(message = "Username shouldn't be empty") String username,
			@NotEmpty(message = "Password shouldn't be empty") String password, @Email String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
	
}
