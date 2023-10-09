package com.parking.dto;

import javax.validation.constraints.Email;

public class EmailRequest {
	
	@Email
	private String email;

	public EmailRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailRequest(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
