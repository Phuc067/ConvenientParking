package com.parking.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.merchant.MerchantSearchParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotEdit;
import com.parking.dto.parkinglot.ParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotSearch;
import com.parking.entity.ParkingLot;
import com.parking.entity.VehicleType;
import com.parking.model.MessageAndBoolean;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.VehicleTypeRepository;
import com.parking.service.ParkingLotService;
import com.parking.utils.AddressUtils;
import com.parking.utils.ImageUtils;
import com.parking.utils.StringUtils;

@Service
public class ParkingLotServiceImpl implements ParkingLotService{

	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
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
		Long id =0L;
		if(parkingLotRepository.existsByLatAndLng(parkingLotDto.getLat(), parkingLotDto.getLng()) && parkingLotRepository.existsByCityAndDistrictAndWardAndStreetAndNumber(parkingLotDto.getCity(), parkingLotDto.getDistrict(), parkingLotDto.getWard(), parkingLotDto.getStreet(), parkingLotDto.getNumber()))
		{
			return new ResponseObject(HttpStatus.ACCEPTED,"Không thể thêm 2 bãi giữ xe ở cũng 1 địa điểm", null);
		}
		try {
			parkingLotRepository.insert(parkingLotDto);
			id = parkingLotRepository.getMaxId();
		} catch (Exception e) {
			return new ResponseObject(HttpStatus.BAD_REQUEST, e.getMessage(), null);
		}
		return  new ResponseObject(HttpStatus.OK, "Parking lot was added successfully", id);
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
	public ResponseObject search(String keyword ) {
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
			return new ResponseObject(HttpStatus.OK, "Không tìm thấy bãi đỗ xe nào", null);
		}
	}

	@Override
	public ResponseObject getById(Long id) {
		Optional<ParkingLot> parkingLot = parkingLotRepository.findById(id);
		if(parkingLot.isPresent())
		{
			return new ResponseObject(HttpStatus.OK, "Lấy bãi giữ xe thành công", parkingLot);
		}
		else return new ResponseObject(HttpStatus.OK, "Lấy bãi giữ xe thất bại", null);
	}

	@Override
	public ResponseObject search(MerchantSearchParkingLotRequest request) {
		request.setKeyword(AddressUtils.formart(request.getKeyword()));
		List<ParkingLot> parkingLots = parkingLotRepository.search(request);
		for(ParkingLot parkingLot: parkingLots)
		{
			ImageUtils.decompressImage(parkingLot.getImages());
		}
		if(parkingLots.size()!=0)
		{
			return new ResponseObject(HttpStatus.OK, "Tìm kiếm bãi đỗ xe thành công", parkingLots);
		}
		else {
			return new ResponseObject(HttpStatus.OK, "Không tìm thấy bãi đỗ xe nào", null);
		}
	}

	@Override
	@Transactional
	public ResponseObject edit(ParkingLotEdit parkingLotEdit) {
		Optional<ParkingLot> parkingLotDB = parkingLotRepository.findById(parkingLotEdit.getId());
		MessageAndBoolean messageAndBoolean = isValidInformation(parkingLotDB, parkingLotEdit);
		if(!messageAndBoolean.getBoolean1())
		{
			return new ResponseObject(HttpStatus.ACCEPTED, messageAndBoolean.getMessage(), null);
		}
		try {
			parkingLotRepository.edit(parkingLotEdit);
			return new ResponseObject(HttpStatus.OK, "Sửa thông tin bãi giữ xe thành công", null);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.ACCEPTED, "Không thể sửa thông tin bãi đỗ xe", null);
		}

	}

	private MessageAndBoolean isValidInformation(Optional<ParkingLot> parkingLot, ParkingLotEdit parkingLotEdit) {
		
		if(parkingLot.isPresent())
		{
			ParkingLot parkingLotDB  = parkingLot.get();
			if( (parkingLotDB.getNumberSlot() -  parkingLotDB.getNumberSlotRemaining()) > parkingLotEdit.getNumberSlot())
			{
				return new MessageAndBoolean("Số lượng mới nhỏ hơn số xe đang giữ trong bãi!", false);
			}
		}
		else {
			return new MessageAndBoolean("Không tìm thấy bãi giữ xe!", false);
		}
		if(parkingLotRepository.existsByLatAndLng(parkingLotEdit.getLat(), parkingLotEdit.getLng()) && parkingLotEdit.getId() != parkingLot.get().getId())
		{
			return new MessageAndBoolean("Không thể thêm 2 bãi giữ xe ở cũng 1 địa điểm", false);
		}
		
		return new MessageAndBoolean("Hợp lệ", true);
	}

	@Override
	public ResponseObject deleteById(Long id) {
		Optional<ParkingLot> parkingLotDB = parkingLotRepository.findById(id);
		ParkingLot parkingLot;
		if(parkingLotDB.isPresent())
		{
			parkingLot = parkingLotDB.get();
		}
		else return new ResponseObject(HttpStatus.NOT_FOUND, "Không tìm thấy bãi giữ xe này", null);
		if(parkingLot.getNumberSlot()>parkingLot.getNumberSlotRemaining())
		{
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Không thể xóa vì đang có người sử dụng bãi giữ xe này", null);
		}
		parkingLotRepository.deleteById(id);
		return new ResponseObject(HttpStatus.OK, "Xoá bãi giữ xe thành công", null);
	}

	@Override
	public ResponseObject getVehicleType(Long id) {
		List<VehicleType> vehicleTypes = vehicleTypeRepository.findByParkingLotId(id);
		return new ResponseObject(HttpStatus.OK, "Lấy danh sách loại xe thành công", vehicleTypes);
	}


}
