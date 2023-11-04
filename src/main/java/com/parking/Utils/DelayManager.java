package com.parking.utils;

import java.util.concurrent.TimeUnit;

public class DelayManager {

	public static void delay(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
