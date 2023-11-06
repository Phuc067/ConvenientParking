package com.parking.dto.ticket;

import java.sql.Timestamp;


public class HistoryTicket {
	private Long id;
	private Timestamp checkInTime;
	private Timestamp checkOutTime;
	private String licensePlate;
	private String parkingLotName;
	public HistoryTicket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HistoryTicket(Long id, Timestamp checkInTime, Timestamp checkOutTime, String licensePlate,
			String parkingLotName) {
		super();
		this.id = id;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.licensePlate = licensePlate;
		this.parkingLotName = parkingLotName;
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
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	
}
