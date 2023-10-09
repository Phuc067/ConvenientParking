package com.parking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class LoginRequest {
	@NotBlank(message = "Username shouldn't not be empty")
	@Size(max = 50, message = "Username shouldn't be exceed 50 character")
	private String username;
	@NotBlank(message = "Password shouldn't not be empty")
	@Size(max = 50, message = "Password shouldn't be exceed 50 character")
	private String password;

	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public LoginRequest(
			@NotBlank(message = "Username shouldn't not be empty") @Size(max = 50, message = "Username shouldn't be exceed 50 character") String username,
			@NotBlank(message = "Password shouldn't not be empty") @Size(max = 50, message = "Password shouldn't be exceed 50 character") String password) {
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

}
