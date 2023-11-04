package com.parking.dto.priceTicket;


public class PriceTicketResponse {
	private Long id;
	private String typeName;
	private Long price;
	public PriceTicketResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public PriceTicketResponse(Long id, String typeName, Long price) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.price = price;
	}


	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
