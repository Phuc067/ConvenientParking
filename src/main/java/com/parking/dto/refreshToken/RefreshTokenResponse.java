package com.parking.dto.refreshToken;

public class RefreshTokenResponse {
	private String accessToken;
	public RefreshTokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RefreshTokenResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
