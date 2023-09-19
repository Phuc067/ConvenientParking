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
}
