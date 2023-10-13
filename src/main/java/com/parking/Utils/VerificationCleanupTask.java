package com.parking.utils;

import java.util.TimerTask;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;

import com.parking.constant.SessionConstant;
import com.parking.repository.LoginRepository;

public  class VerificationCleanupTask extends TimerTask {
    private String username;

    private LoginRepository loginRepository;
    
    
    public VerificationCleanupTask(String username, LoginRepository loginRepository) {
		super();
		this.username = username;
		this.loginRepository = loginRepository;
	}

	@Override
	
    public void run() {     
       loginRepository.removeVerificationCode(username);
    }
}