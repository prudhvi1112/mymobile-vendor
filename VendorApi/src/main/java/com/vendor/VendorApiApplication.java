package com.vendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VendorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorApiApplication.class, args);
	}

}
