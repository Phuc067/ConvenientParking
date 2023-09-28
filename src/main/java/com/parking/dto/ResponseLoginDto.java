package com.parking.dto;

public class ResponseLoginDto {
	private Object object;
	private String role;
	
	public ResponseLoginDto() {
		super();
	}

	public ResponseLoginDto(Object object, String role) {
		super();
		this.object = object;
		this.role = role;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
