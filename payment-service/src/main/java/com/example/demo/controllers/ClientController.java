package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dtos.Driver;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ClientController {

	private WebClient client;
	
	
	@GetMapping(path = "/client")
	public Mono<Driver[]> getAllDrivers(){
		
		return client
				 .get()
				   .uri("http://localhost:3030/api/v1/drivers")
				     .retrieve()
				      .bodyToMono(Driver[].class);

	}

}
