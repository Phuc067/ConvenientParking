package com.parking.service;

import java.util.List;

import com.parking.entity.Merchant;

public interface MerchantService {

	public List<Merchant> getAll();
	public Merchant getById(Long id);
	public void edit(Merchant merchant);
}
