package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.repos.DriverRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import com.example.demo.entity.*;
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.demo","org.training.config"})
@OpenAPIDefinition(info = @Info(title = "Driver Service", version = "1.0"))
public class DriverServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
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
