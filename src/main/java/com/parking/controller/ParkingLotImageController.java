package com.parking.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parking.dto.parkingLotImage.ParkingLotImageRequest;
import com.parking.dto.parkingLotImage.parkingLotImageEditRequest;
import com.parking.model.ResponseObject;
import com.parking.service.ParkingLotImageService;

@RestController
@RequestMapping("/api/parkinglot-images")
public class ParkingLotImageController {
	@Autowired
	private ParkingLotImageService parkingLotImageService;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> doDeleteFile(@PathVariable(name = "id") Long id)
	{
		ResponseObject responseObject= parkingLotImageService.delete(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> doEditFile(@PathVariable(name = "id") Long id, String file)
	{
		ResponseObject responseObject= parkingLotImageService.update(id, file);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
}
