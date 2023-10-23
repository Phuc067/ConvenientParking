package com.parking.dto;

public class ResetPasswordRequest {
	private String username;
	private String password;
	private String verificationCode;
	public ResetPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResetPasswordRequest(String username, String password, String verificationCode) {
		super();
		this.username = username;
		this.password = password;
		this.verificationCode = verificationCode;
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
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
}
