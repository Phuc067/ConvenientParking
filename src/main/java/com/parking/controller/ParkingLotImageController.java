package com.parking.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parking.dto.UploadParkingLotImageRequest;
import com.parking.model.ResponseObject;
import com.parking.service.ParkingLotImageService;

@RestController
@RequestMapping("/api/parking-lot-image")
public class ParkingLotImageController {
	@Autowired
	private ParkingLotImageService parkingLotImageService;

	@PostMapping("/upload/{parkingLotId}")
	public ResponseEntity<?> doUploadFile( @PathVariable("parkingLotId") Long parkingLotId, @RequestBody MultipartFile file) throws IOException {
		ResponseObject responseObject= parkingLotImageService.uploadImageToDB(parkingLotId, file);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

	@GetMapping("/download")
	public ResponseEntity<?> doDownLoadFile(@RequestParam("parkingLotId") Long parkingLotId) {
		return ResponseEntity.status(HttpStatus.OK).body(parkingLotImageService.download(parkingLotId));
	}
}
