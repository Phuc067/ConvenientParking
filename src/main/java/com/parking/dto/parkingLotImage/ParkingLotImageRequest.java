package com.parking.dto.parkingLotImage;

import org.springframework.web.multipart.MultipartFile;

public class ParkingLotImageRequest {
	private Long parkinglotId;
	private MultipartFile file;
	
	public ParkingLotImageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingLotImageRequest(Long parkinglotId, MultipartFile file) {
		super();
		this.parkinglotId = parkinglotId;
		this.file = file;
	}

	public Long getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Long parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
