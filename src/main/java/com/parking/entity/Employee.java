package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.jsf.FacesContextUtils;

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
	@Column(nullable = false)
	@Size(max = 50, message = "First name exceed 50 characters")
	private String firstName;
	@Column(nullable = false)
	@Size(max = 50, message = "Last name exceed 50 characters")
	private String lastName;
	@Column
	private Boolean gender;
	@Column
	@Email
	private String email;
	@Column
	private String phone;
	@Column
	private String avatar;
	
	@OneToOne
	@JoinColumn(name = "loginId",referencedColumnName = "id")
	private Login login;
	
	
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

	

	public Employee(Long id,
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "First name exceed 50 characters") String firstName,
			@NotBlank(message = "First name should not be empty") @Size(max = 50, message = "Last name exceed 50 characters") String lastName,
			@NotEmpty(message = "Gender must not be empty") Boolean gender, @Email String email, String phone,
			String avatar, Login login, Boolean status, ParkingLot parkingLot) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.login = login;
		this.status = status;
		this.parkingLot = parkingLot;
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

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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
