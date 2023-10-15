package com.parking.model;

import com.parking.dto.CheckInData;

public class SocketMessageData {

    private int code;
    private String message;
    private CheckInData checkInData;

    public SocketMessageData(int code, String message, CheckInData checkInData) {
        this.code = code;
        this.message = message;
        this.checkInData = checkInData;
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

    public CheckInData getCheckInData() {
        return checkInData;
    }

    public void setCheckInData(CheckInData checkInData) {
        this.checkInData = checkInData;
    }

}