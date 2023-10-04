package com.parking.service.impl;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.parking.constant.RoleConstant;
import com.parking.constant.SessionConstant;
import com.parking.constant.SystemAdmin;
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
		
		if(loginDto.getUsername().equals( SystemAdmin.login.getUsername())  && loginDto.getPassword().equals(SystemAdmin.login.getPassword()))
		{
			ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully", "admin", RoleConstant.ADMIN);
			return responseLoginDto;
		}
		
		Login login = loginRepository.findByUsername(loginDto.getUsername());
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseLoginDto(HttpStatus.BAD_REQUEST,"Username is invalid",null,null);
		}
		
		if (bcrypt.matches(loginDto.getPassword(), login.getPassword())) {
			
			if (login.getRole().getName().equals(RoleConstant.MERCHANT_NAME)) {
				Merchant merchant = merchantRepository.findByLoginId(login.getId());
				ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully", merchant, RoleConstant.MERCHANT);
				return responseLoginDto;
			}
			else if (login.getRole().getName().equals(RoleConstant.EMPLOYEE_NAME)) {
				Employee employee = employeeRepository.findByLoginId(login.getId());
				ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully",employee, RoleConstant.EMPLOYEE);
				return responseLoginDto;
			}
			else if(login.getRole().getName().equals(RoleConstant.USER_NAME)) {
				User user = userRepository.findByLoginId(login.getId());
				ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully",user, RoleConstant.USER);
				return responseLoginDto;
			}
		}
		return new ResponseLoginDto(HttpStatus.BAD_REQUEST,"you have entered the wrong password",null,null);
	}
	@Override
	public Object register(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
