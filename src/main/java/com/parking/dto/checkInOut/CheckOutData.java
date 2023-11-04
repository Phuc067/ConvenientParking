package com.parking.dto.checkInOut;

public class CheckOutData {
	private String licensePlate;
	private Long price;
	public CheckOutData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckOutData(String licensePlate, Long price) {
		super();
		this.licensePlate = licensePlate;
		this.price = price;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
}
