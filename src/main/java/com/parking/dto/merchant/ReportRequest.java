package com.parking.dto.merchant;

public class ReportRequest {
	private Long parkingLotId;
	private Long year;
	public ReportRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportRequest(Long parkingLotId, Long year) {
		super();
		this.parkingLotId = parkingLotId;
		this.year = year;
	}
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	
}
