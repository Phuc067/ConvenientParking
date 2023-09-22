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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "priceTickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceTicket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1651467095926986797L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	//parkingLotId
	@ManyToOne
	@JoinColumn(name="parkingLotId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ParkingLot parkingLot;
	
	//vehicleTypeId
	@ManyToOne
	@JoinColumn(name = "vehicleTypeId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private VehicleType vehicleType;
	@Column
	@NotBlank(message = "Period time shouldn't be empty")
	private Long periodTime;
	@Column
	@Min(0)
	private Long price;
	public PriceTicket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PriceTicket(Long id, ParkingLot parkingLot, VehicleType vehicleType,
			@NotBlank(message = "Period time shouldn't be empty") Long periodTime, @Min(0) Long price) {
		super();
		this.id = id;
		this.parkingLot = parkingLot;
		this.vehicleType = vehicleType;
		this.periodTime = periodTime;
		this.price = price;
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
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Long getPeriodTime() {
		return periodTime;
	}
	public void setPeriodTime(Long periodTime) {
		this.periodTime = periodTime;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
