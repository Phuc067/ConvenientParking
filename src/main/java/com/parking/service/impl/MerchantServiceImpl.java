package com.parking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.parking.entity.Merchant;
import com.parking.model.ResponseObject;
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
	@Override
	@Transactional(value = TxType.REQUIRED)
	public ResponseObject edit(Merchant merchant) {
		try {
			repo.edit(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.BAD_REQUEST,"Edit failed merchant information", null);
		}
		return new ResponseObject(HttpStatus.ACCEPTED,"Merchant information has been successfully edited", null);
	}
}
