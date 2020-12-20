package com.g2.scheduleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.g2.scheduleservice.infrastructure.rest")
@SpringBootApplication
public class SchedulerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}

}
