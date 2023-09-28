package com.parking.model;


public class ResponseObject {
	private String message;
	private Object object;
	public ResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseObject( String message, Object object) {
		super();
		this.message = message;
		this.object = object;
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
	
}
