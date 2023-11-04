package com.parking.dto.parkingLotImage;


public class ParkingLotImageRequest {
	private Long parkingLotId;
	private String  file;
	
	public ParkingLotImageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingLotImageRequest(Long parkingLotId, String file) {
		super();
		this.parkingLotId = parkingLotId;
		this.file = file;
	}

	public Long getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	
}
