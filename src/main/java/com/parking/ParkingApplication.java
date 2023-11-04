package com.parking;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.parkinglot.ParkingLotSearch;
import com.parking.entity.ParkingLot;
import com.parking.entity.VehicleType;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.PriceTicketRepository;
import com.parking.repository.VehicleTypeRepository;
import com.parking.service.SocketService;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ParkingApplication {
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ParkingApplication.class, args);
	}
	
	@PostConstruct
	public void run() {
		
//		ParkingLotSearch parkingLotSearch = new ParkingLotSearch(3L,"Le");
//		List<ParkingLot> parkingLots = parkingLotRepository.search(parkingLotSearch);
//		for(ParkingLot parkingLot: parkingLots){
//			System.out.println(parkingLot.getParkingLotName());
//		}
	}
}
