package com.parking.dto;

import javax.validation.constraints.Email;

public class EmailDto {
	
	@Email
	private String email;

	public EmailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailDto(String email) {
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
