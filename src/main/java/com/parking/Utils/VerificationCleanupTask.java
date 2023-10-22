package com.parking.utils;

import java.util.TimerTask;

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