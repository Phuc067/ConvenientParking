package com.parking.dto;


public class VerificationDto {
	
	private String username;
	private String verificationCode;
	
	public VerificationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VerificationDto(String username, String verificationCode) {
		super();
		this.username = username;
		this.verificationCode = verificationCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
	
}
