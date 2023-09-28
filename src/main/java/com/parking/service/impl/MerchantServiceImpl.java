package com.parking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.entity.Merchant;
import com.parking.repository.MerchantRepository;
import com.parking.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantRepository repo;
	@Override
	public List<Merchant> getAll() {
		List<Merchant> merchants = repo.findAll();
		return merchants;
	}
	@Override
	public Merchant getById(Long id) {
		Optional<Merchant> oMerchant =  repo.findById(id);
		return oMerchant.get();
	}
}
