package com.parking.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.model.VehicleData;


@Repository
public class PendingTicketRepository {
	    private static final ConcurrentHashMap<CheckInData, VehicleData> pendingTicket = new ConcurrentHashMap<>();

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
	        return pendingTicket.get(checkInData);
	    }

	}


