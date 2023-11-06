package com.parking.dto.merchant;

public class ReportResponse {
	private Long month;
	private double total;
	
	public ReportResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportResponse(Long month, double total) {
		super();
		this.month = month;
		this.total = total;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}	
