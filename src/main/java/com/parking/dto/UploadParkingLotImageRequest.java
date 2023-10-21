package com.parking.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadParkingLotImageRequest {
	private Long parkingLotId;
	private MultipartFile file;
	public UploadParkingLotImageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadParkingLotImageRequest(Long parkingLotId, MultipartFile file) {
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
