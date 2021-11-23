package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.repos.DriverRepository;
import com.example.demo.entity.*;
@SpringBootApplication
public class DriverServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverServiceApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner runner() {
		
		return new CommandLineRunner() {
			
			@Autowired
			DriverRepository repo;
			
			@Override
			public void run(String... args) throws Exception {

				repo.save(new Driver(101L,"Ramesh","kumar","ram@ab.com",8747477,LocalDate.of(1972,10,12)));
			}
		};
	}
}
