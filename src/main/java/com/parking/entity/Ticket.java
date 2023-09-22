package com.parking.entity;



import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")

public class Ticket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3200261929082487402L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column
	@CreationTimestamp
	private Timestamp checkInTime;
	@CreationTimestamp
	@Column
	private Timestamp checkOutTime;
	@Column
	private String liciensePlate;
	
	//vehicleTypeId;
	@ManyToOne
	@JoinColumn(name = "vehicleTypeId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private VehicleType vehicleType;
	//UserId
	@ManyToOne
	@JoinColumn(name = "endUserId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private User user;
	//parkingLotId
	@ManyToOne
	@JoinColumn(name = "parkingLotId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ParkingLot parkingLot;
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ticket(Long id, Timestamp checkInTime, Timestamp checkOutTime, String liciensePlate, VehicleType vehicleType,
			User user, ParkingLot parkingLot) {
		super();
		this.id = id;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.liciensePlate = liciensePlate;
		this.vehicleType = vehicleType;
		this.user = user;
		this.parkingLot = parkingLot;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Timestamp checkInTime) {
		this.checkInTime = checkInTime;
	}
	public Timestamp getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Timestamp checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public String getLiciensePlate() {
		return liciensePlate;
	}
	public void setLiciensePlate(String liciensePlate) {
		this.liciensePlate = liciensePlate;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
