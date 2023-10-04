package com.parking.security;

import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import com.parking.constant.SessionConstant;

public  class VerificationCleanupTask extends TimerTask {
    private HttpSession session;

    public VerificationCleanupTask(HttpSession session) {
        this.session = session;
    }

    @Override
    public void run() {
        // Xóa mã xác minh khỏi session sau khoảng thời gian delay
        session.removeAttribute(SessionConstant.CURRENT_OTP);
    }
}