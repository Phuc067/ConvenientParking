package com.parking;

import java.io.IOException;
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
//		String address = "6 vo van kiet , phuong 3        quan 6 / tp hcm  ";
//		address = AddressUtils.formart(address);
//		System.out.println(address);
	}

}
