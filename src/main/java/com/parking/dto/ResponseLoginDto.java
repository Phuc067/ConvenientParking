package com.parking.dto;

import org.springframework.http.HttpStatus;

public class ResponseLoginDto {
	private HttpStatus status;
	private String message;
	private Long role;
	private String accessToken;
	
	public ResponseLoginDto() {
		super();
	}


	public ResponseLoginDto(HttpStatus status, String message, Long role, String accessToken) {
		super();
		this.status = status;
		this.message = message;
		this.role = role;
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


	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
}
