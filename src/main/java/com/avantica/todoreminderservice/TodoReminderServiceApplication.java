package com.avantica.todoreminderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient 	// It acts as a eureka client
@EnableScheduling
public class TodoReminderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoReminderServiceApplication.class, args);
	}
}
