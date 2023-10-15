package com.parking.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.parking.model.ResponseObject;
import com.parking.service.ParkingLotImageService;

@RestController
@RequestMapping("/api/parkinglotimage")
public class ParkingLotImageController {
	@Autowired
	private ParkingLotImageService parkingLotImageService;

	@PostMapping("/upload")
	public ResponseEntity<?> doUploadFile(@RequestParam("parkingLotId") Long parkingLotId, @RequestParam("file") MultipartFile file) throws IOException {
		ResponseObject responseObject= parkingLotImageService.uploadImageToDB(parkingLotId, file);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

	@GetMapping("/download")
	public ResponseEntity<?> doDownLoadFile(@RequestParam("parkingLotId") Long parkingLotId) {
//		ResponseObject responseObject = parkingLotImageService.downloadImageFromDB(1L);
//		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
		return ResponseEntity.status(HttpStatus.OK).body(parkingLotImageService.download(parkingLotId));
	}
}
