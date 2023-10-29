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
import javax.validation.constraints.Min;

@Entity
@Table(name = "priceTickets")

public class PriceTicket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1651467095926986797L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	// parkingLotId
	@ManyToOne
	@JoinColumn(name = "parkingLotId", referencedColumnName = "id")
	private ParkingLot parkingLot;

	// vehicleTypeId
	@ManyToOne
	@JoinColumn(name = "vehicleTypeId", referencedColumnName = "id")
	private VehicleType vehicleType;

	
	private Timestamp effectTime;
	
	private Timestamp expiredTime;
	
	@Column
	@Min(0)
	private Long price;

	public PriceTicket() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PriceTicket(Long id, ParkingLot parkingLot, VehicleType vehicleType, Timestamp effectTime,
			@Min(0) Long price) {
		super();
		this.id = id;
		this.parkingLot = parkingLot;
		this.vehicleType = vehicleType;
		this.effectTime = effectTime;
		this.price = price;
	}

	public PriceTicket(Long id, ParkingLot parkingLot, VehicleType vehicleType, Timestamp effectTime,
			Timestamp expiredTime, @Min(0) Long price) {
		super();
		this.id = id;
		this.parkingLot = parkingLot;
		this.vehicleType = vehicleType;
		this.effectTime = effectTime;
		this.expiredTime = expiredTime;
		this.price = price;
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

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public Timestamp getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Timestamp effectTime) {
		this.effectTime = effectTime;
	}

	public Timestamp getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
