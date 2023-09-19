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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
}
