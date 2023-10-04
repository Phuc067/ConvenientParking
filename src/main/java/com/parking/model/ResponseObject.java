package com.parking.model;

import org.springframework.http.HttpStatus;

public class ResponseObject {
	private HttpStatus status;
	private String message;
	private Object object;
	
	public ResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseObject( HttpStatus status, String message, Object object) {
		super();
		this.message = message;
		this.object = object;
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
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
