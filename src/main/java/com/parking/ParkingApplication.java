package com.parking;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parking.dto.priceTicket.PriceTicketResponse;
import com.parking.entity.PriceTicket;
import com.parking.repository.PriceTicketRepository;
import com.parking.repository.SpRepository;
import com.parking.utils.AddressUtils;
import com.parking.utils.TimeUtils;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ParkingApplication {
@Autowired
private PriceTicketRepository priceTicketRepository;
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ParkingApplication.class, args);
	}

	
	@PostConstruct
	public void run() {
		
		
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
