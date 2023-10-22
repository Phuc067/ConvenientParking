package com.parking.service.impl;


import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.CheckInData;
import com.parking.dto.ParkingLotRequest;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotRepository;
import com.parking.service.ParkingLotService;
import com.parking.utils.AddressUtils;
import com.parking.utils.ImageUtils;

@Service
public class ParkingLotServiceImpl implements ParkingLotService{

	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Override
	public List<ParkingLot> getAllParkingLot() {
		List<ParkingLot> parkingLots =parkingLotRepository.findAll();
		for(ParkingLot parkingLot: parkingLots)
		{
			ImageUtils.decompressImage(parkingLot.getImages());
		}
		return parkingLots;
	}

	@Override
	@Transactional
	public ResponseObject add(ParkingLotRequest parkingLotDto) {
		
		if(parkingLotRepository.existsByLatAndLng(parkingLotDto.getLat(), parkingLotDto.getLng()))
		{
			return new ResponseObject(HttpStatus.FORBIDDEN,"It is not possible to create 2 parking lots at the same location", null);
		}
		try {
			parkingLotRepository.insert(parkingLotDto);
		} catch (Exception e) {
			return new ResponseObject(HttpStatus.BAD_REQUEST, e.getMessage(), null);
		}
		return  new ResponseObject(HttpStatus.ACCEPTED, "Parking lot was added successfully", null);
	}

	@Override
	public Long getMaxId() {
		// TODO Auto-generated method stub
		return parkingLotRepository.getMaxId();
	}

	@Override
	public List<ParkingLot> getParkingLotByMerchantId(Long id) {
		List<ParkingLot> parkingLots =parkingLotRepository.findByMerchantId(id);
		for(ParkingLot parkingLot: parkingLots)
		{
			ImageUtils.decompressImage(parkingLot.getImages());
		}
		return parkingLots;
	}

	@Override
	public ResponseObject search(String keyword) {
		keyword = AddressUtils.formart(keyword);
		List<ParkingLot> parkingLots = parkingLotRepository.search(keyword);
		for(ParkingLot parkingLot: parkingLots)
		{
			ImageUtils.decompressImage(parkingLot.getImages());
		}
		if(parkingLots.size()!=0)
		{
			return new ResponseObject(HttpStatus.OK, "Tìm kiếm bãi đỗ xe thành công", parkingLots);
		}
		else {
			return new ResponseObject(HttpStatus.NOT_FOUND, "Không tìm thấy bãi đỗ xe nào", null);
		}
	}

}
