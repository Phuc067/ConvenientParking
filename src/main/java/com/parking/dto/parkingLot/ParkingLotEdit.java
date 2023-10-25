package com.parking.dto.parkinglot;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ParkingLotEdit {
	private Long id;
	@NotBlank(message = "Name should not be empty")
	private String parkingLotName;
	@Min(0)
	private Long numberSlot;
	@NotBlank(message = "Latitude shouldn't be empty")
	private Double lat;
	@NotBlank(message = "Longitude shouldn't be empty")
	private Double Lng;
	private String timeOpen;
	private String timeClose;
	private String city;
	private String district;
	private String ward;
	private String street;
	private String number;
	public ParkingLotEdit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParkingLotEdit(Long id, @NotBlank(message = "Name should not be empty") String parkingLotName,
			@Min(0) Long numberSlot, @NotBlank(message = "Latitude shouldn't be empty") Double lat,
			@NotBlank(message = "Longitude shouldn't be empty") Double lng, String timeOpen, String timeClose,
			String city, String district, String ward, String street, String number) {
		super();
		this.id = id;
		this.parkingLotName = parkingLotName;
		this.numberSlot = numberSlot;
		this.lat = lat;
		Lng = lng;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.street = street;
		this.number = number;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	public Long getNumberSlot() {
		return numberSlot;
	}
	public void setNumberSlot(Long numberSlot) {
		this.numberSlot = numberSlot;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return Lng;
	}
	public void setLng(Double lng) {
		Lng = lng;
	}
	public String getTimeOpen() {
		return timeOpen;
	}
	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}
	public String getTimeClose() {
		return timeClose;
	}
	public void setTimeClose(String timeClose) {
		this.timeClose = timeClose;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
