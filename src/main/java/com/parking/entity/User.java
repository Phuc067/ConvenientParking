package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Long id;
	
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
	private String momoId;
	
	@OneToOne
	@JoinColumn(name = "loginId", referencedColumnName = "id")
	@JsonIgnore
	private Login login;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Long id,
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "First name shouldn't be exceed 50 character") String firstName,
			@NotBlank(message = "Last name should not be empty") @Size(max = 50, message = "Last name shouldn't be exceed 50 character") String lastName,
			@NotBlank(message = "Gender should not be empty") Boolean gender, String phone, String momoId,
			Login login) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.momoId = momoId;
		this.login = login;
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
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
