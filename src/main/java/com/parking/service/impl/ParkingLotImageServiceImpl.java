package com.parking.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.parking.Utils.ImageUtils;
import com.parking.dto.ParkingLotImageResponse;
import com.parking.entity.ParkingLot;
import com.parking.entity.ParkingLotImage;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotImageRepository;
import com.parking.repository.ParkingLotRepository;
import com.parking.service.ParkingLotImageService;
import com.parking.service.StorageService;

@Service
public class ParkingLotImageServiceImpl implements ParkingLotImageService {

	@Autowired
	private ParkingLotImageRepository parkingLotImageRepository;

//	@Override
//	@Transactional(rollbackFor = {Exception.class, Error.class})
//	public ResponseObject upload(Long parkingLotId, MultipartFile file) {
//		String path = storageService.store(file);
//		if (!parkingLotImageRepository.existsByParkingLotIdAndUrl(1l, path)) {
//			parkingLotImageRepository.insert(1L, path);
//		}
//		return new ResponseObject(HttpStatus.OK, "Store successfully", null);
//	}

	@Override
	public List<ParkingLotImage> download(long parkingLotId) {
		List<ParkingLotImage> parkingLotImages = parkingLotImageRepository.findByParkingLotId(parkingLotId);
		if (ObjectUtils.isNotEmpty(parkingLotImages)) {
			for (ParkingLotImage parkingLotImage : parkingLotImages) {
				parkingLotImage.setImage(ImageUtils.decompressImage(parkingLotImage.getImage()) );
			}
			return parkingLotImages;
		} else return null;

	}

	@Override
	@Transactional
	public ResponseObject uploadImageToDB(Long parkingLotId, MultipartFile file) throws IOException {
		if (!parkingLotImageRepository.existsByParkingLotIdAndImage(parkingLotId,
				ImageUtils.compressImage(file.getBytes()))) {

			parkingLotImageRepository.insert(parkingLotId, ImageUtils.compressImage(file.getBytes()));
		}
		return new ResponseObject(HttpStatus.OK, "Store successfully", null);
	}

	@Override
	public ResponseObject upload(Long parkingLotId, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}
