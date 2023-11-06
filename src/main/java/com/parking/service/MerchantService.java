package com.parking.service;

import java.util.List;

import com.parking.dto.merchant.MerchantRequest;
import com.parking.entity.Merchant;
import com.parking.model.ResponseObject;

public interface MerchantService {

	List<Merchant> getAll();

	Merchant getById(Long id);

	ResponseObject edit(Merchant merchant);

	ResponseObject getByUsername(String username);

	ResponseObject insert(MerchantRequest merchant);

	ResponseObject getReportInYear(Long parkingLotId, Long year);
}
