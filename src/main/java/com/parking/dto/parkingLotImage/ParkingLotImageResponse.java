package com.parking.dto;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotImageResponse {
	
	private Long  id;
	
	private List<byte[]> images;

	public ParkingLotImageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingLotImageResponse(Long id, List<byte[]> images) {
		super();
		this.id = id;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<byte[]> getImages() {
		return images;
	}

	public void setImages(List<byte[]> images) {
		this.images = images;
	}

}
