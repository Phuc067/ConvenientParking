package com.parking.model;

public class PreCheckOutData {
    Long ticketID;
    int endUserID;
    int parkingLotID;

    public PreCheckOutData() {
    }

    public PreCheckOutData(Long ticketID, int endUserID, int parkingLotID) {
        this.ticketID = ticketID;
        this.endUserID = endUserID;
        this.parkingLotID = parkingLotID;
    }

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public int getEndUserID() {
        return endUserID;
    }

    public void setEndUserID(int endUserID) {
        this.endUserID = endUserID;
    }

    public int getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(int parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

}
