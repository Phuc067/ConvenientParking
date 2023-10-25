package com.parking.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.merchant.MerchantRequest;
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
	@Transactional
	public ResponseObject edit(Merchant merchant) {
		try {
			repo.edit(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.BAD_REQUEST,"Sửa thông tin thất bại", null);
		}
		return new ResponseObject(HttpStatus.ACCEPTED,"Sửa thông tin thành công", null);
	}
	
	@Override
	public ResponseObject getByUsername(String username) {
		Merchant merchant = repo.findByLoginUsername(username);
		if(ObjectUtils.isEmpty(merchant))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "Không tìm thấy công ty", null);
		}
		return new ResponseObject(HttpStatus.OK,"Lấy thông tin công ty thành công", merchant);
	}
	
	@Override
	@Transactional
	public ResponseObject insert(MerchantRequest merchant) {
		try {
			repo.insert(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.BAD_REQUEST,"Thêm thông tin cong ty thất bại", null);
		}
		return new ResponseObject(HttpStatus.ACCEPTED,"Thêm thông tin công ty  thành công", null);
	}
}
