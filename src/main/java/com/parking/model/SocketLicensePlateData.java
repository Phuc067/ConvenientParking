package com.parking.model;


public class SocketLicensePlateData {

	    private int code;
	    private String message;
	    private PreCheckOutData checkOutData;
	    private String licensePlate;
	    
	    public SocketLicensePlateData(int code, String message,PreCheckOutData checkOutData2, String licensePlate) {
	        this.code = code;
	        this.message = message;
	        this.checkOutData = checkOutData2;
	        this.licensePlate = licensePlate;
	    }

	    public int getCode() {
	        return code;
	    }
	    public void setCode(int code) {
	        this.code = code;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public PreCheckOutData getCheckOutData() {
	        return checkOutData;
	    }

	    public void setCheckOutData(PreCheckOutData checkOutData) {
	        this.checkOutData = checkOutData;
	    }

	    public String getLicensePlate() {
	        return licensePlate;
	    }

	    public void setLicensePlate(String licensePlate) {
	        this.licensePlate = licensePlate;
	    }
	}

