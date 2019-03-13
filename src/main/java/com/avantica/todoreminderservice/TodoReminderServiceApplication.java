package com.avantica.todoreminderservice;

import java.time.LocalDateTime;
import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.avantica.todoreminderservice.model.ToDo;
import com.avantica.todoreminderservice.repository.ToDoRepository;

@SpringBootApplication
@EnableEurekaClient 	// It acts as a eureka client
public class TodoReminderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoReminderServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(ToDoRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> new ToDo(i, "ToDo " + i, LocalDateTime.now(), "contact" + i + "@email.com"))
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}

}
