package com.parking.dto.auth;

import org.springframework.http.HttpStatus;

public class LoginResponse {
	private HttpStatus status;
	private String message;
	private String accessToken;
	
	public LoginResponse() {
		super();
	}

	public LoginResponse(HttpStatus status, String message,String accessToken) {
		super();
		this.status = status;
		this.message = message;
		this.accessToken = accessToken;
	}


	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
}
