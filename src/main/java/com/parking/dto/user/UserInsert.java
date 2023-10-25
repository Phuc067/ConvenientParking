package com.parking.dto.user;

public class UserInsert {
	private String firstName;
	private String lastName;
	private Boolean gender;
	private String phone;
	private String momoId;
	private Long loginId;
	public UserInsert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInsert(String firstName, String lastName, Boolean gender, String phone, String momoId, Long loginId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.momoId = momoId;
		this.loginId = loginId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMomoId() {
		return momoId;
	}
	public void setMomoId(String momoId) {
		this.momoId = momoId;
	}
	public Long getLoginId() {
		return loginId;
	}
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	
}
