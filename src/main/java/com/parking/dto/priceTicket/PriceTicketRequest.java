package com.parking.dto.priceTicket;

public class PriceTicketRequest {
	private Long parkingLotId;
	private Long vehicleTypeId;
	private Long price;
	public PriceTicketRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PriceTicketRequest(Long parkingLotId, Long vehicleTypeId, Long price) {
		super();
		this.parkingLotId = parkingLotId;
		this.vehicleTypeId = vehicleTypeId;
		this.price = price;
	}
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
}
