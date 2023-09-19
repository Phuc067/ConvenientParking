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
@Table(name="merchants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Merchant implements Serializable {

	/**
	 * 
	 */
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
	
}
