package com.parking;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ParkingApplication {
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ParkingApplication.class, args);
	}

	
}
