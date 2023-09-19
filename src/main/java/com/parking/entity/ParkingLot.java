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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="parkingLot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot implements Serializable {
	/**
	 * 
	 */
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
	@NotBlank(message = "longitude shouldn't be empty")
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
}
