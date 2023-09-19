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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
}
