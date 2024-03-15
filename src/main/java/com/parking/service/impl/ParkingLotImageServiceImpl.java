package com.parking.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.parking.dto.parkingLotImage.ParkingLotImageResponse;
import com.parking.entity.ParkingLot;
import com.parking.entity.ParkingLotImage;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotImageRepository;
import com.parking.repository.ParkingLotRepository;
import com.parking.service.ParkingLotImageService;
import com.parking.service.StorageService;
import com.parking.utils.ImageUtils;

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
	public ResponseObject download(long parkingLotId) {
		List<ParkingLotImage> parkingLotImages = parkingLotImageRepository.findByParkingLotId(parkingLotId);
		if (ObjectUtils.isNotEmpty(parkingLotImages)) {
			for (ParkingLotImage parkingLotImage : parkingLotImages) {
				parkingLotImage.setData(ImageUtils.decompressImage(parkingLotImage.getData()) );
			}
			return new ResponseObject(HttpStatus.OK,"Lấy danh sách hình ảnh thành công", parkingLotImages);
		} 
		else return new ResponseObject(HttpStatus.NO_CONTENT,"Không tìm thấy hình ảnh", null);

	}

	@Override
	@Transactional
	public ResponseObject uploadImageToDB(Long parkingLotId, String file) throws IOException {
		byte data[]  = Base64.getDecoder().decode(file);
		if (!parkingLotImageRepository.existsByParkingLotIdAndData(parkingLotId,
				ImageUtils.compressImage(data))) {
			parkingLotImageRepository.insert(parkingLotId, ImageUtils.compressImage(data));
		}
		return new ResponseObject(HttpStatus.OK, "Store successfully", null);
	}

	@Override
	public ResponseObject upload(Long parkingLotId, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResponseObject delete(Long id) {
		parkingLotImageRepository.deleteById(id);
		return new ResponseObject(HttpStatus.OK, "Xoá ảnh thành công", null);
	}

	@Override
	@Transactional
	public ResponseObject update(Long id, String file) {
		byte data[]  = Base64.getDecoder().decode(file);
		parkingLotImageRepository.update(id, data);
		return null;
	}

}
