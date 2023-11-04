package com.parking.dto.parkinglot;

public class ParkingLotSearch {
	private Long vehicleTypeId;
	private String keyword;
	public ParkingLotSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParkingLotSearch(Long vehicleTypeId, String keyword) {
		super();
		this.vehicleTypeId = vehicleTypeId;
		this.keyword = keyword;
	}
	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
