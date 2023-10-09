package com.parking.model;

import com.parking.entity.RefreshToken;

public class AuthenticationResponse {
	private String accessToken;
	private String refreshToken;
	private Long role;
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthenticationResponse(String accessToken, String refreshToken, Long role) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	

}
