package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.BookingEntity;
import com.example.demo.model.BookingStatus;
import com.example.demo.repos.BookingRepository;

@SpringBootApplication
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		
		return new CommandLineRunner() {
			
			@Autowired
			BookingRepository repo;
			
			@Override
			public void run(String... args) throws Exception {

				repo.save(new BookingEntity(202L,"guindy", "airport", 997L, 102L,
						LocalDateTime.now(), BookingStatus.COMPLETED));
				
				
			}
		};
	}

}
