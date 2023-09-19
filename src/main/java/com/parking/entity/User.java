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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
}
