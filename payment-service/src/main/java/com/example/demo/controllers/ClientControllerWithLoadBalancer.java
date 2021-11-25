package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dtos.Driver;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v2")
public class ClientControllerWithLoadBalancer {

	
	
	private WebClient client;
	
	
	
	
	@GetMapping(path = "/registry/client")
	public Mono<Driver[]> getAllDriversUsingRegistry(){
		
		return client
				 .get()
				   .uri("lb://DRIVER-SERVICE/api/v1/drivers")
				     .retrieve()
				      .bodyToMono(Driver[].class);

	}


	@GetMapping(path = "/registry/lb/client")
	public Mono<String> getAllDriversUsingRegistryWithLoadBalancer(){
		
		return client
				 .get()
				   .uri("lb://DRIVER-SERVICE/api/v1/drivers")
				     .retrieve()
				      .bodyToMono(String.class);

	}

}
