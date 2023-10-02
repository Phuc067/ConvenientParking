package com.parking.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entity.Merchant;
import com.parking.model.ResponseObject;
import com.parking.service.MerchantService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class MerchantController {
	@Autowired
	private MerchantService service;
	
	@GetMapping("/merchants")
	public ResponseEntity<?> doGetAllMerchant()
	{
		List<Merchant> merchants = service.getAll();
		if(ObjectUtils.isEmpty(merchants))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(merchants);
	}
	
	@PostMapping("merchant/edit")
	public ResponseEntity<?> doEditMerchant(@RequestBody Merchant merchant)
	{
		try {
			service.edit(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Edit failed merchant information", null));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject("Merchant information has been successfully edited", null));
	}
	
}
