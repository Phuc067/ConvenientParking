package com.parking.dto.ticket;

import java.sql.Timestamp;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TicketResponse {
	private Long id;
	private Timestamp checkInTime;
	private String licensePlate;
	private String vehicleTypeName;
	private String parkingLotName;
	private Long price;
	@JsonIgnore
	private Long vehicleTypeId;
	@JsonIgnore
	private Long parkingLotId;
	public TicketResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public TicketResponse(Long id, Timestamp checkInTime, String licensePlate, String vehicleTypeName,
			String parkingLotName, Long vehicleTypeId, Long parkingLotId) {
		super();
		this.id = id;
		this.checkInTime = checkInTime;
		this.licensePlate = licensePlate;
		this.vehicleTypeName = vehicleTypeName;
		this.parkingLotName = parkingLotName;
		this.vehicleTypeId = vehicleTypeId;
		this.parkingLotId = parkingLotId;
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
	public void setCheckInTime(Timestamp timestamp) {
		this.checkInTime = timestamp;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getVehicleTypeName() {
		return vehicleTypeName;
	}
	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String string) {
		this.parkingLotName = string;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Long getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
}
