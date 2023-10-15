package com.parking;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.parking.service.ParkingLotService;
import com.parking.utils.EmailUtils;
import com.parking.utils.VerificationCodeGenerator;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ParkingApplication {
	
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ParkingApplication.class, args);
	}
	
	@PostConstruct
	public void run()
	{
		
	}

}
