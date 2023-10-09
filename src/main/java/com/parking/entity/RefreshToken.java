package com.parking.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	private Instant expriryDate;
	
	@OneToOne
	@JoinColumn(name = "loginId", referencedColumnName = "id")
	@JsonIgnore
	private Login login;

	public RefreshToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RefreshToken(Long id, String token, Instant expriryDate, Login login) {
		super();
		this.id = id;
		this.token = token;
		this.expriryDate = expriryDate;
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpriryDate() {
		return expriryDate;
	}

	public void setExpriryDate(Instant expriryDate) {
		this.expriryDate = expriryDate;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
}
