package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endUsers")

public class User implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 8142795887784296891L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;
	
	@Column
	@NotBlank(message = "First name should not be empty")
	@Size(max = 50, message = "First name shouldn't be exceed 50 character")
	private String firstName;
	
	@Column
	@NotBlank(message = "Last name should not be empty")
	@Size(max = 50, message = "Last name shouldn't be exceed 50 character")
	private String lastName;
	
	@Column
	@NotBlank(message = "Gender should not be empty")
	private Boolean gender;
	
	@Column
	private String phone;
	@Column
	@Email
	private String email;
	@Column
	private String zalopayId;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id,
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "First name shouldn't be exceed 50 character") String firstName,
			@NotBlank(message = "Last name should not be empty") @Size(max = 50, message = "Last name shouldn't be exceed 50 character") String lastName,
			@NotBlank(message = "Gender should not be empty") Boolean gender, String phone, @Email String email,
			String zalopayId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.zalopayId = zalopayId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZalopayId() {
		return zalopayId;
	}
	public void setZalopayId(String zalopayId) {
		this.zalopayId = zalopayId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
