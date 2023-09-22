package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicleTypes")

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

	public VehicleType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VehicleType(Long id, String typeName) {
		super();
		this.id = id;
		this.typeName = typeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
