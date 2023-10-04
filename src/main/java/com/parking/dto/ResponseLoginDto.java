package com.parking.dto;

import org.springframework.http.HttpStatus;

public class ResponseLoginDto {
	private HttpStatus status;
	private String message;
	private Object object;
	private Long role;
	
	public ResponseLoginDto() {
		super();
	}

	public ResponseLoginDto(HttpStatus status, String message, Object object, Long role) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
		this.role = role;
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}
	
	
}
