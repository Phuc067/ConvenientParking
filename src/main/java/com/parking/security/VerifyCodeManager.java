package com.parking.security;

import java.util.Timer;

import javax.servlet.http.HttpSession;

public class VerifyCodeManager {
    private Timer timer = new Timer();

    public void scheduleVerificationCleanup(long delayMilliseconds, HttpSession session) {
        timer.schedule(new VerificationCleanupTask(session), delayMilliseconds);
    }
}