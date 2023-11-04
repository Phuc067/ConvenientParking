package com.parking.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.model.VehicleData;

@Repository
public class PendingTicketRepository {
	    private static ConcurrentHashMap<CheckInData, VehicleData> pendingTicket = new ConcurrentHashMap<>();

	    public boolean containsKey(CheckInData checkInData) {
	        return pendingTicket.containsKey(checkInData);
	    }

	    public boolean addPendingTicket(CheckInData checkInData) {
	        if (pendingTicket.containsKey(checkInData)) {
	            return false;
	        }
	        pendingTicket.put(checkInData, new VehicleData(-1, null));
	        return true;
	    }

	    public void removePendingTicket(CheckInData checkInData) {
	        pendingTicket.remove(checkInData);
	    }

	    public void setPendingTicketInformation(CheckInData checkInData, VehicleData vehicleData) {
	    	pendingTicket.put(checkInData, vehicleData);
	    	
	    }

	    public boolean isPendingTicket(CheckInData checkInData) {
	        return pendingTicket.containsKey(checkInData) && pendingTicket.get(checkInData).getVehicleTypeId() == -1;
	    }

	    public VehicleData getPendingTicketInformation(CheckInData checkInData) {
	        VehicleData vehicleData = pendingTicket.get(checkInData);
	        return  vehicleData;
	     }
	    
	    public void show()
	    {
	    	for (ConcurrentHashMap.Entry<CheckInData, VehicleData> entry : pendingTicket.entrySet()) {
	    	    CheckInData key = entry.getKey();
	    	    VehicleData value = entry.getValue();
	    	    System.out.println("Key: " + key.getUserId() +" " + key.getParkingLotId() + ", Value: " + value.getLicensePlate() + " "+ value.getVehicleTypeId());
	    	}
	    }
	}


