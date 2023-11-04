package com.parking.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parking.dto.parkingLotImage.ParkingLotImageRequest;
import com.parking.dto.parkingLotImage.parkingLotImageEditRequest;
import com.parking.model.IdRequest;
import com.parking.model.ResponseObject;
import com.parking.service.ParkingLotImageService;

@RestController
@RequestMapping("/api/parkinglot-image")
public class ParkingLotImageController {
	@Autowired
	private ParkingLotImageService parkingLotImageService;

	@PostMapping("/upload")
	public ResponseEntity<?> doUploadFile(@RequestBody ParkingLotImageRequest request) throws IOException {
		ResponseObject responseObject= parkingLotImageService.uploadImageToDB(request.getParkingLotId(), request.getFile());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> doDeleteFile(@RequestBody IdRequest request)
	{
		ResponseObject responseObject= parkingLotImageService.delete(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> doEditFile(@RequestBody parkingLotImageEditRequest request)
	{
		ResponseObject responseObject= parkingLotImageService.update(request.getId(), request.getFile());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping("/download")
	public ResponseEntity<?> doDownLoadFile(@RequestParam("parkingLotId") Long parkingLotId) {
//		ResponseObject responseObject = parkingLotImageService.downloadImageFromDB(1L);
//		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
		return ResponseEntity.status(HttpStatus.OK).body(parkingLotImageService.download(parkingLotId));
	}
}
