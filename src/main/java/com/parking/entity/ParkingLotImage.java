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

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "parkingLotImages")

public class ParkingLotImage implements Serializable{
	
	private static final long serialVersionUID = 2802160008300746992L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "parkingLotId", referencedColumnName = "id")
	@JsonIgnore
	private ParkingLot parkingLot;
	
	@Column
	private byte data[];

	public ParkingLotImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingLotImage(Long id, ParkingLot parkingLot, byte[] data) {
		super();
		this.id = id;
		this.parkingLot = parkingLot;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
