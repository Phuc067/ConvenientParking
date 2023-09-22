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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parkingLotImages")

public class ParkingLotImage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2802160008300746992L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	//parking lot id
	@ManyToOne
	@JoinColumn(name = "parkingLotId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ParkingLot parkingLot;
	
	@Column
	private String url;

	public ParkingLotImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingLotImage(Long id, ParkingLot parkingLot, String url) {
		super();
		this.id = id;
		this.parkingLot = parkingLot;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
