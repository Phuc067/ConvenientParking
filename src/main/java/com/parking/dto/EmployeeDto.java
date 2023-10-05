package com.parking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EmployeeDto {
	private Long id;
	@NotEmpty(message = "First name should not be empty")
	@Size(max = 50, message = "First name exceed 50 characters")
	private String firstName;
	@NotEmpty(message = "First name should not be empty")
	@Size(max = 50, message = "Last name exceed 50 characters")
	private String lastName;
	@Size(min = 1, message = "Gender shouldn't be empty")
	private Boolean gender;
	@Email(message = "Email is invalid")
	private String email;
	private String phone;
	private String avatar;
	@NotEmpty(message = "username should not be empty")
	private String username;
	@NotEmpty(message = "password should not be empty")
	private String password;
	
	private Long parkingLotId;
	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeDto(Long id,
			@NotEmpty(message = "First name should not be empty") @Size(max = 50, message = "First name exceed 50 characters") String firstName,
			@NotEmpty(message = "First name should not be empty") @Size(max = 50, message = "Last name exceed 50 characters") String lastName,
			@Size(min = 1, message = "Gender shouldn't be empty") Boolean gender,
			@Email(message = "Email is invalid") String email, String phone, String avatar,
			@NotEmpty(message = "username should not be empty") String username,
			@NotEmpty(message = "password should not be empty") String password, Long parkingLotId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.username = username;
		this.password = password;
		this.parkingLotId = parkingLotId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
}
