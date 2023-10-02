package com.parking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EmployeeDto {
	@NotEmpty(message = "First name should not be empty")
	@Size(max = 50, message = "First name exceed 50 characters")
	private String firstName;
	@NotEmpty(message = "First name should not be empty")
	@Size(max = 50, message = "Last name exceed 50 characters")
	private String lastName;
	@Size(min = 1, message = "Gender shouldn't be empty")
	private Boolean gender;
	@Email
	private String email;
	private String phone;
	private String avatar;
	private Long parkingLotId;
	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDto(
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "First name exceed 50 characters") String firstName,
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "Last name exceed 50 characters") String lastName,
			@NotEmpty(message = "Gender must not be empty") Boolean gender, @Email String email, String phone,
			String avatar, Long parkingLotId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.parkingLotId = parkingLotId;
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
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	
	
}
