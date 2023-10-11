package com.parking.model;

import com.parking.entity.RefreshToken;

public class AuthenticationResponse {
	private String accessToken;
	private String refreshToken;
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthenticationResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	

}
