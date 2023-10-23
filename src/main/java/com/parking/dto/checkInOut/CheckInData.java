package com.parking.dto;


public class CheckInData {
	private Long userId;
	private Long parkingLotId;
	public CheckInData() {
		super();
	}
	public CheckInData(Long userId, Long parkingLotId) {
		super();
		this.userId = userId;
		this.parkingLotId = parkingLotId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	
}