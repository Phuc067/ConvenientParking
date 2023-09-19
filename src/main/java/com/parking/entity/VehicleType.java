package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicleTypes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555815006049594612L;
	@GeneratedValue
	@Id
	private Long id;
	
	@Column
	private String typeName;
}
