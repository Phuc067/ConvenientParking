package com.parking.model;


public class AuthenticationResponse {
	private String accessToken;
	private Long role;
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthenticationResponse(String accessToken, Long role) {
		super();
		this.accessToken = accessToken;
		this.role = role;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}

}
