package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="parkingLots")

public class ParkingLot implements Serializable {
	private static final long serialVersionUID = 298032442141281380L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column
	@Size(max = 50, message = "Name shouldn't be exceed 50 character")
	@NotBlank(message = "Name should not be empty")
	private String parkingLotName;
	@Column
	@Min(0)
	private Long numberSlot;
	@Column
	@Min(0)
	private Long numberSlotRemaining;
	@Column
	private Boolean status;
	//merchantId
	@ManyToOne
	@JoinColumn(name = "merchantId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Merchant merchant;
	@Column
	@NotBlank(message = "Latitude shouldn't be empty")
	private Double lat;
	@Column
	@NotBlank(message = "Longitude shouldn't be empty")
	private Double Lng;
	@Column
	private String timeOpen;
	@Column
	private String timeClose;
	@Column
	private String city;
	@Column
	private String district;
	@Column
	private String ward;
	@Column
	private String street;
	@Column
	private String number;
	public ParkingLot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParkingLot(Long id,
			@Size(max = 50, message = "Name shouldn't be exceed 50 character") @NotBlank(message = "Name should not be empty") String parkingLotName,
			@Min(0) Long numberSlot, @Min(0) Long numberSlotRemaining, Boolean status, Merchant merchant,
			@NotBlank(message = "Latitude shouldn't be empty") Double lat,
			@NotBlank(message = "Longitude shouldn't be empty") Double lng, String timeOpen, String timeClose,
			String city, String district, String ward, String street, String number) {
		super();
		this.id = id;
		this.parkingLotName = parkingLotName;
		this.numberSlot = numberSlot;
		this.numberSlotRemaining = numberSlotRemaining;
		this.status = status;
		this.merchant = merchant;
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
	public Long getNumberSlotRemaining() {
		return numberSlotRemaining;
	}
	public void setNumberSlotRemaining(Long numberSlotRemaining) {
		this.numberSlotRemaining = numberSlotRemaining;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
