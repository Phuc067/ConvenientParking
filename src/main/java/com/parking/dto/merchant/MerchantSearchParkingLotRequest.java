package com.parking.dto.merchant;

public class MerchantSearchParkingLotRequest {
	private Long id;
	private String keyword;
	public MerchantSearchParkingLotRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MerchantSearchParkingLotRequest(Long id, String keyword) {
		super();
		this.id = id;
		this.keyword = keyword;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
