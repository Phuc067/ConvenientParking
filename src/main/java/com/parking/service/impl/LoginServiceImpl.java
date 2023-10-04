package com.parking.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.parking.constant.RoleConstant;
import com.parking.dto.LoginDto;
import com.parking.dto.ResponseLoginDto;
import com.parking.entity.Employee;
import com.parking.entity.Login;
import com.parking.entity.Merchant;
import com.parking.entity.User;
import com.parking.repository.EmployeeRepository;
import com.parking.repository.LoginRepository;
import com.parking.repository.MerchantRepository;
import com.parking.repository.UserRepository;
import com.parking.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	@Override
	public ResponseLoginDto doLogin(LoginDto loginDto) {
		Login login = loginRepository.findByUsername(loginDto.getUsername());
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseLoginDto("Username is invalid",null,null);
		}
		if (bcrypt.matches(loginDto.getPassword(), login.getPassword())) {
			
			if (login.getRole().getName().equals(RoleConstant.ADMIN)) {
				Merchant merchant = merchantRepository.findByLoginId(login.getId());
				ResponseLoginDto responseLoginDto = new ResponseLoginDto("Logged in successfully", merchant, RoleConstant.ADMIN);
				return responseLoginDto;
			}
			else if (login.getRole().getName().equals(RoleConstant.EMPLOYEE)) {
				Employee employee = employeeRepository.findByLoginId(login.getId());
				ResponseLoginDto responseLoginDto = new ResponseLoginDto("Logged in successfully",employee, RoleConstant.EMPLOYEE);
				return responseLoginDto;
			}
			else if(login.getRole().getName().equals(RoleConstant.USER)) {
				User user = userRepository.findByLoginId(login.getId());
				ResponseLoginDto responseLoginDto = new ResponseLoginDto("Logged in successfully",user, RoleConstant.USER);
				return responseLoginDto;
			}
		}
		return new ResponseLoginDto("you have entered the wrong password",null,null);
	}
	@Override
	public Object register(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
