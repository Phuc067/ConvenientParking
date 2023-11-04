package com.parking.dto.checkInOut;

public class CheckInData {
	private Long userId;
	private Long parkingLotId;
	public CheckInData() {
		super();
	}
	public CheckInData(Long userId, Long parkingLotId) {
		super();
		this.userId = userId;
		this.parkingLotId = parkingLotId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CheckInData other = (CheckInData) obj;
        if(this.userId == other.userId && this.parkingLotId == other.parkingLotId)
        {
        	return true;
        }
		return false;
    }

    @Override
    public int hashCode() {
        return (int) (userId * parkingLotId);
    }
}