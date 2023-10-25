package com.parking.dto.checkInOut;

import com.parking.model.VehicleData;

public class CheckInInformation {
	private CheckInData checkInData;
	private VehicleData vehicleData;
	public CheckInInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckInInformation(CheckInData checkInData, VehicleData vehicleData) {
		super();
		this.checkInData = checkInData;
		this.vehicleData = vehicleData;
	}
	public CheckInData getCheckInData() {
		return checkInData;
	}
	public void setCheckInData(CheckInData checkInData) {
		this.checkInData = checkInData;
	}
	public VehicleData getVehicleData() {
		return vehicleData;
	}
	public void setVehicleData(VehicleData vehicleData) {
		this.vehicleData = vehicleData;
	}
	
	
}
