package com.parking.dto.refreshToken;

public class RefreshTokenRequest {
	private String refreshToken;

	public RefreshTokenRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RefreshTokenRequest(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
}
