package com.parking.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entity.Merchant;
import com.parking.service.MerchantService;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
	@Autowired
	private MerchantService service;
	
	@GetMapping("")
	public ResponseEntity<?> doGetAllMerchant()
	{
		List<Merchant> merchants = service.getAll();
		if(ObjectUtils.isEmpty(merchants))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(merchants);
	}
	
}
