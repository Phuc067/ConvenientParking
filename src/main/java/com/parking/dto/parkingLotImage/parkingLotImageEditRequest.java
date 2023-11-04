package com.parking.dto.parkingLotImage;

public class parkingLotImageEditRequest {
	private Long id;
	private String file;
	public parkingLotImageEditRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public parkingLotImageEditRequest(Long id, String file) {
		super();
		this.id = id;
		this.file = file;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
