package com.parking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parking.entity.VehicleType;
import com.parking.model.ResponseObject;
import com.parking.repository.VehicleTypeRepository;
import com.parking.service.VehicleTypeService;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService{
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	@Override
	public ResponseObject getAll() {
		try {
			List<VehicleType> vehicleTypes= vehicleTypeRepository.findAll();
			return new ResponseObject(HttpStatus.OK, "Lấy danh sách loại xe thành công", vehicleTypes);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.ACCEPTED, "Lấy danh sách loại xe thất bại", null);
		}
	}


}
