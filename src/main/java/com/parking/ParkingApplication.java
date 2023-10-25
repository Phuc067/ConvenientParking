package com.parking;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parking.utils.AddressUtils;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ParkingApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@PostConstruct
	public void run() {
//		Instant timestamp1 = Instant.parse("2023-10-24T12:00:00Z");
//        Instant timestamp2 = Instant.parse("2023-10-25T14:30:00Z");
//
//        // Tính khoảng thời gian giữa hai timestamp
//        Duration duration = Duration.between(timestamp1, timestamp2);
//
//        // Lấy giá trị số giây, phút, giờ, v.v. trong khoảng thời gian
//        long seconds = duration.getSeconds();
//        long minutes = duration.toMinutes();
//        long hours = duration.toHours();
//        long days = duration.toDays();
//
//        System.out.println("Khoảng thời gian:");
//        System.out.println("Số giây: " + seconds);
//        System.out.println("Số phút: " + minutes);
//        System.out.println("Số giờ: " + hours);
//        System.out.println("Số ngày: " + days);
	}

}
