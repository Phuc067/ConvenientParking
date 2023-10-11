package com.parking.dto;

public class RefreshTokenResponse {
	private String accessToken;
	private Long role;
	public RefreshTokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RefreshTokenResponse(String accessToken, Long role) {
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
