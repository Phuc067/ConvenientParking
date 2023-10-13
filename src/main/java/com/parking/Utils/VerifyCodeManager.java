package com.parking.utils;

import java.util.Timer;

import com.parking.repository.LoginRepository;

public class VerifyCodeManager {
    private Timer timer = new Timer();

   
    public void scheduleVerificationCleanup(long delayMilliseconds, String username , LoginRepository loginRepository) {
        timer.schedule(new VerificationCleanupTask(username, loginRepository), delayMilliseconds);
    }
}