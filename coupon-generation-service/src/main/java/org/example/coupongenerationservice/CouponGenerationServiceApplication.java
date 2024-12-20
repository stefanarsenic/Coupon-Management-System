package org.example.coupongenerationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CouponGenerationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponGenerationServiceApplication.class, args);
	}



}
