package com.parking.model;

public class VehicleData {
	private int vehicleTypeId;
	private String licensePlate;
	public VehicleData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VehicleData(int vehicleTypeId, String licensePlate) {
		super();
		this.vehicleTypeId = vehicleTypeId;
		this.licensePlate = licensePlate;
	}
	public int getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	
}
