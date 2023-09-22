package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="employees")

public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 854919399257520818L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Long id;
	@Column
	@Size(max = 50, message = "User name shouldn't be exceed 50 character")
	private String username;
	@Column
	@NotBlank(message = "First name should not be empty")
	@Size(max = 50, message = "First name exceed 50 characters")
	private String firstName;
	@Column
	@NotBlank(message = "First name should not be empty")
	@Size(max = 50, message = "Last name exceed 50 characters")
	private String lastName;
	@Column
	@NotEmpty(message = "Gender must not be empty")
	private Boolean gender;
	@Column
	private String password;
	@Column
	@Email
	private String email;
	@Column
	private String phone;
	@Column
	private String avatar;
	
	//permissionId
	@ManyToOne
	@JoinColumn(name = "permissionId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Permission permission;
	
	@Column
	private Boolean status;
	//parkingLotId
	
	@ManyToOne
	@JoinColumn(name = "parkingLotId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ParkingLot parkingLot;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, @Size(max = 50, message = "User name shouldn't be exceed 50 character") String username,
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "First name exceed 50 characters") String firstName,
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "Last name exceed 50 characters") String lastName,
			@NotEmpty(message = "Gender must not be empty") Boolean gender, String password, @Email String email,
			String phone, String avatar, Permission permission, Boolean status, ParkingLot parkingLot) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.permission = permission;
		this.status = status;
		this.parkingLot = parkingLot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
