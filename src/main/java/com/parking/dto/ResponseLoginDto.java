package com.parking.dto;

public class ResponseLoginDto {
	private String message;
	private Object object;
	private String role;
	
	public ResponseLoginDto() {
		super();
	}


	public ResponseLoginDto(String message, Object object, String role) {
		super();
		this.message = message;
		this.object = object;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
