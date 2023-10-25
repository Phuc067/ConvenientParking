package com.parking.model;

public class MessageAndBoolean {
	private String message;
	private Boolean boolean1;
	public MessageAndBoolean(String message, Boolean boolean1) {
		super();
		this.message = message;
		this.boolean1 = boolean1;
	}
	public MessageAndBoolean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getBoolean1() {
		return boolean1;
	}
	public void setBoolean1(Boolean boolean1) {
		this.boolean1 = boolean1;
	}
	
}
