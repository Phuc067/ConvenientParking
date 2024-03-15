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
import com.parking.service.ParkingLotSearchService;
import com.parking.service.ParkingLotService;
import com.parking.service.SocketService;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ParkingApplication {
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotSearchService parkingLotSearchService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ParkingApplication.class, args);
	}
	
	@PostConstruct
	public void run() {
		
//		List<ParkingLot> parkingLots = parkingLotRepository.findAll();
//		parkingLotSearchService.setParkingLotSearchServiceImpl(parkingLots);
//		int suggest = parkingLotSearchService.suggestParkingLot(10.806511904215052, 106.70339679765308);
//		System.out.println(suggest);
//	
	}
}
