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

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.primitives.Bytes;

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
	@JsonIgnore
	private ParkingLot parkingLot;
	
	@Column
	private byte image[];

	public ParkingLotImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingLotImage(Long id, ParkingLot parkingLot, byte[] image) {
		super();
		this.id = id;
		this.parkingLot = parkingLot;
		this.image = image;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
