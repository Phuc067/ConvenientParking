package com.parking.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.constant.RoleConstant;
import com.parking.dto.user.UserInsert;
import com.parking.entity.Login;
import com.parking.entity.User;
import com.parking.model.ResponseObject;
import com.parking.repository.LoginRepository;
import com.parking.repository.UserRepository;
import com.parking.service.UserService;


@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseObject getByUsername(String username) {
		
		Login login = loginRepository.findByUsername(username);
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseObject(HttpStatus.UNAUTHORIZED, "Không tìm thấy tài khoản.", null);
		}
		if(!login.getRole().getName().equals(RoleConstant.USER_NAME))
		{
			return new ResponseObject(HttpStatus.NO_CONTENT, "Không thể lấy thông tin từ tài khoản này.", null);
		}
		User user = userRepository.findByLoginId(login.getId());
		
		if(ObjectUtils.isEmpty(user))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "Người dùng chưa có thông tin trong hệ thống.", null);
		}
		else return new ResponseObject(HttpStatus.OK, "Lấy thông tin người dùng thành công.", user);
	}

	@Override
	@Transactional
	public ResponseObject insert(UserInsert user) {
		try {
			userRepository.insert(user);
			return new ResponseObject(HttpStatus.OK, "Thêm thông tin người dùng thành công", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.ACCEPTED, "Thêm thông tin người dùng thất bại", null);
		}
	}

}
