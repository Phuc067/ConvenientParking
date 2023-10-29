package com.parking.dto.parkinglot;


public class ParkingLotRequest {
	private String parkingLotName;
	private Long numberSlot;
	private Long merchantId;
	private Double lat;
	private Double lng;
	private String timeOpen;
	private String timeClose;
	private String city;
	private String district;
	private String ward;
	private String street;
	private String number;
	public ParkingLotRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ParkingLotRequest(String parkingLotName, Long numberSlot, Long merchantId, Double lat, Double lng,
			String timeOpen, String timeClose, String city, String district, String ward, String street,
			String number) {
		super();
		this.parkingLotName = parkingLotName;
		this.numberSlot = numberSlot;
		this.merchantId = merchantId;
		this.lat = lat;
		this.lng = lng;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.street = street;
		this.number = number;
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
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
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
