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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="merchants")

public class Merchant implements Serializable {

	private static final long serialVersionUID = 7120780037010928471L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Long id;
	@Column
	@Size(max = 50, message = "Name shouldn't be exceed 50 character")
	@NotBlank(message = "Name should not be empty")
	private String name;
	@Column
	@Size(max = 50, message = "Represent shouldn't be exceed 50 character")
	@NotBlank(message = "Represent should not be empty")
	private String represent;
	@Column
	@Email
	private String email;
	@Column
	private String phone;
	
	@OneToOne
	@JoinColumn(name = "loginId", referencedColumnName = "id")
	@JsonIgnore
	private Login login;
	
	public Merchant() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Merchant(Long id,
			@Size(max = 50, message = "Name shouldn't be exceed 50 character") @NotBlank(message = "Name should not be empty") String name,
			@Size(max = 50, message = "Represent shouldn't be exceed 50 character") @NotBlank(message = "Represent should not be empty") String represent,
			@Email String email, String phone, Login login) {
		super();
		this.id = id;
		this.name = name;
		this.represent = represent;
		this.email = email;
		this.phone = phone;
		this.login = login;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
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


	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
