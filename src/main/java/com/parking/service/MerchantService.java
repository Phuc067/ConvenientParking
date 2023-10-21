package com.parking.service;

import java.util.List;

import com.parking.entity.Merchant;
import com.parking.model.ResponseObject;

public interface MerchantService {

	public List<Merchant> getAll();
	public Merchant getById(Long id);
	public ResponseObject edit(Merchant merchant);
	public ResponseObject getByUsername(String username);
}
