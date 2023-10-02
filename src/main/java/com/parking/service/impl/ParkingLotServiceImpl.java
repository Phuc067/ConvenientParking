package com.parking.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.ParkingLotDto;
import com.parking.entity.Merchant;
import com.parking.entity.ParkingLot;
import com.parking.repository.MerchantRepository;
import com.parking.repository.ParkingLotRepository;
import com.parking.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService{

	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Override
	public List<ParkingLot> getAllParkingLot() {
		
		return parkingLotRepository.findAll();
	}

	@Override
	public void add(ParkingLotDto parkingLotDto) {
		
		ParkingLot parkingLot = new ParkingLot();
		Long id = parkingLotRepository.getMaxId()+1;
		System.out.println(id);
		parkingLot.setId(id);
		parkingLot.setParkingLotName(parkingLotDto.getParkingLotName());
		parkingLot.setNumberSlot(parkingLotDto.getNumberSlot());
		parkingLot.setNumberSlotRemaining(parkingLotDto.getNumberSlot());
		Merchant merchant = merchantRepository.findById(parkingLotDto.getMerchantId()).get();
		parkingLot.setMerchant(merchant);
		parkingLot.setStatus(1l);
		parkingLot.setLat(parkingLotDto.getLat());
		parkingLot.setLng(parkingLotDto.getLng());
		parkingLot.setTimeClose(parkingLotDto.getTimeClose());
		parkingLot.setTimeOpen(parkingLotDto.getTimeOpen());
		parkingLot.setCity(parkingLotDto.getCity());
		parkingLot.setDistrict(parkingLotDto.getDistrict());
		parkingLot.setWard(parkingLotDto.getWard());
		parkingLot.setStreet(parkingLotDto.getStreet());
		parkingLot.setNumber(parkingLotDto.getNumber());
		parkingLotRepository.save(parkingLot);
	}

	@Override
	public Long getMaxId() {
		// TODO Auto-generated method stub
		return parkingLotRepository.getMaxId();
	}

	@Override
	public List<ParkingLot> getParkingLotByMerchantId(Long id) {
		
		return parkingLotRepository.findByMerchantId(id);
	}

}
