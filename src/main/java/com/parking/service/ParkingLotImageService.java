package com.parking.service;


import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.parking.dto.parkingLotImage.ParkingLotImageResponse;
import com.parking.entity.ParkingLotImage;
import com.parking.model.ResponseObject;

public interface ParkingLotImageService {
	@Transactional
	ResponseObject  upload(Long parkingLotId, MultipartFile file) ;

	ResponseObject download(long parkingLotId);
	
	ResponseObject  uploadImageToDB(Long parkingLotId, String string) throws IOException ;

	ResponseObject delete(Long id);

	ResponseObject update(Long id, String file);

}
