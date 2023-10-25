package com.parking.dto.merchant;


public class MerchantRequest {
	private String name;
	private String represent;
	private String phone;
	private Long loginId;
	public MerchantRequest( String name, String represent, String phone, Long loginId) {
		super();
		this.name = name;
		this.represent = represent;
		this.phone = phone;
		this.loginId = loginId;
	}
	public MerchantRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRepresent() {
		return represent;
	}
	public void setRepresent(String represent) {
		this.represent = represent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getLoginId() {
		return loginId;
	}
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	
	
}
