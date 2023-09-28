package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logins")
public class Login implements Serializable{

	private static final long serialVersionUID = 2943096805631582103L;
	
	@Id
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	private Role role;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(Long id, String username, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
