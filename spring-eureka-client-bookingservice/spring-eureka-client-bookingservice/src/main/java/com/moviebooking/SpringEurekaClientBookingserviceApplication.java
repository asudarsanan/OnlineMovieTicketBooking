package com.moviebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringEurekaClientBookingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaClientBookingserviceApplication.class, args);
	}

}
